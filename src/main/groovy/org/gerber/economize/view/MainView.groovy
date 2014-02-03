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
import org.gerber.economize.controller.MainController
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * @author Michael Gerber
 *
 */
class MainView extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)
		primaryStage.setTitle("Economize");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
		fxmlLoader.setController(ctx.getBean(MainController.class));
		Pane myPane = (Pane) fxmlLoader.load();
		((MainController)fxmlLoader.getController()).showNewBankView();
		Scene myScene = new Scene(myPane);
		primaryStage.setScene(myScene);
		primaryStage.show();
	}

}
