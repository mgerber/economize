/**
 * 
 */
package org.gerber.economize.service.impl

import org.gerber.economize.hbci4j.wrapper.impl.HbciCallback;
import org.gerber.economize.service.HbciServiceCallback;
import org.gerber.economize.service.dto.BankDTO
import org.kapott.hbci.callback.AbstractHBCICallback
import org.springframework.stereotype.Component

/**
 * @author Michael Gerber
 *
 */
@Component
class HBCICallbackFactory {
	public AbstractHBCICallback createHBCICallback(final BankDTO bank, final HbciServiceCallback hbciServiceCallback) {
		def hbciCallback = new HbciCallback(bank, hbciServiceCallback)
		return hbciCallback
	}
}
