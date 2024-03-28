package com.mockcompany.webapp.data;

import com.mockcompany.webapp.model.ProductItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This Spring pattern is Java/Spring magic.  At runtime, spring will generate an implementation of this class based on
 * the name/arguments of the method signatures defined in the interface.  See this link for details on doing data access:
 *
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 *
 * It's also possible to do specific queries using the @Query annotation:
 *
 * https://www.baeldung.com/spring-data-jpa-query
 */
@Repository
public interface ProductItemRepository extends CrudRepository<ProductItem, Long> {

    @Query(value = "SELECT p FROM ProductItem p WHERE LOWER(p.name) LIKE %?1% or LOWER(p.description) LIKE %?1%")
    List<ProductItem> findLikeSearch(String query);

    @Query(value = "SELECT p FROM ProductItem p WHERE LOWER(p.name)=?1 or LOWER(p.description)=?1")
    List<ProductItem> findLikeSearchExact(String query);

}
