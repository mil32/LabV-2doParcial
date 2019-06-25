package com.utn.parcialLab.repository;

import com.utn.parcialLab.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    String MY_QUERY = "select p.title, u.name as user, count(distinct c.id) as count from publication p inner join user u on p.user_id= u.id inner join comment c on c.publication_id = p.id where p.id = ?1" ;

    @Query(value = MY_QUERY, nativeQuery = true)
    ComentsxPublications getCount(Integer id);

}
