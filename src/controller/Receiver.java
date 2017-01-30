package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import model.data.*;
import model.data.Level.*;
import model.policy.*;

public class Receiver {
	public enum CommandType {LOAD, DISPLAY, MOVE, SAVE, EXIT }
	//	private LevelLoader ll;
	private Level level;
	private HashMap<String, Constructor<LevelLoader>> loaderConstructorMap;
	private HashMap<String, Constructor<LevelSaver>> saverConstructorMap;

	@SuppressWarnings("unchecked")
	public Receiver(){
		loaderConstructorMap = new HashMap<String, Constructor<LevelLoader>>();
		saverConstructorMap = new HashMap<String, Constructor<LevelSaver>>();


		loaderConstructorMap.put("txt", (Constructor<LevelLoader>)MyTextLevelLoader.class.getConstructors()[0]);
		loaderConstructorMap.put("obj", (Constructor<LevelLoader>)MyObjectLevelLoader.class.getConstructors()[0]);

		saverConstructorMap.put("txt", (Constructor<LevelSaver>)MyTextLevelSaver.class.getConstructors()[0]);
		saverConstructorMap.put("obj", (Constructor<LevelSaver>)MyObjectLevelSaver.class.getConstructors()[0]);

	}

	public void action(CommandType type, String arg){
		switch(type){
		case LOAD:
			LoadLevelByFileName(arg);
			break;
		case DISPLAY:
			print();
			break;
		case EXIT:
			System.exit(0);
			break;
		case MOVE:
			move(arg);
			break;
		case SAVE:
			saveLevelAsFile(arg);
			break;
		default:
			System.err.println("Error! Command ton found.");
			break;
		}
	}

	private void saveLevelAsFile(String fileName) {
		Constructor<LevelSaver> levelSaverToCall = saverConstructorMap.get(fileName.substring(fileName.length()-3));
		LevelSaver saver;
		try {
			saver = levelSaverToCall.newInstance();
			saver.saveLevel(fileName, level);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	private void LoadLevelByFileName(String fileName) {
		if(!(new File(fileName).isFile())){
			System.err.println("Error loading file. A file with the name" + fileName + " doas not exist");
			return;
		}
		FileInputStream is = null;
		try {
			is = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<LevelLoader> levelLoaderToCall = loaderConstructorMap.get(fileName.substring(fileName.length()-3));
		LevelLoader loader;
		try {
			if(is != null){
				loader = levelLoaderToCall.newInstance();
				level = loader.loadLevel(is);
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(is != null){
					is.close();
				}
				else{
					System.err.println("Error generating level. File type is not supported");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	private void print(){
		System.out.println(LevelToString.getToString(level));	
	}

	private void move(String arg){
		Direction dir = null;
		Element grid[][] = level.getLevelGrid();
		Player player = level.getMainPlayer();
		Element potentialBox = null;

		switch(arg.toUpperCase()){
		case("UP"):
			dir = Direction.UP;
		potentialBox = grid[player.getRow()-1][player.getCol()];
		break;
		case("DOWN"):
			dir = Direction.DOWN;
		potentialBox = grid[player.getRow()+1][player.getCol()];
		break;
		case("LEFT"):
			dir = Direction.LEFT;
		potentialBox = grid[player.getRow()][player.getCol()-1];
		break;
		case("RIGHT"):
			dir = Direction.RIGHT;
		potentialBox = grid[player.getRow()][player.getCol()+1];
		break;
		}
		if(potentialBox != null){
			MySokobanPolicy.move(level, dir, potentialBox.getRow(), potentialBox.getCol());
		}
		else{
			MySokobanPolicy.move(level, dir, -1, -1);
		}
	}
}

