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

import edu.hbaha.spring.domain.Tb_Supplier;
import edu.hbaha.spring.repository.SupplierRepository;
import edu.hbaha.spring.service.SupplierService;


@Service
public class SupplierServiceImpl implements SupplierService{
	@Autowired
	SupplierRepository serviceRepository;

	@Override
	public List<Tb_Supplier> findByNameContaining(String name) {
		return serviceRepository.findByNameContaining(name);
	}

	@Override
	public Page<Tb_Supplier> findByNameContaining(String name, Pageable pageable) {
		return serviceRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Tb_Supplier> S save(S entity) {
		return serviceRepository.save(entity);
	}

	@Override
	public <S extends Tb_Supplier> Optional<S> findOne(Example<S> example) {
		return serviceRepository.findOne(example);
	}

	@Override
	public List<Tb_Supplier> findAll() {
		return serviceRepository.findAll();
	}

	@Override
	public Page<Tb_Supplier> findAll(Pageable pageable) {
		return serviceRepository.findAll(pageable);
	}

	@Override
	public List<Tb_Supplier> findAll(Sort sort) {
		return serviceRepository.findAll(sort);
	}

	@Override
	public List<Tb_Supplier> findAllById(Iterable<Integer> ids) {
		return serviceRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_Supplier> findById(Integer id) {
		return serviceRepository.findById(id);
	}

	@Override
	public <S extends Tb_Supplier> List<S> saveAll(Iterable<S> entities) {
		return serviceRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		serviceRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return serviceRepository.existsById(id);
	}

	@Override
	public <S extends Tb_Supplier> S saveAndFlush(S entity) {
		return serviceRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_Supplier> List<S> saveAllAndFlush(Iterable<S> entities) {
		return serviceRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_Supplier> Page<S> findAll(Example<S> example, Pageable pageable) {
		return serviceRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_Supplier> entities) {
		serviceRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_Supplier> long count(Example<S> example) {
		return serviceRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_Supplier> entities) {
		serviceRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return serviceRepository.count();
	}

	@Override
	public <S extends Tb_Supplier> boolean exists(Example<S> example) {
		return serviceRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		serviceRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		serviceRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_Supplier entity) {
		serviceRepository.delete(entity);
	}

	@Override
	public <S extends Tb_Supplier, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return serviceRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		serviceRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		serviceRepository.deleteAllInBatch();
	}

	@Override
	public Tb_Supplier getOne(Integer id) {
		return serviceRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_Supplier> entities) {
		serviceRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		serviceRepository.deleteAll();
	}

	@Override
	public Tb_Supplier getById(Integer id) {
		return serviceRepository.getById(id);
	}

	@Override
	public Tb_Supplier getReferenceById(Integer id) {
		return serviceRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_Supplier> List<S> findAll(Example<S> example) {
		return serviceRepository.findAll(example);
	}

	@Override
	public <S extends Tb_Supplier> List<S> findAll(Example<S> example, Sort sort) {
		return serviceRepository.findAll(example, sort);
	}
	
	
}
