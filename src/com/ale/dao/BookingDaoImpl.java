package com.ale.dao;

import com.ale.entity.*;
import com.ale.util.DaoService;
import com.ale.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements DaoService<Booking> {
    @Override
    public List<Booking> fetchAll() throws ClassNotFoundException, SQLException {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT booking.id, booking.user_id, booking.jadwal_id, jadwal.id, user.id, " +
                    "tempatVaksin.nama, jadwal.jam, jadwal.tanggal, jadwal.tempatVaksin_id, " +
                    "vaksin.nama, vaksin.id, tempatVaksin.vaksin_id, tempatVaksin.id FROM booking " +
                    "INNER JOIN jadwal ON jadwal.id = booking.jadwal_id " +
                    "INNER JOIN user ON user.id = booking.user_id " +
                    "INNER JOIN tempatVaksin ON tempatVaksin.id = jadwal.tempatVaksin_id " +
                    "INNER JOIN vaksin ON vaksin.id = tempatVaksin.vaksin_id";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        Booking b = new Booking();
                        Jadwal j = new Jadwal();
                        User u = new User();
                        Vaksin v = new Vaksin();
                        TempatVaksin t = new TempatVaksin();

                        u.setId(rs.getInt("user.id"));
                        j.setId(rs.getInt("jadwal.id"));
                        j.setTanggal(rs.getString("jadwal.tanggal"));
                        j.setJam(rs.getString("jadwal.jam"));
                        j.setTempatVaksin(t);

                        v.setId(rs.getInt("vaksin.id"));
                        v.setNama(rs.getString("vaksin.nama"));
                        t.setId(rs.getInt("tempatVaksin.id"));
                        t.setNama(rs.getString("tempatVaksin.nama"));
                        t.setVaksin_id(v);

                        b.setId(rs.getInt("booking.id"));
                        b.setUser_id(u);
                        b.setJadwal_id(j);

                        bookings.add(b);
                    }
                }
            }
        }
        return bookings;
    }

    @Override
    public int addData(Booking booking) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO booking(id, user_id, jadwal_id) VALUES(?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, booking.getId());
                ps.setInt(2, booking.getUser_id().getId());
                ps.setInt(3, booking.getJadwal_id().getId());

                if (ps.executeUpdate() != 0){
                    connection.commit();
                    result = 1;
                } else{
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Booking booking) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int deleteData(Booking booking) throws ClassNotFoundException, SQLException {
        return 0;
    }
}
