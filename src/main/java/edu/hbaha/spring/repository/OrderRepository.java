package edu.hbaha.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_Order;

@Repository
public interface OrderRepository extends JpaRepository<Tb_Order, Integer> {
	List<Tb_Order> findByCustomerID(Integer customerID);
	List<Tb_Order> findByCustomerIDAndOrderDateBetween(Integer customerID, Date starDate, Date endDate);
	List<Tb_Order> findByOrderDateBetween(Date starDate, Date endDate);
}
