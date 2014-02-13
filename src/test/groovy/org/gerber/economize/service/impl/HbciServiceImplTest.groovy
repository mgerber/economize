/**
 * 
 */
package org.gerber.economize.service.impl;

import static org.junit.Assert.*;

import org.gerber.economize.configuration.SpringConfiguration;
import org.gerber.economize.service.HbciService
import org.gerber.economize.service.dto.AccountDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kapott.hbci.callback.AbstractHBCICallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import spock.lang.*
/**
 * @author Michael Gerber
 *
 */
@ContextConfiguration(classes=SpringConfiguration.class)
class HbciServiceImplTest extends Specification {

	@Autowired
	private HbciService hbciService

	def hbciCallbackFactoryMock = GroovyMock(HBCICallbackFactory)
	def hbciHandlerFactoryMock = GroovyMock(HBCIHandlerFactory)
	def hbciCallbackMock = GroovyMock(AbstractHBCICallback)

	def setup() {
		this.hbciCallbackFactoryMock.createHBCICallback(_) >> this.hbciCallbackMock
	}

	/**
	 * Test method for {@link org.gerber.economize.service.impl.HbciServiceImpl#getAccountsFromBank(org.gerber.economize.service.dto.BankDTO)}.
	 */
	void testGetAccountsFromBank() {
		//setup:
		//this.hbciCallbackFactoryMock.createHBCICallback(_) >> this.hbciCallback
		
		when:
		this.hbciService.getAccountsFromBank(null)
		
		then:
		null
		/*
		AccountDTO[] accountDTOs = this.hbciService.getAccountsFromBank(bank);
		assertEquals(accountDTOs, this.accounts);*/
	}

	/**
	 * Test method for {@link org.gerber.economize.service.impl.HbciServiceImpl#getTransactionsByAccount(org.gerber.economize.service.dto.AccountDTO)}.
	 */
	@Test
	public final void testGetTransactionsByAccount() {
		fail("Not yet implemented"); // TODO
	}
}
