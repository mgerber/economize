/**
 * 
 */
package org.gerber.economize.service.impl

import org.gerber.economize.hbci4j.wrapper.impl.HBCIHandlerWrapper
import org.kapott.hbci.passport.HBCIPassport
import org.springframework.stereotype.Component;

/**
 * @author Michael Gerber
 *
 */
@Component
class HBCIHandlerWrapperFactory {
	public HBCIHandlerWrapper createHBCIHandlerWrapper(final String hbciversion, final HBCIPassport passport) {
		return new HBCIHandlerWrapper(hbciversion, passport);
	}
}
