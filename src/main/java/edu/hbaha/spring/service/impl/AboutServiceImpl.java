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

import edu.hbaha.spring.domain.Tb_About;
import edu.hbaha.spring.repository.AboutRepository;
import edu.hbaha.spring.service.AboutService;


@Service
public class AboutServiceImpl implements AboutService{
	@Autowired
	AboutRepository aboutRepository;

	@Override
	public <S extends Tb_About> S save(S entity) {
		return aboutRepository.save(entity);
	}

	@Override
	public <S extends Tb_About> Optional<S> findOne(Example<S> example) {
		return aboutRepository.findOne(example);
	}

	@Override
	public List<Tb_About> findAll() {
		return aboutRepository.findAll();
	}

	@Override
	public Page<Tb_About> findAll(Pageable pageable) {
		return aboutRepository.findAll(pageable);
	}

	@Override
	public List<Tb_About> findAll(Sort sort) {
		return aboutRepository.findAll(sort);
	}

	@Override
	public List<Tb_About> findAllById(Iterable<Integer> ids) {
		return aboutRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_About> findById(Integer id) {
		return aboutRepository.findById(id);
	}

	@Override
	public <S extends Tb_About> List<S> saveAll(Iterable<S> entities) {
		return aboutRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		aboutRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return aboutRepository.existsById(id);
	}

	@Override
	public <S extends Tb_About> S saveAndFlush(S entity) {
		return aboutRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_About> List<S> saveAllAndFlush(Iterable<S> entities) {
		return aboutRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_About> Page<S> findAll(Example<S> example, Pageable pageable) {
		return aboutRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_About> entities) {
		aboutRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_About> long count(Example<S> example) {
		return aboutRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_About> entities) {
		aboutRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return aboutRepository.count();
	}

	@Override
	public <S extends Tb_About> boolean exists(Example<S> example) {
		return aboutRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		aboutRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		aboutRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_About entity) {
		aboutRepository.delete(entity);
	}

	@Override
	public <S extends Tb_About, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return aboutRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		aboutRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		aboutRepository.deleteAllInBatch();
	}

	@Override
	public Tb_About getOne(Integer id) {
		return aboutRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_About> entities) {
		aboutRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		aboutRepository.deleteAll();
	}

	@Override
	public Tb_About getById(Integer id) {
		return aboutRepository.getById(id);
	}

	@Override
	public Tb_About getReferenceById(Integer id) {
		return aboutRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_About> List<S> findAll(Example<S> example) {
		return aboutRepository.findAll(example);
	}

	@Override
	public <S extends Tb_About> List<S> findAll(Example<S> example, Sort sort) {
		return aboutRepository.findAll(example, sort);
	}
	
	
}
