package com.crudbasico.app.repository;

import com.crudbasico.app.model.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepository extends JpaRepository<Asociacion, Long> {
}