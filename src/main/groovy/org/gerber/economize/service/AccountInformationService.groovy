/**
 *
 */
package org.gerber.economize.service

import org.gerber.economize.domain.Account

/**
 * @author Mike Großmann
 *
 */
interface AccountInformationService {
    Account createAccount(String blz, String knr, String iban)
}
