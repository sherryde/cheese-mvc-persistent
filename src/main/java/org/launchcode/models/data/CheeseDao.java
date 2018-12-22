package org.launchcode.models.data;

import org.launchcode.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
//Dao: Data Access Object

// will be an interface not a class. It will allow us to access cheese classes.
// It will be the interface by which we interact with the database.
// (We will not be touching the database directly. This interface will be do it for us)
/* @Repository
// tells spring this is an interface in a repository and it should manage it-
// allows spring to create a concrete class that implements this interface
* @Transactional
* Specifies that all the methods within the interface should be wrapped by a database transaction.
* A db transactions is one interaction or communication with the db.
* A single or a stack of queries can be sent to the db
* */
@Repository
@Transactional // good practice to add to Dao interfaces
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}

// extends the CrudRepository and defines a contract.
// It specifies several methods to allow us to put data in and out of the database