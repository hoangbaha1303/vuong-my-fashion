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

import edu.hbaha.spring.domain.Tb_Brand;
import edu.hbaha.spring.repository.BrandRepository;
import edu.hbaha.spring.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	BrandRepository brandRepository;

	@Override
	public List<Tb_Brand> findByNameContaining(String name) {
		return brandRepository.findByNameContaining(name);
	}
	
	@Override
	public Page<Tb_Brand> findByNameContaining(String name, Pageable pageable) {
		return brandRepository.findByNameContaining(name, pageable);
	}
	
	
	@Override
	public <S extends Tb_Brand> S save(S entity) {
		return brandRepository.save(entity);
	}

	@Override
	public <S extends Tb_Brand> Optional<S> findOne(Example<S> example) {
		return brandRepository.findOne(example);
	}

	@Override
	public List<Tb_Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public Page<Tb_Brand> findAll(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	@Override
	public List<Tb_Brand> findAll(Sort sort) {
		return brandRepository.findAll(sort);
	}

	@Override
	public List<Tb_Brand> findAllById(Iterable<Integer> ids) {
		return brandRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_Brand> findById(Integer id) {
		return brandRepository.findById(id);
	}

	@Override
	public <S extends Tb_Brand> List<S> saveAll(Iterable<S> entities) {
		return brandRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		brandRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return brandRepository.existsById(id);
	}

	@Override
	public <S extends Tb_Brand> S saveAndFlush(S entity) {
		return brandRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_Brand> List<S> saveAllAndFlush(Iterable<S> entities) {
		return brandRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_Brand> Page<S> findAll(Example<S> example, Pageable pageable) {
		return brandRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_Brand> entities) {
		brandRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_Brand> long count(Example<S> example) {
		return brandRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_Brand> entities) {
		brandRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return brandRepository.count();
	}

	@Override
	public <S extends Tb_Brand> boolean exists(Example<S> example) {
		return brandRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		brandRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		brandRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_Brand entity) {
		brandRepository.delete(entity);
	}

	@Override
	public <S extends Tb_Brand, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return brandRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		brandRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		brandRepository.deleteAllInBatch();
	}

	@Override
	public Tb_Brand getOne(Integer id) {
		return brandRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_Brand> entities) {
		brandRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		brandRepository.deleteAll();
	}

	@Override
	public Tb_Brand getById(Integer id) {
		return brandRepository.getById(id);
	}

	@Override
	public Tb_Brand getReferenceById(Integer id) {
		return brandRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_Brand> List<S> findAll(Example<S> example) {
		return brandRepository.findAll(example);
	}

	@Override
	public <S extends Tb_Brand> List<S> findAll(Example<S> example, Sort sort) {
		return brandRepository.findAll(example, sort);
	}
	
	
}
