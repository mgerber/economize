/**
 *
 */
package org.gerber.economize.service.impl

import org.gerber.economize.domain.Bank
import org.gerber.economize.repositories.BankInformationRepository
import org.gerber.economize.service.BankInformationService;
import org.gerber.economize.service.dto.BankDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Mike Großmann
 *
 */
@Service
@Transactional(readOnly = true)
class BankInformationServiceImpl implements BankInformationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankInformationServiceImpl)

    @Autowired
    private BankInformationRepository bankInformationRepository

    @Override
    String getBankName(String bankCode) {

        def bankFound = bankInformationRepository.findByBankCode(bankCode)

        LOGGER.info '{} found for {}', bankFound, bankCode

        return bankFound?.name
    }

    @Override
    BankDTO createBank(String bankName, String bankCode, String country, String host, String port) {

        def bankCreated = new Bank()

        bankCreated.name     = bankName
        bankCreated.bankCode = bankCode
        bankCreated.country  = country
        bankCreated.host     = host
        bankCreated.port     = port

        LOGGER.info '{} created', bankCreated

        def bankSaved = bankInformationRepository.save(bankCreated)

        LOGGER.info '{} saved', bankSaved
		return new BankDTO(bankSaved)
    }
	
	List<BankDTO> getSavedBanks() {
		List<BankDTO> bankList = new ArrayList()
		this.bankInformationRepository.findAll().each {
			BankDTO bankDTO = new BankDTO(it)
			bankList.add(bankDTO)
			//bankDTO.hbciVersion = it.hbciVersion
		}
		return bankList
	}

	@Override
	public BankDTO getBankByID(final Long id) {
		Bank bank = this.bankInformationRepository.findOne(id)
		BankDTO bankDTO = new BankDTO(bank)
		return bankDTO;
	}
}
