/**
 * 
 */
package org.gerber.economize.service.impl

import org.gerber.economize.hbci4j.wrapper.HBCIPassportWrapper
import org.kapott.hbci.passport.AbstractHBCIPassport
import org.kapott.hbci.passport.HBCIPassport
import org.springframework.stereotype.Component

/**
 * @author Michael Gerber
 *
 */
@Component
class HBCIPassportWrapperFactory {
	public HBCIPassport createHBCIPassportWrapper(final String name, final Object init) {
		return AbstractHBCIPassport.getInstance(name, init);
	}
}
