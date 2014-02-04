/**
 *
 */
package org.gerber.economize.repositories

import org.gerber.economize.domain.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Mike Großmann
 */
@Repository
public interface AccountInformationRepository extends CrudRepository<Account, Long> {

}