package com.ale.util;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<T>{

        List<T> fetchAll() throws ClassNotFoundException, SQLException;

        int addData(T t) throws ClassNotFoundException, SQLException;

        int updateData(T t) throws ClassNotFoundException, SQLException;

        int deleteData(T t) throws ClassNotFoundException, SQLException;

}
