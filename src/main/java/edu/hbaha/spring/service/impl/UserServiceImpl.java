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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.repository.UserReponsitory;
import edu.hbaha.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserReponsitory userReponsitory;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<Tb_User> findByNameContaining(String name) {
		return userReponsitory.findByNameContaining(name);
	}

	@Override
	public Page<Tb_User> findByNameContaining(String name, Pageable pageable) {
		return userReponsitory.findByNameContaining(name, pageable);
	}

	@Override
	public Optional<Tb_User> findByUserName(String username) {
		return userReponsitory.findByUserName(username);
	}
	
	@Override
	public boolean isEqualPassword(String password, String password2) {
		return bCryptPasswordEncoder.matches(password, password2);
	}
	
	@Override
	public Tb_User login(String username, String password) {
		Optional<Tb_User> otpExist = findByUserName(username);
		
		if(otpExist.isPresent() && bCryptPasswordEncoder.matches(password, otpExist.get().getPassword())) {
			otpExist.get().setPassword("");
			return otpExist.get();
		}
		return null;
	}
	
	@Override
	public <S extends Tb_User> S save(S entity) {
		Optional<Tb_User> optExist = findByUserName(entity.getUserName());
		if (optExist.isPresent()) {
			if (StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(optExist.get().getPassword());
			}
			else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
		} else {
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		}
		return userReponsitory.save(entity);
	}

	@Override
	public <S extends Tb_User> Optional<S> findOne(Example<S> example) {
		return userReponsitory.findOne(example);
	}

	@Override
	public List<Tb_User> findAll() {
		return userReponsitory.findAll();
	}

	@Override
	public Page<Tb_User> findAll(Pageable pageable) {
		return userReponsitory.findAll(pageable);
	}

	@Override
	public List<Tb_User> findAll(Sort sort) {
		return userReponsitory.findAll(sort);
	}

	@Override
	public List<Tb_User> findAllById(Iterable<Integer> ids) {
		return userReponsitory.findAllById(ids);
	}

	@Override
	public Optional<Tb_User> findById(Integer id) {
		return userReponsitory.findById(id);
	}

	@Override
	public <S extends Tb_User> List<S> saveAll(Iterable<S> entities) {
		return userReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		userReponsitory.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return userReponsitory.existsById(id);
	}

	@Override
	public <S extends Tb_User> S saveAndFlush(S entity) {
		return userReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_User> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_User> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_User> entities) {
		userReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_User> long count(Example<S> example) {
		return userReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_User> entities) {
		userReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return userReponsitory.count();
	}

	@Override
	public <S extends Tb_User> boolean exists(Example<S> example) {
		return userReponsitory.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		userReponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		userReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_User entity) {
		userReponsitory.delete(entity);
	}

	@Override
	public <S extends Tb_User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userReponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		userReponsitory.deleteAllInBatch();
	}

	@Override
	public Tb_User getOne(Integer id) {
		return userReponsitory.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_User> entities) {
		userReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userReponsitory.deleteAll();
	}

	@Override
	public Tb_User getById(Integer id) {
		return userReponsitory.getById(id);
	}

	@Override
	public Tb_User getReferenceById(Integer id) {
		return userReponsitory.getReferenceById(id);
	}

	@Override
	public <S extends Tb_User> List<S> findAll(Example<S> example) {
		return userReponsitory.findAll(example);
	}

	@Override
	public <S extends Tb_User> List<S> findAll(Example<S> example, Sort sort) {
		return userReponsitory.findAll(example, sort);
	}

}
