package edu.hbaha.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hbaha.spring.domain.Tb_ProductComment;

@Repository
public interface CommentRepository extends JpaRepository<Tb_ProductComment, Integer> {
	List<Tb_ProductComment> findByProductID(Integer productID);
}
