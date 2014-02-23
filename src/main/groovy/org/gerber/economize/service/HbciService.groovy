/**
 * 
 */
package org.gerber.economize.service

import org.gerber.economize.service.dto.AccountDTO;
import org.gerber.economize.service.dto.BankDTO
import org.gerber.economize.service.dto.TransactionDTO

/**
 * @author Michael Gerber
 *
 */
interface HbciService {
	List<AccountDTO> getAccountsFromBank(BankDTO bankDTO, HbciServiceCallback hbciServiceCallback)
	List<TransactionDTO> getTransactionsByAccount(BankDTO bankDTO, AccountDTO accountDTO, HbciServiceCallback hbciServiceCallback)
}
