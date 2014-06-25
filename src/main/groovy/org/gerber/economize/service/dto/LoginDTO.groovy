/**
 * 
 */
package org.gerber.economize.service.dto

import org.gerber.economize.domain.Bank;
import org.gerber.economize.domain.Login

/**
 * @author Michael Gerber
 *
 */
class LoginDTO {
	
	public LoginDTO() {
	}
	
	public LoginDTO(Login login) {
		this.bank = login.bank
		this.userName = login.userName
		this.tanMedia = login.tanMedia
	}
	
	private Bank bank
	
	private String userName
	
	private String tanMedia
}
