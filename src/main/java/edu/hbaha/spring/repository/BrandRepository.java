package edu.hbaha.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_Brand;

@Repository
public interface BrandRepository extends JpaRepository<Tb_Brand, Integer>{
	List<Tb_Brand> findByNameContaining(String name);
	Page<Tb_Brand> findByNameContaining(String name, Pageable pageable);
}
