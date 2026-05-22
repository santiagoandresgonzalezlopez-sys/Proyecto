package com.crudbasico.app.repository;

import com.crudbasico.app.model.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticionRepository extends JpaRepository<Competicion, Long> {
}