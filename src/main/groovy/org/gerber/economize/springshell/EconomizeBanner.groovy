/**
 * 
 */
package org.gerber.economize.springshell

import org.springframework.shell.plugin.BannerProvider
import org.springframework.shell.plugin.support.DefaultBannerProvider
import org.springframework.shell.support.util.OsUtils
import org.springframework.stereotype.Component;

/**
 * @author Michael Gerber
 *
 */
@Component
class EconomizeBanner extends DefaultBannerProvider implements BannerProvider {

	/* (non-Javadoc)
	 * @see org.springframework.shell.plugin.PluginProvider#name()
	 */
	@Override
	public String name() {
		return "Economize"
	}

	/* (non-Javadoc)
	 * @see org.springframework.shell.plugin.BannerProvider#getBanner()
	 */
	@Override
	public String getBanner() {
		StringBuffer buf = new StringBuffer();
		buf.append('    ______                                       _           '  + OsUtils.LINE_SEPARATOR);
		buf.append('   / ____/_____ ____   ____   ____   ____ ___   (_)____  ___ '  + OsUtils.LINE_SEPARATOR);
		buf.append('  / __/  / ___// __ \\ / __ \\ / __ \\ / __ \\`__ \\ / //_  / / _ \\'  + OsUtils.LINE_SEPARATOR);
		buf.append(' / /___ / /__ / /_/ // / / // /_/ // / / / / // /  / /_/  __/'  + OsUtils.LINE_SEPARATOR);
		buf.append('/_____/ \\___/ \\____//_/ /_/ \\____//_/ /_/ /_//_/  /___/\\___/ '  + OsUtils.LINE_SEPARATOR);
		buf.append('                                                             '  + OsUtils.LINE_SEPARATOR)
		return buf.toString();
	}
			
	/* (non-Javadoc)
	 * @see org.springframework.shell.plugin.BannerProvider#getVersion()
	 */
	@Override
	public String getVersion() {
		return "0.0.1"
	}

	/* (non-Javadoc)
	 * @see org.springframework.shell.plugin.BannerProvider#getWelcomeMessage()
	 */
	@Override
	public String getWelcomeMessage() {
			return "Welcome to Economize CLI"
	}

}
