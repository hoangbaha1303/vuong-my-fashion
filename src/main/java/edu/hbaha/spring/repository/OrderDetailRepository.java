package edu.hbaha.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<Tb_OrderDetail, Integer> {
	List<Tb_OrderDetail> findByid_OrderID(Integer orderID);
}
