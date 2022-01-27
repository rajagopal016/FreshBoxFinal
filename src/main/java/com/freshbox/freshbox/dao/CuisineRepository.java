package com.freshbox.freshbox.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freshbox.freshbox.model.Cuisine;
import com.freshbox.freshbox.model.User;
import com.freshbox.freshbox.dao.*;


// This will be AUTO IMPLEMENTED by Spring into a Bean called ProductRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface CuisineRepository extends CrudRepository<Cuisine, Integer> {

}