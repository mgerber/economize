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
    BankDTO createBank(BankDTO bankDTO)
    BankDTO saveBank(BankDTO bankDTO)
	List<BankDTO> getSavedBanks()
	BankDTO getBankByID(Long id)
}