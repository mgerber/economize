/**
 * 
 */
package org.gerber.economize.domain

import javax.persistence.*

/**
 * @author Michael Gerber
 *
 */
class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Bank bank

	private String userName

	private String tanMedia
	
		@Override
		public String toString() {
			return "Login{" +
					"id=" + id +
					", userName=" + userName +
					", bank=" + bank +
					", tanMedia='" + tanMedia +
					'\'' +
					'}';
		}
	
}
