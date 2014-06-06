/**
 *
 */
package org.gerber.economize.javafx.controller

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField
import org.gerber.economize.domain.Account
import org.gerber.economize.domain.Bank
import org.gerber.economize.repositories.AccountInformationRepository
import org.gerber.economize.repositories.BankInformationRepository
import org.gerber.economize.service.BankInformationService
import org.gerber.economize.service.dto.BankDTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Michael Gerber
 *
 */
@Controller
class NewBankController {
    @FXML
    protected TextField bankName
    @FXML
    protected TextField blz
    @FXML
    protected TextField url
    @FXML
    protected TextField port

    @Autowired
    BankInformationService bankInformationService

    @FXML
    protected void saveButtonFired(ActionEvent event) {
		BankDTO bankDTO = new BankDTO()
		bankDTO.bankCode = this.blz.getText()
		bankDTO.name = this.bankName.getText()
		bankDTO.hbciHost = this.url.getText()
		bankDTO.port = this.port.getText()
        bankInformationService.createBank(bankDTO)

    }
}
