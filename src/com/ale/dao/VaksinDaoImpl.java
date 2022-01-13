package com.ale.dao;

import com.ale.entity.TempatVaksin;
import com.ale.entity.Vaksin;
import com.ale.util.DaoService;
import com.ale.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaksinDaoImpl implements DaoService<Vaksin> {
    @Override
    public List<Vaksin> fetchAll() throws ClassNotFoundException, SQLException {
        List<Vaksin> vs = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * FROM vaksin ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        Vaksin v = new Vaksin();
                        v.setId(rs.getInt("id"));
                        v.setNama(rs.getString("nama"));
                        vs.add(v);
                    }
                }
            }
        }
        return vs;
    }

    @Override
    public int addData(Vaksin vaksin) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int updateData(Vaksin vaksin) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int deleteData(Vaksin vaksin) throws ClassNotFoundException, SQLException {
        return 0;
    }
}
