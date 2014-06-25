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
		@CliOption(key = [ "blz" ], mandatory = true, help = "Bankleitzahl") final String blz) {

        BankDTO defaultData = this.hbciService.getBankByCode(blz)

		ConsoleReader reader = new ConsoleReader()
		String name = reader.readLine('Name [' + defaultData.name + ']:')
		String location = reader.readLine('Ort [' + defaultData.location + ']:')
		String bic = reader.readLine('BIC [' + defaultData.bic + ']:')
		String crc = reader.readLine('crc-Code [' + defaultData.crc + ']:')
		String hbciHost = reader.readLine('HBCI-Host [' + defaultData.hbciHost + ']:')
		String pinTanURL = reader.readLine('PinTan-URL [' + defaultData.pinTanURL + ']:')
		String hbciVersion = reader.readLine('HBCI-Version [' + defaultData.hbciVersion + ']:')
		String pinTanVersion = reader.readLine('PinTanVersion [' + defaultData.pinTanVersion + ']:')
		String port = reader.readLine('Port [' + defaultData.port + ']:')
		String country = reader.readLine('Land [' + defaultData.country + ']:')

		if (!name.empty) defaultData.name = name
		if (!location.empty) defaultData.location = location
		if (!bic.empty) defaultData.bic = bic
		if (!crc.empty) defaultData.crc = crc
		
		if (!hbciHost.empty) defaultData.hbciHost = hbciHost
		if (!pinTanURL.empty) defaultData.pinTanURL = pinTanURL
		if (!hbciVersion.empty) defaultData.hbciVersion = hbciVersion
		if (!pinTanVersion.empty) defaultData.pinTanVersion = pinTanVersion
		if (!port.empty) defaultData.port = port
		if (!country.empty) defaultData.country = country
		
		BankDTO bank = this.bankInformationService.createBank(defaultData)

		return bank;
	}

	@CliCommand(value = "bank changeBank", help = "Aendert eine gespeicherte Bank")
	public String changeBank (
		@CliOption(key = [ "bankID" ], mandatory = true, help = "Bank ID") final Long bankId) {

        BankDTO origBankData = this.bankInformationService.getBankByID(bankId)

		ConsoleReader reader = new ConsoleReader()
		String name = reader.readLine('Name [' + origBankData.name + ']:')
		String location = reader.readLine('Ort [' + origBankData.location + ']:')
		String bic = reader.readLine('BIC [' + origBankData.bic + ']:')
		String crc = reader.readLine('crc-Code [' + origBankData.crc + ']:')
		String hbciHost = reader.readLine('HBCI-Host [' + origBankData.hbciHost + ']:')
		String pinTanURL = reader.readLine('PinTan-URL [' + origBankData.pinTanURL + ']:')
		String hbciVersion = reader.readLine('HBCI-Version [' + origBankData.hbciVersion + ']:')
		String pinTanVersion = reader.readLine('PinTanVersion [' + origBankData.pinTanVersion + ']:')
		String port = reader.readLine('Port [' + origBankData.port + ']:')
		String country = reader.readLine('Land [' + origBankData.country + ']:')

		if (!name.empty) origBankData.name = name
		if (!location.empty) origBankData.location = location
		if (!bic.empty) origBankData.bic = bic
		if (!crc.empty) origBankData.crc = crc
		
		if (!hbciHost.empty) origBankData.hbciHost = hbciHost
		if (!pinTanURL.empty) origBankData.pinTanURL = pinTanURL
		if (!hbciVersion.empty) origBankData.hbciVersion = hbciVersion
		if (!pinTanVersion.empty) origBankData.pinTanVersion = pinTanVersion
		if (!port.empty) origBankData.port = port
		if (!country.empty) origBankData.country = country
		
		BankDTO bank = this.bankInformationService.saveBank(origBankData)

		return bank;
	}

	@CliCommand(value = "bank showAllSavedBanks", help = "Zeigt alle gespeicherten Banken")
	public String showAllSavedBanks() {
		List<BankDTO> list = this.bankInformationService.getSavedBanks()
		StringBuffer buf = new StringBuffer()
		list.each {
			buf.append('ID=' + it.id)
			buf.append(' Name=' + it.name)
			buf.append(' BLZ=' + it.bankCode)
			buf.append(' BIC=' + it.bic)
			buf.append(OsUtils.LINE_SEPARATOR)
		}
		return buf.toString()
	}
	
	@CliCommand(value = "bank showOneSavedBank", help = "Zeigt die Daten einer gespeicherten Bank")
	public String showOneSavedBanks(
		@CliOption(key = [ "bankID" ], mandatory = true, help = "Bank ID") final Long bankId) {
		BankDTO list = this.bankInformationService.getBankByID(bankId)
		StringBuffer buf = new StringBuffer()
		buf.append('ID=' + list.id)
		buf.append(' Name=' + list.name)
		buf.append(' BLZ=' + list.bankCode)
		buf.append(' BIC=' + list.bic)
		buf.append(' HBCI-Host=' + list.hbciHost)
		buf.append(' HBCI-Version=' + list.hbciVersion)
		buf.append(OsUtils.LINE_SEPARATOR)
		return buf.toString()
	}
	
	@CliCommand(value = "bank showAccounts", help = "Ruft Konten von einer Bank ab")
	public String showOnlineAccountsByBank(
		@CliOption(key = [ "bankID" ], mandatory = true, help = "Bank ID") final Long bankId) {
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
		String answer = reader.readLine('Soll ich das Passwort speichern (J/N)? [N]:')
		if (answer.equalsIgnoreCase('J')) {
			answer = reader.readLine('Bist du dir absolut sicher (J/N)? [N]:')
			if (answer.equalsIgnoreCase('J')) {
				reader.readLine('Vergiss es! Ich speichere kein Passwort!')
			}
		}
		return password
	}

	@Override
	public String getUserid() {
		ConsoleReader reader = new ConsoleReader();
		String userId = reader.readLine('Benutzerkennung: ')
		return userId
	}
}
