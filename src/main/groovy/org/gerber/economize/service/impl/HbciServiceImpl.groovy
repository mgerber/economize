/**
 * 
 */
package org.gerber.economize.service.impl

import javax.annotation.Resource

import org.gerber.economize.hbci4j.wrapper.HBCIJobWrapper
import org.gerber.economize.hbci4j.wrapper.HBCIPassportWrapper
import org.gerber.economize.hbci4j.wrapper.impl.GVRKUmsWrapper
import org.gerber.economize.hbci4j.wrapper.impl.HBCIExecStatusWrapper
import org.gerber.economize.hbci4j.wrapper.impl.HBCIUtilsWrapper
import org.gerber.economize.hbci4j.wrapper.impl.KontoWrapper
import org.gerber.economize.service.HbciService
import org.gerber.economize.service.HbciServiceCallback;
import org.gerber.economize.service.dto.AccountDTO
import org.gerber.economize.service.dto.BankDTO
import org.gerber.economize.service.dto.TransactionDTO
import org.kapott.hbci.GV_Result.GVRKUms.UmsLine;
import org.kapott.hbci.manager.HBCIUtilsInternal;
import org.kapott.hbci.passport.HBCIPassport
import org.kapott.hbci.structures.Konto;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Michael Gerber
 *
 */
@Service
class HbciServiceImpl implements HbciService {

    @Resource(name='bankDataMap')
    private Map<String, String> bankDataMap

	@Resource(name='DefaultHbciProperties')
	private Properties defaultHbciProperties

	@Autowired
	private HBCICallbackFactory hbciCallbackFactory
	
	@Autowired
	private KontoWrapperFactory kontoWrapperFactory
	
	@Autowired
	private HBCIUtilsWrapper hbciUtilsWrapper
	
	@Autowired
	private HBCIPassportWrapperFactory hbciPassportWrapperFactory
	
	@Autowired
	private HBCIHandlerWrapperFactory hbciHandlerWrapperFactory
	
	/* (non-Javadoc)
	 * @see org.gerber.economize.service.HbciService#getAccountsFromBank(org.gerber.economize.service.dto.BankDTO)
	 */
	@Override
	public List<AccountDTO> getAccountsFromBank(final BankDTO bankDTO, final HbciServiceCallback hbciServiceCallback) {
		def accountDTOList = new ArrayList<AccountDTO>()
		if (bankDTO != null) {
			def hbciCallback = this.hbciCallbackFactory.createHBCICallback(bankDTO, hbciServiceCallback)
			this.hbciUtilsWrapper.init(this.defaultHbciProperties, hbciCallback)
			HBCIPassport hbciPassportWrapper = this.hbciPassportWrapperFactory.createHBCIPassportWrapper("PinTan", "Passport")
 			def hbciHandlerWrapper = this.hbciHandlerWrapperFactory.createHBCIHandlerWrapper(bankDTO.pinTanVersion, hbciPassportWrapper)
	
			Konto[] kontoWrapperArray = hbciPassportWrapper.getAccounts()
			for(int ii = 0; ii < kontoWrapperArray.length; ii++) {
				def accountDTO = new AccountDTO()
				accountDTO.accountNumber = kontoWrapperArray[ii].number
				accountDTO.iban = kontoWrapperArray[ii].iban
				accountDTOList.add(accountDTO)
			}
			hbciHandlerWrapper.close()
		}
		return accountDTOList;
	}

	/* (non-Javadoc)
	 * @see org.gerber.economize.service.HbciService#getTransactionsByAccount(org.gerber.economize.service.dto.AccountDTO)
	 */
	@Override
	public List<TransactionDTO> getTransactionsByAccount(final BankDTO bankDTO, final AccountDTO accountDTO, final HbciServiceCallback hbciServiceCallback) {
		def hbciCallback = this.hbciCallbackFactory.createHBCICallback(bankDTO, hbciServiceCallback)
		this.hbciUtilsWrapper.init(this.defaultHbciProperties, hbciCallback)
		HBCIPassportWrapper hbciPassportWrapper = this.hbciPassportWrapperFactory.createHBCIPassportWrapper("PinTan", "Passport")
		def hbciHandlerWrapper = this.hbciHandlerWrapperFactory.createHBCIHandlerWrapper(bankDTO.pinTanVersion, hbciPassportWrapper)

		def kontoWrapper = this.kontoWrapperFactory.createKonto()
		kontoWrapper.iban = accountDTO.iban
		kontoWrapper.number = accountDTO.accountNumber

		HBCIJobWrapper trans = hbciHandlerWrapper.newJob("KUmsAll");
		trans.setParam("my", kontoWrapper);
		trans.addToQueue();
		HBCIExecStatusWrapper ret = hbciHandlerWrapper.execute();
		GVRKUmsWrapper result = (GVRKUmsWrapper)trans.getJobResult();

		def transactionList = new ArrayList<TransactionDTO>()
		if (result.isOK()) {
			List<UmsLine> bookedDataList = result.getFlatData()
			
			for (bookedData in bookedDataList) {
				def transactionDTO = new TransactionDTO();
				transactionList.add(transactionDTO);
			}
		}
		hbciHandlerWrapper.close()
		return transactionList;
	}

    /* (non-Javadoc)
     * @see org.gerber.economize.service.HbciService#getBankByCode(java.lang.String)
     */
    @Override
    BankDTO getBankByCode(String bankCode) {
        BankDTO bankDTO = new BankDTO()

        def bankData = this.bankDataMap.get(bankCode)?.split("\\|")
		bankDTO.bankCode		= bankCode
		bankDTO.name			= bankData?.getAt(0)
		bankDTO.location		= bankData?.getAt(1)
		bankDTO.bic      		= bankData?.getAt(2)
		bankDTO.crc				= bankData?.getAt(3)
		bankDTO.hbciHost		= bankData?.getAt(4)
		bankDTO.pinTanURL		= bankData?.getAt(5)
		bankDTO.hbciVersion		= bankData?.getAt(6)
		bankDTO.pinTanVersion	= bankData?.getAt(7)
		bankDTO.port			= "443"
		bankDTO.country			= "DE"
	
        return bankDTO
    }
}
