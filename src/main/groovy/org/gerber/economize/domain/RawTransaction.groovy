/**
 * 
 */
package org.gerber.economize.domain

import javax.persistence.CascadeType;
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne;

/**
 * @author Michael Gerber
 *
 */
@Entity
class RawTransaction  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Account account

	/** Datum der Wertstellung */
	private Date   valuta

	/** Buchungsdatum */
	private Date   entryDate

	/** Gebuchter Betrag */
	private long  value

	/** Währung des gebuchten Betrags */
	private String valueCurrency
	
	/** Handelt es sich um eine Storno-Buchung? */
	private boolean isCancellation
	
	/** Der Saldo <em>nach</em> dem Buchen des Betrages <code>value</code> */
	//private Saldo  accountingBalance

	/** Gebuchter Betrag */
	private long  accountingBalanceValue

	/** Währung des gebuchten Betrags */
	private String accountingBalanceValueCurrency
	
    /** Zeitpunkt der Gültigkeit dieses Saldos. */
	public Date   accountingBalanceDate
	
	/** Kundenreferenz */
	private String customerReference
	
	/** Kreditinstituts-Referenz */
	private String instituteReference

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }
}
