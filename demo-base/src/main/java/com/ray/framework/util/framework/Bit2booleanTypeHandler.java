package com.ray.framework.util.framework;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wb-guoshengwang on 2015/6/9.
 */
@SuppressWarnings("rawtypes")
public class Bit2booleanTypeHandler implements TypeHandler {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        Boolean b = (Boolean) o;
        String value = b == true ? "1" : "0";
        preparedStatement.setString(i, value);
    }

    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        Boolean rt = Boolean.FALSE;
        if (str.equalsIgnoreCase("1")){
            rt = Boolean.TRUE;
        }
        return rt;
    }

    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        Boolean b = resultSet.getBoolean(i);
        return b == true ? "1" : "0";
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        Boolean b = callableStatement.getBoolean(i);
        return b == true ? "1" : "0";
    }
}
