/**
 *
 */
package org.gerber.economize.service.impl

import org.gerber.economize.domain.Account
import org.gerber.economize.repositories.AccountInformationRepository
import org.gerber.economize.repositories.BankInformationRepository
import org.gerber.economize.service.AccountInformationService;
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
    Account createAccount(final String bankCode, final String knr, final String iban, final String tanMedia) {

        def bankFound = bankInformationRepository.findByBankCode(bankCode)

        LOGGER.info '{} found for {}', bankFound, bankCode

        def accountCreated = new Account(bank: bankFound, accountNumber: knr, iban: iban, tanMedia: tanMedia)

        LOGGER.info '{} created', accountCreated

        def accountSaved = accountInformationRepository.save(accountCreated)

        LOGGER.info '{} saved', accountSaved
    }
}
