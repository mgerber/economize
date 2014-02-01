package org.gerber.economize.view

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

import org.gerber.economize.configuration.SpringConfiguration;
import org.gerber.economize.controller.EventHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class TestView extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)
		primaryStage.setTitle("FXML TableView Example");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/myView.fxml"));
		
        fxmlLoader.setController(ctx.getBean(EventHandler.class));
        Pane myPane = (Pane) fxmlLoader.load();
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
