/**
 * 
 */
package org.gerber.economize.service

import org.gerber.economize.domain.Bank
import org.gerber.economize.domain.Login

/**
 * @author Michael Gerber
 *
 */
interface LoginInformationService {
	Login createLogin(Bank bank, String tanMedia)
	
}
