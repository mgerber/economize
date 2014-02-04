/**
 *
 */
package org.gerber.economize.service

import org.gerber.economize.domain.Account
import org.gerber.economize.repositories.AccountInformationRepository
import org.gerber.economize.repositories.BankInformationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mike Großmann
 *
 */
@Service
@Transactional(readOnly = true)
class AccountInformationServiceImpl implements AccountInformationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInformationServiceImpl)

    @Autowired
    private BankInformationRepository bankInformationRepository

    @Autowired
    AccountInformationRepository accountInformationRepository

    @Override
    @Transactional(readOnly = false)
    Account createAccount(final String blz, final String knr, final String iban) {

        def bankFound = bankInformationRepository.findByBlz(blz)

        LOGGER.info '{} found for {}', bankFound, blz

        def accountCreated = new Account(bank: bankFound, accountNumber: knr, iban: iban)

        LOGGER.info '{} created', accountCreated

        def accountSaved = accountInformationRepository.save(accountCreated)

        LOGGER.info '{} saved', accountSaved
    }
}
