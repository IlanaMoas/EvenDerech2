package controller;

import java.util.Observable;

import model.Model;
import view.View;

public class MyController implements Controller {
	private View ui;
	private Model model;

	public MyController(View ui, Model model){
		this.ui = ui;
		this.model = model;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
