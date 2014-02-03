

/**
 * 
 */
package org.gerber.economize.controller

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

/**
 * @author Michael Gerber
 *
 */
@Controller
class MainController {
	@FXML
	private StackPane actionView
	@Autowired
	NewBankController newBankController
	
	public void showNewBankView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/NewBankView.fxml"));
		fxmlLoader.setController(this.newBankController);
		this.actionView.getChildren().addAll(fxmlLoader.load());
		 //= (Pane) fxmlLoader.load();
		//Scene myScene = new Scene(myPane);
		//primaryStage.setScene(myScene);
		//primaryStage.show();


	}
}
