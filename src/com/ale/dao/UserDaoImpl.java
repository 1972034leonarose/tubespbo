package com.ale.dao;

import com.ale.entity.User;
import com.ale.util.DaoService;
import com.ale.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements DaoService<User> {
    @Override
    public List<User> fetchAll() throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from user";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setNik(rs.getString("nik"));
                        user.setPassword(rs.getString("password"));
                        user.setEmail(rs.getString("email"));
                        user.setAlamat(rs.getString("alamat"));
                        user.setKota(rs.getString("kota"));
                        user.setTanggalLahir(rs.getString("tglLahir"));
                        users.add(user);
                    }
                }
            }
        }
        return users;
    }

    public User fetchUser(User user) throws ClassNotFoundException, SQLException {
        User result;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from user where nik = ? and password = ? ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, user.getNik());
                ps.setString(2, user.getPassword());
                try (ResultSet rs = ps.executeQuery()) {
                    result = new User();
                    while (rs.next()) {
                        result.setId(rs.getInt("id"));
                        result.setNama(rs.getString("nama"));
                        result.setPassword(rs.getString("password"));
                        result.setAlamat(rs.getString("alamat"));
                        result.setTanggalLahir(rs.getString("tanggalLahir"));
                        result.setKota(rs.getString("kota"));
                        result.setEmail(rs.getString("email"));
                        result.setNoTelp(rs.getString("noTelp"));
                    }
                    return result;
                }
            }
        }
    }

    @Override
    public int addData(User user) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO user (password, nama, nik, tanggalLahir, alamat, noTelp, kota, email) VALUES(?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
               ps.setString(1, user.getPassword());
               ps.setString(2, user.getNama());
               ps.setString(3, user.getNik());
               ps.setString(4, user.getTanggalLahir());
               ps.setString(5, user.getAlamat());
               ps.setString(6, user.getNoTelp());
               ps.setString(7, user.getKota());
               ps.setString(8,user.getEmail());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(User user) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE user SET password=?, nama=?, nik=?, tanggalLahir=?, alamat=?, noTelp=?, kota=?, email=?) WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, user.getPassword());
                ps.setString(2, user.getNama());
                ps.setString(3, user.getNik());
                ps.setString(4, user.getTanggalLahir());
                ps.setString(5, user.getAlamat());
                ps.setString(6, user.getNoTelp());
                ps.setString(7, user.getKota());
                ps.setString(8,user.getEmail());
                ps.setInt(9, user.getId());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(User user) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM user WHERE  id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, user.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
}

