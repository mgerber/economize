package org.gerber.economize.javafx.controller

import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label
import javafx.scene.control.TextField;

@Controller
class EventHandler
{
	@FXML
	private TextField synopsis
	@FXML
	private Label messageBox;
	
	@FXML
	protected void newIssueFired(ActionEvent event)
	{
		println("newIssueFired" + event)
		this.synopsis.setText("newIssueFired");	
		this.messageBox.setText("newIssueFired")
	}
	@FXML
	protected void saveIssueFired(ActionEvent event)
	{
		println("saveIssueFired" + event)
		this.synopsis.setText("saveIssueFired");
		this.messageBox.setText("saveIssueFired")
	}
	@FXML
	protected void deleteButtonFired(ActionEvent event)
	{
		println("deleteIssueFired" + event)
		this.synopsis.setText("deleteIssueFired");
		this.messageBox.setText("deleteIssueFired")
	}
}
