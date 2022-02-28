package com.esprit.examen.repositories;


import com.esprit.examen.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{

    @Query(value = "Select * from session s where s.formateur_id =:formateurId", nativeQuery = true)
    Set<Session> findSessionByFormateur(@Param("formateurId") Long formateurId);




}
