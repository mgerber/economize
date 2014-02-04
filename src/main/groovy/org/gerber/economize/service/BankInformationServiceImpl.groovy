/**
 *
 */
package org.gerber.economize.service

import org.gerber.economize.domain.Bank
import org.gerber.economize.repositories.BankInformationRepository
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
class BankInformationServiceImpl implements BankInformationService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BankInformationServiceImpl)

    @Autowired
    private BankInformationRepository bankInformationRepository

    @Override
    String getBankName(String blz) {

        def bankFound = bankInformationRepository.findByBlz(blz)

        LOGGER.info '{} found for {}', bankFound, blz

        return bankFound?.name
    }

    @Override
    Bank createBank(String bankName, String blz, String country, String host, String port) {

        def bankCreated = new Bank()

        bankCreated.name = bankName
        bankCreated.blz      = blz
        bankCreated.country  = country
        bankCreated.host     = host
        bankCreated.port     = port

        LOGGER.info '{} created', bankCreated

        def bankSaved = bankInformationRepository.save(bankCreated)

        LOGGER.info '{} saved', bankSaved
    }
}
