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

import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.domain.Tb_UserGroup;
import edu.hbaha.spring.repository.UserGroupRepository;
import edu.hbaha.spring.service.UserGroupService;

@Service
public class UserGroupServiceImpl implements UserGroupService{
	@Autowired
	UserGroupRepository userGroupRepository;

	@Override
	public List<Tb_UserGroup> findByNameContaining(String name) {
		return userGroupRepository.findByNameContaining(name);
	}

	@Override
	public Page<Tb_UserGroup> findByNameContaining(String name, Pageable pageable) {
		return userGroupRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Tb_UserGroup> S save(S entity) {
		return userGroupRepository.save(entity);
	}

	@Override
	public <S extends Tb_UserGroup> Optional<S> findOne(Example<S> example) {
		return userGroupRepository.findOne(example);
	}

	@Override
	public List<Tb_UserGroup> findAll() {
		return userGroupRepository.findAll();
	}

	@Override
	public Page<Tb_UserGroup> findAll(Pageable pageable) {
		return userGroupRepository.findAll(pageable);
	}

	@Override
	public List<Tb_UserGroup> findAll(Sort sort) {
		return userGroupRepository.findAll(sort);
	}

	@Override
	public List<Tb_UserGroup> findAllById(Iterable<Integer> ids) {
		return userGroupRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_UserGroup> findById(Integer id) {
		return userGroupRepository.findById(id);
	}

	@Override
	public <S extends Tb_UserGroup> List<S> saveAll(Iterable<S> entities) {
		return userGroupRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		userGroupRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return userGroupRepository.existsById(id);
	}

	@Override
	public <S extends Tb_UserGroup> S saveAndFlush(S entity) {
		return userGroupRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_UserGroup> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userGroupRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_UserGroup> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userGroupRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_UserGroup> entities) {
		userGroupRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_UserGroup> long count(Example<S> example) {
		return userGroupRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_UserGroup> entities) {
		userGroupRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return userGroupRepository.count();
	}

	@Override
	public <S extends Tb_UserGroup> boolean exists(Example<S> example) {
		return userGroupRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		userGroupRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		userGroupRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_UserGroup entity) {
		userGroupRepository.delete(entity);
	}

	@Override
	public <S extends Tb_UserGroup, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userGroupRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userGroupRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		userGroupRepository.deleteAllInBatch();
	}

	@Override
	public Tb_UserGroup getOne(Integer id) {
		return userGroupRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_UserGroup> entities) {
		userGroupRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userGroupRepository.deleteAll();
	}

	@Override
	public Tb_UserGroup getById(Integer id) {
		return userGroupRepository.getById(id);
	}

	@Override
	public Tb_UserGroup getReferenceById(Integer id) {
		return userGroupRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_UserGroup> List<S> findAll(Example<S> example) {
		return userGroupRepository.findAll(example);
	}

	@Override
	public <S extends Tb_UserGroup> List<S> findAll(Example<S> example, Sort sort) {
		return userGroupRepository.findAll(example, sort);
	}
	
	
}
