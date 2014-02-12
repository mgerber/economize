/**
 * 
 */
package org.gerber.economize.service.impl

import org.kapott.hbci.manager.HBCIHandler
import org.kapott.hbci.passport.HBCIPassport
import org.springframework.stereotype.Component;

/**
 * @author Michael Gerber
 *
 */
@Component
class HBCIHandlerFactory {
	public HBCIHandler createHBCIHandler(final String hbciVersion, final HBCIPassport hbciPassport) {
		def hbciHandle = new HBCIHandler(hbciVersion, hbciPassport)
		return hbciHandle
	}
}
