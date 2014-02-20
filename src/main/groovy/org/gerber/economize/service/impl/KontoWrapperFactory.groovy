/**
 * 
 */
package org.gerber.economize.service.impl

import org.gerber.economize.hbci4j.wrapper.impl.KontoWrapper
import org.springframework.stereotype.Component

/**
 * @author Michael Gerber
 *
 */
@Component
class KontoWrapperFactory {
	public KontoWrapper createKonto() {
		return new KontoWrapper();
	}
}
