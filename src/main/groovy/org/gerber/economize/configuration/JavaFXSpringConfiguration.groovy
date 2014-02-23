package org.gerber.economize.configuration

import javafx.fxml.FXMLLoader

import org.gerber.economize.javafx.controller.MainController
import org.gerber.economize.javafx.controller.NewAccountController
import org.gerber.economize.javafx.controller.NewBankController
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["org.gerber.economize"])
class JavaFXSpringConfiguration {
	@Autowired
	private NewBankController newBankController
	@Autowired
	private NewAccountController newAccountController


	@Bean(name="ViewMap")
	public Map<String, Object> getViewMap() {
		def Map<String, Object> views = new HashMap()
		def fxmlLoader
		fxmlLoader = new FXMLLoader(getClass().getResource(MainController.NEW_BANK_VIEW))
		fxmlLoader.setController(this.newBankController)
		views.put(MainController.NEW_BANK_VIEW, fxmlLoader.load());
			
		fxmlLoader = new FXMLLoader(getClass().getResource(MainController.NEW_ACCOUNT_VIEW))
		fxmlLoader.setController(this.newAccountController)
		views.put(MainController.NEW_ACCOUNT_VIEW, fxmlLoader.load());

		return views
	}

}
