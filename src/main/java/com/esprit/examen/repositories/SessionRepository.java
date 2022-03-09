package com.esprit.examen.repositories;

import com.esprit.examen.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esprit.examen.entities.Session;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{


}
