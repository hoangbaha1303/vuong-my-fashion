package edu.hbaha.spring.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.hbaha.spring.domain.Tb_ProductComment;
import edu.hbaha.spring.repository.CommentRepository;
import edu.hbaha.spring.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepository commentRepository;

	@Override
	public <S extends Tb_ProductComment> S save(S entity) {
		return commentRepository.save(entity);
	}
	
	@Override
	public List<Tb_ProductComment> findByProductID(Integer productID) {
		return commentRepository.findByProductID(productID);
	}
	
	@Override
	public int countComment(Integer productID) {
		return commentRepository.findByProductID(productID).size();
	}
	
	@Override
	public double avgCommentVote(Integer productID) {
		if(commentRepository.findByProductID(productID).isEmpty()) {
			return 0;
		}
		return commentRepository.findByProductID(productID).stream().mapToDouble(x -> x.getVote()).sum()/countComment(productID);
	}
	
	@Override
	public <S extends Tb_ProductComment> Optional<S> findOne(Example<S> example) {
		return commentRepository.findOne(example);
	}

	@Override
	public List<Tb_ProductComment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Page<Tb_ProductComment> findAll(Pageable pageable) {
		return commentRepository.findAll(pageable);
	}

	@Override
	public List<Tb_ProductComment> findAll(Sort sort) {
		return commentRepository.findAll(sort);
	}

	@Override
	public List<Tb_ProductComment> findAllById(Iterable<Integer> ids) {
		return commentRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_ProductComment> findById(Integer id) {
		return commentRepository.findById(id);
	}

	@Override
	public <S extends Tb_ProductComment> List<S> saveAll(Iterable<S> entities) {
		return commentRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		commentRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return commentRepository.existsById(id);
	}

	@Override
	public <S extends Tb_ProductComment> S saveAndFlush(S entity) {
		return commentRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_ProductComment> List<S> saveAllAndFlush(Iterable<S> entities) {
		return commentRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_ProductComment> Page<S> findAll(Example<S> example, Pageable pageable) {
		return commentRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_ProductComment> entities) {
		commentRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_ProductComment> long count(Example<S> example) {
		return commentRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_ProductComment> entities) {
		commentRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return commentRepository.count();
	}

	@Override
	public <S extends Tb_ProductComment> boolean exists(Example<S> example) {
		return commentRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		commentRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		commentRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_ProductComment entity) {
		commentRepository.delete(entity);
	}

	@Override
	public <S extends Tb_ProductComment, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return commentRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		commentRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		commentRepository.deleteAllInBatch();
	}

	@Override
	public Tb_ProductComment getOne(Integer id) {
		return commentRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_ProductComment> entities) {
		commentRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		commentRepository.deleteAll();
	}

	@Override
	public Tb_ProductComment getById(Integer id) {
		return commentRepository.getById(id);
	}

	@Override
	public Tb_ProductComment getReferenceById(Integer id) {
		return commentRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_ProductComment> List<S> findAll(Example<S> example) {
		return commentRepository.findAll(example);
	}

	@Override
	public <S extends Tb_ProductComment> List<S> findAll(Example<S> example, Sort sort) {
		return commentRepository.findAll(example, sort);
	}
	
	
}
