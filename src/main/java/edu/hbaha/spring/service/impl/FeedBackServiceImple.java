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

import edu.hbaha.spring.domain.Tb_FeedBack;
import edu.hbaha.spring.repository.FeedBackRepository;
import edu.hbaha.spring.service.FeedBackService;

@Service
public class FeedBackServiceImple implements FeedBackService{
	@Autowired
	FeedBackRepository feedBackRepository;

	@Override
	public <S extends Tb_FeedBack> S save(S entity) {
		return feedBackRepository.save(entity);
	}

	@Override
	public <S extends Tb_FeedBack> Optional<S> findOne(Example<S> example) {
		return feedBackRepository.findOne(example);
	}

	@Override
	public List<Tb_FeedBack> findByStatus(Boolean status) {
		return feedBackRepository.findByStatus(status);
	}

	@Override
	public List<Tb_FeedBack> findAll() {
		return feedBackRepository.findAll();
	}

	@Override
	public Page<Tb_FeedBack> findAll(Pageable pageable) {
		return feedBackRepository.findAll(pageable);
	}

	@Override
	public List<Tb_FeedBack> findAll(Sort sort) {
		return feedBackRepository.findAll(sort);
	}

	@Override
	public List<Tb_FeedBack> findAllById(Iterable<Integer> ids) {
		return feedBackRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_FeedBack> findById(Integer id) {
		return feedBackRepository.findById(id);
	}

	@Override
	public <S extends Tb_FeedBack> List<S> saveAll(Iterable<S> entities) {
		return feedBackRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		feedBackRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return feedBackRepository.existsById(id);
	}

	@Override
	public <S extends Tb_FeedBack> S saveAndFlush(S entity) {
		return feedBackRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_FeedBack> List<S> saveAllAndFlush(Iterable<S> entities) {
		return feedBackRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_FeedBack> Page<S> findAll(Example<S> example, Pageable pageable) {
		return feedBackRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_FeedBack> entities) {
		feedBackRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_FeedBack> long count(Example<S> example) {
		return feedBackRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_FeedBack> entities) {
		feedBackRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return feedBackRepository.count();
	}

	@Override
	public <S extends Tb_FeedBack> boolean exists(Example<S> example) {
		return feedBackRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		feedBackRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		feedBackRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_FeedBack entity) {
		feedBackRepository.delete(entity);
	}

	@Override
	public <S extends Tb_FeedBack, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return feedBackRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		feedBackRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		feedBackRepository.deleteAllInBatch();
	}

	@Override
	public Tb_FeedBack getOne(Integer id) {
		return feedBackRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_FeedBack> entities) {
		feedBackRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		feedBackRepository.deleteAll();
	}

	@Override
	public Tb_FeedBack getById(Integer id) {
		return feedBackRepository.getById(id);
	}

	@Override
	public Tb_FeedBack getReferenceById(Integer id) {
		return feedBackRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_FeedBack> List<S> findAll(Example<S> example) {
		return feedBackRepository.findAll(example);
	}

	@Override
	public <S extends Tb_FeedBack> List<S> findAll(Example<S> example, Sort sort) {
		return feedBackRepository.findAll(example, sort);
	}
	
	
}
