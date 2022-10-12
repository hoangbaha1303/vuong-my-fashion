package edu.hbaha.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_User;

@Repository
public interface UserReponsitory extends JpaRepository<Tb_User, Integer>{
	List<Tb_User> findByNameContaining(String name);
	Page<Tb_User> findByNameContaining(String name, Pageable pageable);
	Optional<Tb_User> findByUserName(String username);
}
