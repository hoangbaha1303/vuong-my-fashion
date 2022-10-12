package edu.hbaha.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_Product;

@Repository
public interface ProductRepository extends JpaRepository<Tb_Product, Integer>{
	List<Tb_Product> findByNameContaining(String name);
	Page<Tb_Product> findByNameContaining(String name, Pageable pageable);
	List<Tb_Product> findByCateID(Integer cateID);
	List<Tb_Product> findTop7ByOrderByCreateDateDesc();
	List<Tb_Product> findTop8ByOrderByViewCountDesc();
	List<Tb_Product> findTop4ByHotAfterOrderByHotAsc(Date date);

}
