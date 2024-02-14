package com.encaja.domain.repository;

import com.encaja.domain.model.Bussines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BussinesRepository extends JpaRepository<Bussines, String> {

}
