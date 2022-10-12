package edu.hbaha.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_FeedBack;

@Repository
public interface FeedBackRepository extends JpaRepository<Tb_FeedBack, Integer>{
	List<Tb_FeedBack> findByStatus(Boolean status);
}
