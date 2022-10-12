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

import edu.hbaha.spring.domain.Tb_Contact;
import edu.hbaha.spring.repository.ContactRepository;
import edu.hbaha.spring.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	ContactRepository contactRepository;

	@Override
	public <S extends Tb_Contact> S save(S entity) {
		return contactRepository.save(entity);
	}

	@Override
	public <S extends Tb_Contact> Optional<S> findOne(Example<S> example) {
		return contactRepository.findOne(example);
	}

	@Override
	public List<Tb_Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public Page<Tb_Contact> findAll(Pageable pageable) {
		return contactRepository.findAll(pageable);
	}

	@Override
	public List<Tb_Contact> findAll(Sort sort) {
		return contactRepository.findAll(sort);
	}

	@Override
	public List<Tb_Contact> findAllById(Iterable<Integer> ids) {
		return contactRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_Contact> findById(Integer id) {
		return contactRepository.findById(id);
	}

	@Override
	public <S extends Tb_Contact> List<S> saveAll(Iterable<S> entities) {
		return contactRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		contactRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return contactRepository.existsById(id);
	}

	@Override
	public <S extends Tb_Contact> S saveAndFlush(S entity) {
		return contactRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_Contact> List<S> saveAllAndFlush(Iterable<S> entities) {
		return contactRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_Contact> Page<S> findAll(Example<S> example, Pageable pageable) {
		return contactRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_Contact> entities) {
		contactRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_Contact> long count(Example<S> example) {
		return contactRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_Contact> entities) {
		contactRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return contactRepository.count();
	}

	@Override
	public <S extends Tb_Contact> boolean exists(Example<S> example) {
		return contactRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		contactRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		contactRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_Contact entity) {
		contactRepository.delete(entity);
	}

	@Override
	public <S extends Tb_Contact, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return contactRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		contactRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		contactRepository.deleteAllInBatch();
	}

	@Override
	public Tb_Contact getOne(Integer id) {
		return contactRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_Contact> entities) {
		contactRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		contactRepository.deleteAll();
	}

	@Override
	public Tb_Contact getById(Integer id) {
		return contactRepository.getById(id);
	}

	@Override
	public Tb_Contact getReferenceById(Integer id) {
		return contactRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_Contact> List<S> findAll(Example<S> example) {
		return contactRepository.findAll(example);
	}

	@Override
	public <S extends Tb_Contact> List<S> findAll(Example<S> example, Sort sort) {
		return contactRepository.findAll(example, sort);
	}
	
	
}
