/**
 * 
 */
package org.gerber.economize.service.impl;

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.gerber.economize.configuration.*
import org.gerber.economize.service.dto.AccountDTO
import org.gerber.economize.service.dto.BankDTO
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.kapott.hbci.callback.AbstractHBCICallback
import org.kapott.hbci.manager.HBCIHandler
import org.kapott.hbci.manager.HBCIUtils
import org.kapott.hbci.passport.AbstractHBCIPassport
import org.kapott.hbci.passport.HBCIPassport
import org.kapott.hbci.structures.Konto
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader

/**
 * @author Michael Gerber
 *
 */
@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes=SpringConfiguration.class, loader=AnnotationConfigContextLoader.class)
@PrepareForTest( [HBCIUtils.class, AbstractHBCIPassport.class, HBCIHandler.class] )
class HbciServiceImplTest {


	@InjectMocks
	private HbciServiceImpl hbciService
	
	@Mock
	private Properties defaultHbciProperties
	@Mock
	private HBCIHandlerFactory hbciHandlerFactory
	@Mock
	private HBCICallbackFactory hbciCallbackFactory
	@Mock
	private HBCIPassport passportMock
	@Mock
	private BankDTO bank
	@Mock
	private AbstractHBCICallback hbciCallback

	private Konto[] accounts = new Konto[4];

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//static mocks
		PowerMockito.mockStatic(HBCIUtils.class)
		PowerMockito.mockStatic(AbstractHBCIPassport.class)
		PowerMockito.mockStatic(HBCIHandler.class)
		//static stubbing
		PowerMockito.when(AbstractHBCIPassport.getInstance("PinTan", "Passport")).thenReturn(this.passportMock);
		PowerMockito.when(this.passportMock.getHBCIVersion()).thenReturn("plus");
		PowerMockito.when(this.passportMock.getAccounts()).thenReturn(this.accounts);
		//stubbing
		when(this.hbciCallbackFactory.createHBCICallback(null)).thenReturn(null)
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.gerber.economize.service.impl.HbciServiceImpl#getAccountsFromBank(org.gerber.economize.service.dto.BankDTO)}.
	 */
	@Test
	public final void testGetAccountsFromBank() {
		AccountDTO[] accountDTOs = this.hbciService.getAccountsFromBank(bank);
		assertEquals(accountDTOs, this.accounts);
	}

	/**
	 * Test method for {@link org.gerber.economize.service.impl.HbciServiceImpl#getTransactionsByAccount(org.gerber.economize.service.dto.AccountDTO)}.
	 */
	@Test
	public final void testGetTransactionsByAccount() {
		fail("Not yet implemented"); // TODO
	}

}
