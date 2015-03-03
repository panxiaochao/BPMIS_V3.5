package org.pxcbpmisframework.core.common.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import org.pxcbpmisframework.core.common.dao.jdbc.JdbcTemplateDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("jdbcTemplateDao")
@Transactional
public class JdbcTemplateIml implements JdbcTemplateDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	public void execute(String sql) {
	}

	public int queryForInt(String sql) {
		return queryForInt(sql, null);
	}

	public int queryForInt(String sql, Object[] args) {
		try {
			return ((Integer) this.jdbcTemplate.queryForObject(sql, args,
					Integer.class)).intValue();
		} catch (EmptyResultDataAccessException e) {
		}
		return -1;
	}

	public String queryForString(String sql) {
		return queryForString(sql, null);
	}

	public String queryForString(String sql, Object[] args) {
		try {
			return (String) this.jdbcTemplate.queryForObject(sql, args,
					String.class);
		} catch (EmptyResultDataAccessException e) {
		}
		return "-1";
	}

	public <T> List<T> queryForList(String sql) {
		try {
			return (List<T>)this.jdbcTemplate.queryForList(sql);
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}

	public <T> List<T> queryForList(String sql, Class<T> entityClass) {
		return queryForList(sql, null, entityClass);
	}

	public <T> List<T> queryForList(String sql, Object[] args,
			Class<T> entityClass) {
		try {
			return (List<T>)this.jdbcTemplate.queryForList(sql, new Object[] { args,
					new BeanPropertyRowMapper(entityClass) });
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}
}
