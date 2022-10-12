package edu.hbaha.spring.service.impl;

import java.util.Date;
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

import edu.hbaha.spring.domain.Tb_Product;
import edu.hbaha.spring.repository.ProductRepository;
import edu.hbaha.spring.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Tb_Product> findByNameContaining(String name) {
		return productRepository.findByNameContaining(name);
	}
	

	@Override
	public List<Tb_Product> findTop4ByHotAfterOrderByHotAsc(Date date) {
		return productRepository.findTop4ByHotAfterOrderByHotAsc(date);
	}

	@Override
	public Page<Tb_Product> findByNameContaining(String name, Pageable pageable) {
		return productRepository.findByNameContaining(name, pageable);
	}
	
	@Override
	public List<Tb_Product> findTop8ByOrderByViewCountDesc() {
		return productRepository.findTop8ByOrderByViewCountDesc();
	}

	@Override
	public List<Tb_Product> findTop7ByOrderByCreateDateDesc() {
		return productRepository.findTop7ByOrderByCreateDateDesc();
	}

	@Override
	public List<Tb_Product> findByCateID(Integer cateID) {
		return productRepository.findByCateID(cateID);
	}
	
	@Override
	public <S extends Tb_Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public <S extends Tb_Product> Optional<S> findOne(Example<S> example) {
		return productRepository.findOne(example);
	}

	@Override
	public List<Tb_Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Tb_Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Tb_Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public List<Tb_Product> findAllById(Iterable<Integer> ids) {
		return productRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public <S extends Tb_Product> List<S> saveAll(Iterable<S> entities) {
		return productRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		productRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return productRepository.existsById(id);
	}

	@Override
	public <S extends Tb_Product> S saveAndFlush(S entity) {
		return productRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_Product> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_Product> entities) {
		productRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_Product> long count(Example<S> example) {
		return productRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_Product> entities) {
		productRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public <S extends Tb_Product> boolean exists(Example<S> example) {
		return productRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		productRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public <S extends Tb_Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		productRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productRepository.deleteAllInBatch();
	}

	@Override
	public Tb_Product getOne(Integer id) {
		return productRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_Product> entities) {
		productRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public Tb_Product getById(Integer id) {
		return productRepository.getById(id);
	}

	@Override
	public Tb_Product getReferenceById(Integer id) {
		return productRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_Product> List<S> findAll(Example<S> example) {
		return productRepository.findAll(example);
	}

	@Override
	public <S extends Tb_Product> List<S> findAll(Example<S> example, Sort sort) {
		return productRepository.findAll(example, sort);
	}
	
	
}
