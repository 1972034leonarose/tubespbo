package com.ale.dao;

import com.ale.entity.TempatVaksin;
import com.ale.entity.User;
import com.ale.entity.Vaksin;
import com.ale.util.DaoService;
import com.ale.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TempatVaksinDaoImpl implements DaoService<TempatVaksin> {
    @Override
    public List<TempatVaksin> fetchAll() throws ClassNotFoundException, SQLException {
        List<TempatVaksin> tvs = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT tempatVaksin.id, tempatVaksin.nama, tempatVaksin.alamat, tempatVaksin.noTlpn, tempatVaksin.email, tempatVaksin.kota, vaksin.nama, tempatVaksin.vaksin_id, vaksin.id FROM tempatVaksin" +
                    " INNER JOIN vaksin ON vaksin.id = tempatVaksin.vaksin_id ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        TempatVaksin tv = new TempatVaksin();
                        Vaksin v = new Vaksin();

                        v.setId(rs.getInt("vaksin.id"));
                        v.setNama(rs.getString("vaksin.nama"));

                        tv.setId(rs.getInt("tempatVaksin.id"));
                        tv.setNama(rs.getString("tempatVaksin.nama"));
                        tv.setNoTlpn(rs.getString("tempatVaksin.noTlpn"));
                        tv.setAlamat(rs.getString("tempatVaksin.alamat"));
                        tv.setEmail(rs.getString("tempatVaksin.email"));
                        tv.setVaksin_id(v);

                        tvs.add(tv);
                    }
                }
            }
        }
        return tvs;
    }

    /**
     * Fungsi untuk fetch booking sesuai dengan kota domisili user ????
     */

    public TempatVaksin fetchTerdekat(User user) throws ClassNotFoundException, SQLException {
        TempatVaksin result;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from tempatVaksin where kota = ? ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, user.getKota());

                try (ResultSet rs = ps.executeQuery()) {
                    result = new TempatVaksin();
                    while (rs.next()) {
                        result.setId(rs.getInt("id"));
                        result.setNama(rs.getString("nama"));
                        result.setAlamat(rs.getString("alamat"));
                        result.setKota(rs.getString("kota"));
                        result.setEmail(rs.getString("email"));
                        result.setNoTlpn(rs.getString("noTelpn"));
                    }
                    return result;
                }
            }
        }
    }

    @Override
    public int addData(TempatVaksin tempatVaksin) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int updateData(TempatVaksin tempatVaksin) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int deleteData(TempatVaksin tempatVaksin) throws ClassNotFoundException, SQLException {
        return 0;
    }
}
