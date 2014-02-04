/**
 * 
 */
package org.gerber.economize.controller

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField

import org.gerber.economize.domain.Bank
import org.gerber.economize.repositories.BankInformationRepository
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

	@Autowired BankInformationRepository bankInformationRepository
	
	@FXML
	protected void saveButtonFired(ActionEvent event) {
		Bank bank = new Bank();
		bank.bankName = this.bankName.getText();
		bank.blz = this.blz.getText()
		bank.host = this.url.getText()
		bank.port = this.port.getText()
		bankInformationRepository.save(bank)
	}
}
