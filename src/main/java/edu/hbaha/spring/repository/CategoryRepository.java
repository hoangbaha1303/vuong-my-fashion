package edu.hbaha.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_ProductCategory;

@Repository
public interface CategoryRepository extends JpaRepository<Tb_ProductCategory,Integer>{
	List<Tb_ProductCategory> findByNameContaining(String name);
	Page<Tb_ProductCategory> findByNameContaining(String name, Pageable pageable);
	List<Tb_ProductCategory> findByParrentID(Integer parrentID);
}
