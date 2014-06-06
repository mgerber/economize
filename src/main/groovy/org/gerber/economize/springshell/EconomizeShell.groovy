package org.gerber.economize.springshell

import jline.ConsoleReader

import org.gerber.economize.service.HbciService
import org.gerber.economize.service.HbciServiceCallback;
import org.gerber.economize.service.dto.AccountDTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliAvailabilityIndicator
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component

@Component
class EconomizeShell implements CommandMarker, HbciServiceCallback {
	@Autowired
	private HbciService hbciService
	
	@CliAvailabilityIndicator(["bank getAccounts"])
	public boolean isCommandAvailable() {
	  return true;
	}
  
	@CliCommand(value = "bank getAccounts", help = "Retrieves all accounts from a bank")
	public String getAccounts (
	  @CliOption(key = [ "bank" ], mandatory = true, help = "name of the bank") final String message) {
	  
	  List<AccountDTO> acountList = this.hbciService.getAccountsFromBank(null, this);
	  return "bank: " + message;
	}

	@Override
	public String getPassword() {
		ConsoleReader reader = new ConsoleReader();
		String password = reader.readLine('Password: ', '*'.toCharacter())
	}

	@Override
	public String getUserid() {
		ConsoleReader reader = new ConsoleReader();
		String userId = reader.readLine('Benutzerkennung: ')
		return userId
	}
}
