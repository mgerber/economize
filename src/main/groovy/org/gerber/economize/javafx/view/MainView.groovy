/**
 * 
 */
package org.gerber.economize.javafx.view

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

import org.gerber.economize.configuration.JavaFXSpringConfiguration
import org.gerber.economize.configuration.SpringConfiguration
import org.gerber.economize.javafx.controller.MainController
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * @author Michael Gerber
 *
 */
class MainView extends Application {

    public static final String MAIN_VIEW = "/fxml/MainView.fxml"

    @Override
	public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Economize");

        def ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class, JavaFXSpringConfiguration.class)
		def fxmlLoader = new FXMLLoader(getClass().getResource(MAIN_VIEW))
		fxmlLoader.setController(ctx.getBean(MainController.class))

		def myPane = fxmlLoader.load()

        //((MainController)fxmlLoader.getController()).showNewBankView()

        Scene myScene = new Scene(myPane);
		primaryStage.setScene(myScene);
		primaryStage.show();
	}

}
