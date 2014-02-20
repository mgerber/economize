/**
 * 
 */
package org.gerber.economize.view

import javafx.scene.Node;
import javafx.scene.control.TreeItem

/**
 * @author Michael Gerber
 *
 */
class NavigationTreeItem extends TreeItem {
	private final String targetView
	/**
	 * @param arg0
	 */
	public NavigationTreeItem(String arg0, String targetView) {
		super(arg0);
		this.targetView = targetView
	}

}
