/**
 * 
 */
package org.gerber.economize.service.impl

import javax.annotation.Resource

import org.gerber.economize.service.HbciService
import org.gerber.economize.service.dto.AccountDTO
import org.gerber.economize.service.dto.BankDTO
import org.gerber.economize.service.dto.TransactionDTO
import org.kapott.hbci.manager.HBCIHandler
import org.kapott.hbci.passport.HBCIPassport
import org.kapott.hbci.structures.Konto
import org.kapott.hbci.manager.HBCIHandler
import org.kapott.hbci.manager.HBCIUtils
import org.kapott.hbci.passport.AbstractHBCIPassport
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service

/**
 * @author Michael Gerber
 *
 */
@Service
class HbciServiceImpl implements HbciService {
	
	@Resource(name='DefaultHbciProperties')
	private Properties defaultHbciProperties

	@Autowired
	private HBCIHandlerFactory hbciHandlerFactory
	
	@Autowired
	private HBCICallbackFactory hbciCallbackFactory
	
	/* (non-Javadoc)
	 * @see org.gerber.economize.service.HbciService#getAccountsFromBank(org.gerber.economize.service.dto.BankDTO)
	 */
	@Override
	public AccountDTO[] getAccountsFromBank(BankDTO bank) {
		def hbciCallback = this.hbciCallbackFactory.createHBCICallback(bank);
		HBCIUtils.init(this.defaultHbciProperties, hbciCallback);
		String passportDescription="Passport";
		HBCIPassport passport=AbstractHBCIPassport.getInstance("PinTan", passportDescription);
		String version=passport.getHBCIVersion();
		def hbciHandle = hbciHandlerFactory.createHBCIHandler((version.length() != 0) ? version : "plus", passport);

		Konto[] kontos = passport.getAccounts();

		return null;
	}

	/* (non-Javadoc)
	 * @see org.gerber.economize.service.HbciService#getTransactionsByAccount(org.gerber.economize.service.dto.AccountDTO)
	 */
	@Override
	public TransactionDTO[] getTransactionsByAccount(AccountDTO account) {
		// TODO Auto-generated method stub
		return null;
	}

}
