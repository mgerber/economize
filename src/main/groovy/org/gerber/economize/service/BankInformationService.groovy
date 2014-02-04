/**
 *
 */
package org.gerber.economize.service

import org.gerber.economize.domain.Bank

/**
 * @author Mike Großmann
 *
 */
public interface BankInformationService {
    String getBankName(String blz)
    Bank createBank(String bankName, String blz, String country, String host, String port)
}