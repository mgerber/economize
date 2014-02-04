/**
 * 
 */
package org.gerber.economize.controller

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
        def  fxmlLoader = new FXMLLoader(getClass().getResource(NEW_BANK_VIEW));
        fxmlLoader.setController(this.newBankController);

        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(fxmlLoader.load())
    }

    @FXML
    public void showAccounts() {
        accountInformationRepository.findAll().each {it -> LOGGER.info '{} found', it}
    }

    @FXML
    public void newAccount() {
        def  fxmlLoader = new FXMLLoader(getClass().getResource(NEW_ACCOUNT_VIEW))
        fxmlLoader.setController(this.newAccountController)

        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(fxmlLoader.load())
    }

}
