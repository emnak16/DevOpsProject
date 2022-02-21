package com.esprit.examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;


@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long>{

	
	//@Query("select count(f.id) from Formateur f")
	@Query(value = "SELECT (count(f.id)) from formateur f, cours c , session s, [session_cours] sc " +
			"where ((f.id = s.formateur_id) and (sc.sessions_id = s.id) and (sc.cours_id = c.id)" +
			" and ( upper(c.type_cours)) Like upper(:typeCours)", nativeQuery = true)
public Long nombreFormateursImpliquesDansUnCours(@Param("typeCours")TypeCours typeCours);
	

}
