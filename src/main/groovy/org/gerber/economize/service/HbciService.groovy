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
	AccountDTO[] getAccountsFromBank(BankDTO bank)
	TransactionDTO[] getTransactionsByAccount(AccountDTO account)
}
