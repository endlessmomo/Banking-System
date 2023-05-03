package bank.service;

import bank.dao.dto.SignUpFormDto;
import bank.util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static java.time.Instant.now;

public class LoginService {
    public void signUp(SignUpFormDto dto) {
        Connection conn = null;
        PreparedStatement ps = null;

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO member (login_id, password, user_name, RRN, phoneNumber, address, created_at,updated_at, is_deleted)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPw());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getRRN());
            ps.setString(5, dto.getPhoneNumber());
            ps.setString(6, dto.getAddress());
            ps.setTimestamp(7, date);
            ps.setTimestamp(8, null);
            ps.setBoolean(9, false);

            ps.execute();

            sql = "INSERT INTO login_status(status, updated_at) VALUES(?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setBoolean(1, false);
            ps.setTimestamp(2, date);

            ps.execute();
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (ps != null)
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
