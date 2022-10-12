package edu.hbaha.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Tb_Supplier, Integer> {
	List<Tb_Supplier> findByNameContaining(String name);
	Page<Tb_Supplier> findByNameContaining(String name, Pageable pageable);
}
