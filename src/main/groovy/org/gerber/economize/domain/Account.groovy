/**
 *
 *
 */
package org.gerber.economize.domain

import javax.persistence.*

/**
 * @author Michael Gerber
 *
 */
@Entity
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Bank bank

    private String accountNumber

    private String iban


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", bank=" + bank +
                ", accountNumber='" + accountNumber + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
