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
class Bank implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

    private String name

    private String host

    private String port

    private String blz

    private String country


    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blz='" + blz + '\'' +
                ", country='" + country + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
