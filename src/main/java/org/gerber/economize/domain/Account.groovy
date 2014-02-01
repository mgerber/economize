/**
 * 
 */
package org.gerber.economize.domain

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Michael Gerber
 *
 */
@Entity
class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
//	@Column(nullable=false)
//	private Bank bank;
	private String accountNumber;
	private String IBAN;
}
