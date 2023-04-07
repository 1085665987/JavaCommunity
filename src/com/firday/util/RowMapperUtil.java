package com.firday.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public abstract class RowMapperUtil<T>  implements RowMapper<T>{

	@Override
	public abstract T mapRow(ResultSet rs, int arg1) throws SQLException;

}
