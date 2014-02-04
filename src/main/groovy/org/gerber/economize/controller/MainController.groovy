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

	@Resource(name='ViewMap')
	private Map<String, Object> viewMap

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
        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(this.viewMap.get(NEW_BANK_VIEW))
    }

    @FXML
    public void showAccounts() {
        accountInformationRepository.findAll().each {it -> LOGGER.info '{} found', it}
    }

    @FXML
    public void newAccount() {
        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(this.viewMap.get(NEW_ACCOUNT_VIEW))
    }

}
