/**
 *
 */
package org.gerber.economize.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author Michael Gerber
 *
 */
@Entity
class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

    private String bankCode

    private String name

	private String location
	
	private String bic
	
	private String crc
	
    private String hbciHost

	private String pinTanURL

	private String hbciVersion

	private String pinTanVersion

    private String port

    private String country


    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bankCode='" + bankCode + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", BIC='" + bic + '\'' +
                ", crc='" + crc + '\'' +
                ", hbciHost='" + hbciHost + '\'' +
                ", pinTanURL='" + pinTanURL + '\'' +
                ", hbciVersion='" + hbciVersion + '\'' +
                ", pinTanVersion='" + pinTanVersion + '\'' +
                ", port='" + port + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
