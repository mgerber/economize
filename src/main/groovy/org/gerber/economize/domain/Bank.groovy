/**
 * 
 */
package org.gerber.economize.domainimport javax.persistence.Embeddable;
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id



/**
 * @author Michael Gerber
 *
 */
@Entity
@Embeddable
class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bankId;
	private String bankName;
	private String host;
	private String port;
	private String blz;
	private String country

}
