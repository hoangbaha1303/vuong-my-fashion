package edu.hbaha.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_Contact;

@Repository
public interface ContactRepository extends JpaRepository<Tb_Contact, Integer> {

}
