/**
 *
 */
package org.gerber.economize.repositories

import org.gerber.economize.domain.Bank
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Mike Großmann
 *
 */
@Repository
public interface BankInformationRepository extends CrudRepository<Bank, Long> {

    Bank findByBlz(String blz)

}