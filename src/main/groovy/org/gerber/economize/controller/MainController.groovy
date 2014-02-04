/**
 * 
 */
package org.gerber.economize.controller

import javax.annotation.Resource;

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.StackPane

import org.gerber.economize.repositories.AccountInformationRepository
import org.gerber.economize.repositories.BankInformationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

/**
 * @author Michael Gerber
 *
 */
@Controller
class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController)

    public static final String NEW_BANK_VIEW = "/fxml/NewBankView.fxml"
    public static final String NEW_ACCOUNT_VIEW = "/fxml/NewAccountView.fxml"
	

    @FXML
    private StackPane actionView

	private Map<String, Object> viewMap = new HashMap()

    @Autowired
    private NewBankController newBankController

    @Autowired
    NewAccountController newAccountController
    @Autowired
    BankInformationRepository bankInformationRepository
    @Autowired
    AccountInformationRepository accountInformationRepository

    @FXML
    public void showBanks() {
        bankInformationRepository.findAll().each { it -> LOGGER.info '{} found', it}
    }

    @FXML
    public void newBank() {
		def view = this.viewMap.get(NEW_BANK_VIEW)
		if (view == null)
		{
			LOGGER.info 'creating new view {}', NEW_BANK_VIEW
			def  fxmlLoader = new FXMLLoader(getClass().getResource(NEW_BANK_VIEW))
			fxmlLoader.setController(this.newBankController)
			view = fxmlLoader.load();
			this.viewMap.put(NEW_BANK_VIEW, view);
		}

        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(view)
    }

    @FXML
    public void showAccounts() {
        accountInformationRepository.findAll().each {it -> LOGGER.info '{} found', it}
    }

    @FXML
    public void newAccount() {
		def view = this.viewMap.get(NEW_ACCOUNT_VIEW)
		if (view == null)
		{
			LOGGER.info 'creating new view {}', NEW_ACCOUNT_VIEW
			def  fxmlLoader = new FXMLLoader(getClass().getResource(NEW_ACCOUNT_VIEW))
			fxmlLoader.setController(this.newAccountController)
			view = fxmlLoader.load();
			this.viewMap.put(NEW_ACCOUNT_VIEW, view);
		}

        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(view)
    }

}
