package com.ale.dao;

import com.ale.entity.Booking;
import com.ale.entity.Jadwal;
import com.ale.entity.TempatVaksin;
import com.ale.entity.User;
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
            String query = "SELECT booking.id, booking.user_id, booking.jadwal_id, jadwal.id, user.id FROM booking INNER JOIN jadwal ON jadwal.id = booking.jadwal_id " +
                    "INNER JOIN user ON user.id = booking.user_id";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        Booking b = new Booking();
                        Jadwal j = new Jadwal();
                        User u = new User();

                        u.setId(rs.getInt("user.id"));
                        j.setId(rs.getInt("jadwal.id"));

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
