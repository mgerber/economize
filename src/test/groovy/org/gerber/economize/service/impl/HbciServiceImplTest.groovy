/**
 * 
 */
package org.gerber.economize.service.impl;

import static org.junit.Assert.*

import org.gerber.economize.hbci4j.wrapper.HBCIJobWrapper
import org.gerber.economize.hbci4j.wrapper.HBCIPassportWrapper
import org.gerber.economize.hbci4j.wrapper.impl.GVRKUmsWrapper
import org.gerber.economize.hbci4j.wrapper.impl.HBCIHandlerWrapper
import org.gerber.economize.hbci4j.wrapper.impl.HBCIUtilsWrapper
import org.gerber.economize.hbci4j.wrapper.impl.KontoWrapper
import org.gerber.economize.service.HbciServiceCallback;
import org.gerber.economize.service.dto.AccountDTO
import org.gerber.economize.service.dto.BankDTO
import org.junit.Test
import org.kapott.hbci.callback.HBCICallback

import spock.lang.*
/**
 * @author Michael Gerber
 *
 */
class HbciServiceImplTest extends Specification {

	private HbciServiceImpl hbciService = new HbciServiceImpl()

	/*
	 * Java Mocks.
	 */
	def hbciUtilsWrapperMock = Mock(HBCIUtilsWrapper)
	def hbciHandlerWrapperMock = Mock(HBCIHandlerWrapper)

	/*
	 * Groovy Mocks.
	 */
	def hbciCallbackFactoryMock = GroovyMock(HBCICallbackFactory)
	def hbciPassportWrapperFactoryMock = GroovyMock(HBCIPassportWrapperFactory)
	def hbciHandlerWrapperFactoryMock = GroovyMock(HBCIHandlerWrapperFactory)
	def kontoWrapperFactoryMock = GroovyMock(KontoWrapperFactory)
	
	def hbciCallbackMock = GroovyMock(HBCICallback)
	def hbciPassportWrapperMock = GroovyMock(HBCIPassportWrapper)

	def bankDTOMock = GroovyMock(BankDTO)
	def hbciServiceCallbackMock = GroovyMock(HbciServiceCallback)
	def accountDTOMock = GroovyMock(AccountDTO)
	def kontoWrapperMock = Mock(KontoWrapper)
	def hbciJobWrapperMock = Mock(HBCIJobWrapper)
	def gvrkUmsWrapperMock = Mock(GVRKUmsWrapper)

	/*
	 * test data
	 */
	def KontoWrapper[] kontoWrapperArray

	def kontoWrapper0 = Mock(KontoWrapper)
	def kontoWrapper1 = Mock(KontoWrapper)
	def kontoWrapper2 = Mock(KontoWrapper)
	
	def setup() {
		this.hbciService.hbciCallbackFactory = this.hbciCallbackFactoryMock
		this.hbciService.hbciUtilsWrapper = this.hbciUtilsWrapperMock
		this.hbciService.hbciPassportWrapperFactory = this.hbciPassportWrapperFactoryMock
		this.hbciService.hbciHandlerWrapperFactory = this.hbciHandlerWrapperFactoryMock
		this.hbciService.kontoWrapperFactory = this.kontoWrapperFactoryMock
		
		this.kontoWrapperArray = new KontoWrapper[3]
		this.kontoWrapperArray[0] = this.kontoWrapper0
		this.kontoWrapperArray[1] = this.kontoWrapper1
		this.kontoWrapperArray[2] = this.kontoWrapper2		
	}

	def "test that getAccountsFromBank calls all methods in right order"() {
		when:
		this.hbciService.getAccountsFromBank(this.bankDTOMock, this.hbciServiceCallbackMock)
		
		then: "ensure that initialization is done"
		1 * this.hbciUtilsWrapperMock.init(_, _)
		then:
		1 * this.hbciPassportWrapperFactoryMock.createHBCIPassportWrapper(_, _) >> this.hbciPassportWrapperMock
		then:
		1 * this.hbciHandlerWrapperFactoryMock.createHBCIHandlerWrapper(_, _) >> this.hbciHandlerWrapperMock
		then:
		1 * this.hbciPassportWrapperMock.getAccounts() >> this.kontoWrapperArray
		then:
		1 * this.hbciHandlerWrapperMock.close()
	}

	def "test that GetAccountsFromBank returns the right values"() {
		setup:
		this.hbciPassportWrapperFactoryMock.createHBCIPassportWrapper(_, _) >> this.hbciPassportWrapperMock
		this.hbciHandlerWrapperFactoryMock.createHBCIHandlerWrapper(_, _) >> this.hbciHandlerWrapperMock
		this.hbciPassportWrapperMock.getAccounts() >> this.kontoWrapperArray
		
		expect:
		this.hbciService.getAccountsFromBank(this.bankDTOMock, this.hbciServiceCallbackMock) != null
		this.hbciService.getAccountsFromBank(null, null).size() == 0
		this.hbciService.getAccountsFromBank(this.bankDTOMock, this.hbciServiceCallbackMock).size() == 3
	}

	def "test that getTransactionsByAccount calls all methods in right order"() {
		when:
		this.hbciService.getTransactionsByAccount(this.bankDTOMock, this.accountDTOMock, hbciServiceCallbackMock)
		
		then: "ensure that initialization is done"
		1 * this.hbciUtilsWrapperMock.init(_, _)
		then: "ensure that a passport is created"
		1 * this.hbciPassportWrapperFactoryMock.createHBCIPassportWrapper(_, _) >> this.hbciPassportWrapperMock
		then:
		1 * this.hbciHandlerWrapperFactoryMock.createHBCIHandlerWrapper(_, _) >> this.hbciHandlerWrapperMock
		then:
		1 * this.kontoWrapperFactoryMock.createKonto() >> this.kontoWrapperMock
		then: "ensure that a new job is created by calling jobFactory"
		1 * this.hbciHandlerWrapperMock.newJob(_) >> this.hbciJobWrapperMock
		then: "ensure that required methods are called on hbciJob"
		1 * this.hbciJobWrapperMock.setParam(_, this.kontoWrapperMock);
		1 * this.hbciJobWrapperMock.addToQueue();
		then: "ensure that we call the bank"
		1 * this.hbciHandlerWrapperMock.execute()
		then: "ensure that we get the data from the hbciJob"
		1 * this.hbciJobWrapperMock.getJobResult() >> this.gvrkUmsWrapperMock
		then: "ensure that we test if the request ended without any errors"
		1 * this.gvrkUmsWrapperMock.isOK() >> true
		then:
		1 * this.hbciHandlerWrapperMock.close()
	}
	
	def "test that getTransactionsByAccount returns the right values"() {
		setup:
		this.hbciPassportWrapperFactoryMock.createHBCIPassportWrapper(_, _) >> this.hbciPassportWrapperMock
		this.hbciHandlerWrapperFactoryMock.createHBCIHandlerWrapper(_, _) >> this.hbciHandlerWrapperMock
		this.kontoWrapperFactoryMock.createKonto() >> this.kontoWrapperMock
		this.hbciHandlerWrapperMock.newJob(_) >> this.hbciJobWrapperMock
		this.hbciJobWrapperMock.getJobResult() >> this.gvrkUmsWrapperMock
		this.gvrkUmsWrapperMock.isOK() >> true
		
		expect:
		this.hbciService.getTransactionsByAccount(this.bankDTOMock, this.accountDTOMock, this.hbciServiceCallbackMock) != null
	}
}
