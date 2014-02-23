/**
 * 
 */
package org.gerber.economize.javafx.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import org.gerber.economize.service.AccountInformationService
import org.gerber.economize.service.BankInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

/**
 * @author Michael Gerber
 *
 */
@Controller
class NewAccountController {

    @FXML
    protected TextField blz

    @FXML
    protected TextField bank

    @FXML
	protected TextField knr

	@Autowired BankInformationService bankInformationService
    @Autowired AccountInformationService accountInformationService

	@FXML
	protected void saveButtonFired(ActionEvent event) {

       this.accountInformationService.createAccount(this.blz.getText(), this.knr.getText(), '')

	}
}
