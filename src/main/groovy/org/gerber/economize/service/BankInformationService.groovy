/**
 *
 */
package org.gerber.economize.service

import java.util.List;

import org.gerber.economize.service.dto.BankDTO;

/**
 * @author Mike Großmann
 *
 */
public interface BankInformationService {
    String getBankName(String blz)
    BankDTO createBank(String bankName, String blz, String country, String hbciVersion, String host, String port)
	List<BankDTO> getSavedBanks()
	BankDTO getBankByID(Long id)
}