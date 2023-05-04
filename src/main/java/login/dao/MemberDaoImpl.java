package login.dao;

import global.util.DBUtil;
import login.dto.MemberDto;
import login.dto.request.SignUpRequestDto;
import login.dto.response.FindLoginIdResponseDto;
import login.util.Crypt;

import java.sql.*;
import java.time.LocalDateTime;

public class MemberDaoImpl implements MemberDao {
    @Override
    public void insertMember(SignUpRequestDto dto) {
        Connection conn = null;
        PreparedStatement ps = null;

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO member (login_id, password, user_name, RRN, phone_number, address, created_at, updated_at, is_deleted)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, dto.getLoginID());
            ps.setString(2, Crypt.encryptPassword(dto.getPassword()));
            ps.setString(3, dto.getUserName());
            ps.setString(4, dto.getRRN());
            ps.setString(5, dto.getPhoneNumber());
            ps.setString(6, dto.getAddress());
            ps.setTimestamp(7, date);
            ps.setTimestamp(8, date);
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
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MemberDto findMemberByID(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MemberDto dto = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "SELECT * FROM member WHERE login_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                dto = MemberDto.builder()
                        .memberID(rs.getLong("member_id"))
                        .loginID(rs.getString("login_id"))
                        .password(rs.getString("password"))
                        .userName(rs.getString("user_name"))
                        .RRN(rs.getString("RRN"))
                        .phoneNumber(rs.getString("phone_number"))
                        .address(rs.getString("address"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .isDeleted(rs.getBoolean("is_deleted"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    @Override
    public FindLoginIdResponseDto findMemberByRRN(String RRN) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FindLoginIdResponseDto responseDto = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "SELECT login_id, user_name FROM member WHERE RRN = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, RRN);

            rs = ps.executeQuery();

            if (rs.next()) {
                responseDto = FindLoginIdResponseDto.builder()
                        .loginID(rs.getString("login_id"))
                        .userName(rs.getString("user_name"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseDto;
    }
}

