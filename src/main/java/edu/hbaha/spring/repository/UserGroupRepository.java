package edu.hbaha.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<Tb_UserGroup, Integer>{
	List<Tb_UserGroup> findByNameContaining(String name);
	Page<Tb_UserGroup> findByNameContaining(String name, Pageable pageable);
}
