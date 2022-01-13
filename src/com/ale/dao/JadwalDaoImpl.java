package com.ale.dao;

import com.ale.entity.Jadwal;
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

public class JadwalDaoImpl implements DaoService<Jadwal> {
    @Override
    public List<Jadwal> fetchAll() throws ClassNotFoundException, SQLException {
        List<Jadwal> jadwals = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT jadwal.id, jadwal.jam, jadwal.tanggal, jadwal.tempatVaksin_id, tempatVaksin.id, tempatVaksin.nama" +
                    " FROM jadwal INNER JOIN tempatVaksin ON tempatVaksin.id = jadwal.id";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        Jadwal j = new Jadwal();
                        TempatVaksin t = new TempatVaksin();

                        t.setId(rs.getInt("tempatVaksin.id"));
                        t.setNama(rs.getString("tempatVaksin.jam"));

                        j.setId(rs.getInt("jadwal.id"));
                        j.setJam(rs.getString("jadwal.jam"));
                        j.setTanggal(rs.getString("jadwal.tanggal"));

                        j.setTempatVaksin(t);
                        jadwals.add(j);
                    }
                }
            }
        }
        return jadwals;
    }

    public List<Jadwal> fetchJadwal(TempatVaksin tempat) throws SQLException, ClassNotFoundException {
        List<Jadwal> jadwals = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * FROM jadwal WHERE tempatVaksin_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, tempat.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
//                        TempatVaksin tv = new TempatVaksin();
//                        tv.setId(rs.getInt("id"));
//                        tv.setNama(rs.getString("nama"));

                        Jadwal j = new Jadwal();
                        j.setId(rs.getInt("id"));
                        j.setJam(rs.getString("jam"));
                        j.setTanggal(rs.getString("tanggal"));
//                        j.setTempatVaksin(tv);

                        jadwals.add(j);
                    }
                }
            }
        }
        return jadwals;
    }


    @Override
    public int addData(Jadwal jadwal) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int updateData(Jadwal jadwal) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int deleteData(Jadwal jadwal) throws ClassNotFoundException, SQLException {
        return 0;
    }
}
