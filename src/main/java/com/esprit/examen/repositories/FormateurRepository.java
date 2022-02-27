package com.esprit.examen.repositories;

import com.esprit.examen.entities.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long>{


	@Query(value = "SELECT(count(f.id)) from formateur f join session s on (f.id = s.formateur_id) join session_cours sc on (sc.sessions_id = s.id) join cours c on sc.cours_id = c.id where (c.type_cours = :typeCours)", nativeQuery = true)
	Long nombreFormateursImpliquesDansUnCours(@Param("typeCours") String typeCours);


}
