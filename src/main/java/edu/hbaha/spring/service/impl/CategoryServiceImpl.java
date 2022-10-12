package edu.hbaha.spring.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.hbaha.spring.domain.Tb_ProductCategory;
import edu.hbaha.spring.repository.CategoryRepository;
import edu.hbaha.spring.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Tb_ProductCategory> findByNameContaining(String name) {
		return categoryRepository.findByNameContaining(name);
	}
	
	@Override
	public List<Tb_ProductCategory> findByParrentID(Integer parrentID) {
		return categoryRepository.findByParrentID(parrentID);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void delete(Tb_ProductCategory entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_ProductCategory> entities) {
		categoryRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		categoryRepository.deleteAllById(ids);
	}

	@Override
	public List<Tb_ProductCategory> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Tb_ProductCategory> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public List<Tb_ProductCategory> findAllById(Iterable<Integer> ids) {
		return categoryRepository.findAllById(ids);
	}

	@Override
	public <S extends Tb_ProductCategory> List<S> saveAll(Iterable<S> entities) {
		return categoryRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		categoryRepository.flush();
	}

	@Override
	public Page<Tb_ProductCategory> findByNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Tb_ProductCategory> S saveAndFlush(S entity) {
		return categoryRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_ProductCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryRepository.saveAllAndFlush(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_ProductCategory> entities) {
		categoryRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		categoryRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		categoryRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public <S extends Tb_ProductCategory> boolean exists(Example<S> example) {
		return categoryRepository.exists(example);
	}

	@Override
	public boolean existsById(Integer id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public Page<Tb_ProductCategory> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public <S extends Tb_ProductCategory, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return categoryRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<Tb_ProductCategory> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Tb_ProductCategory getById(Integer id) {
		return categoryRepository.getById(id);
	}

	@Override
	public <S extends Tb_ProductCategory> S save(S entity) {
		
		return categoryRepository.save(entity);
	}
	
	
}
