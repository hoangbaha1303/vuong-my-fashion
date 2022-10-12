package edu.hbaha.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_About;

@Repository
public interface AboutRepository extends JpaRepository<Tb_About, Integer>{
	
}
