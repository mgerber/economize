/**
 * 
 */
package org.gerber.economize.springshell

import org.springframework.shell.plugin.PromptProvider
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

/**
 * @author Michael Gerber
 *
 */
@Component
class EconomizePrompt extends DefaultPromptProvider implements PromptProvider {
	@Override
	public String getPrompt() {
		return "economize>";
	}
	
	
	@Override
	public String getProviderName() {
		return "Economize prompt provider";
	}
}
