/**
 * 
 */
package org.gerber.economize.view

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

import org.gerber.economize.configuration.SpringConfiguration
import org.gerber.economize.controller.*
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * @author Michael Gerber
 *
 */
class NewBankView extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)
		primaryStage.setTitle("Economize");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/NewBankView.fxml"));
		fxmlLoader.setController(ctx.getBean(NewBankController.class));
		Pane myPane = (Pane) fxmlLoader.load();
		Scene myScene = new Scene(myPane);
		primaryStage.setScene(myScene);
		primaryStage.show();
	}

}
