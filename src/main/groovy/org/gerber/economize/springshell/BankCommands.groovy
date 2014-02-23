/**
 * 
 */
package org.gerber.economize.springshell

import jline.ConsoleReader
import org.gerber.economize.domain.Bank
import org.gerber.economize.service.BankInformationService
import org.gerber.economize.service.HbciService
import org.gerber.economize.service.HbciServiceCallback
import org.gerber.economize.service.dto.AccountDTO
import org.gerber.economize.service.dto.BankDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliAvailabilityIndicator
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.shell.support.util.OsUtils
import org.springframework.stereotype.Component

/**
 * @author Michael Gerber
 *
 */
@Component
class BankCommands implements CommandMarker, HbciServiceCallback {
	@Autowired
	private BankInformationService bankInformationService
	@Autowired
	private HbciService hbciService

	@CliAvailabilityIndicator(["bank createBank"])
	public boolean isCommandAvailable() {
	  return true;
	}
  
	@CliCommand(value = "bank createBank", help = "Erzeugt eine neue Bank in der Datenbank")
	public String createBank (
		@CliOption(key = [ "bankName" ], mandatory = true, help = "Name der Bank") final String bankName,
		@CliOption(key = [ "blz" ], mandatory = true, help = "Bankleitzahl") final String blz) {
		ConsoleReader reader = new ConsoleReader()
		String host = reader.readLine('Host: ')
		String port = reader.readLine('Port: ')
		BankDTO bank = this.bankInformationService.createBank(bankName, blz, 'DE', host, port)
		return "";
	}

	@CliCommand(value = "bank showSavedBanks", help = "Zeigt alle gespeicherten Banken")
	public String showSavedBanks() {
		List<BankDTO> list = this.bankInformationService.getSavedBanks()
		StringBuffer buf = new StringBuffer()
		list.each {
			buf.append('ID=' + it.id)
			buf.append(' Name=' + it.name)
			buf.append(' BLZ=' + it.bankCode)
			buf.append(OsUtils.LINE_SEPARATOR)
		}
		return buf.toString()
	}
	
	@CliCommand(value = "bank showAccounts", help = "Ruft Konten von einer Bank ab")
	public String showOnlineAccountsByBank(
		@CliOption(key = [ "bankId" ], mandatory = true, help = "Bank ID") final Long bankId) {
		BankDTO bankDTO = this.bankInformationService.getBankByID(bankId);
		List<AccountDTO> accountDTOList = this.hbciService.getAccountsFromBank(bankDTO, this)
		StringBuffer buf = new StringBuffer()
		accountDTOList.each {
			buf.append(it.accountNumber)
			buf.append(OsUtils.LINE_SEPARATOR)
		}
		return buf.toString()
	}
	
	@Override
	public String getPassword() {
		ConsoleReader reader = new ConsoleReader();
		String password = reader.readLine('Password: ', '*'.toCharacter())
	}
}
