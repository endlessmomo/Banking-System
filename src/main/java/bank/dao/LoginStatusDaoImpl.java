package bank.dao;

import bank.dao.dto.LoginStatusDto;
import bank.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class LoginStatusDaoImpl implements LoginStatusDao {
    @Override
    public void updateLoginState(long id, boolean status) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "UPDATE login_status SET status = ?, updated_at = ? WHERE member_id = ?";
            ps = conn.prepareStatement(sql);

            ps.setBoolean(1, status);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(3, id);

            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new IllegalArgumentException("로그인 업데이트하는데 있어 오류가 발생했습니다.");
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public LoginStatusDto getLoginUserCountAndMemberId() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LoginStatusDto dto= null;

        int rowCnt = 0;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "SELECT * FROM login_status WHERE status = true";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                dto = LoginStatusDto.builder()
                        .count(rs.getRow())
                        .member_id(rs.getLong("member_id"))
                        .build();
            }
        } catch (SQLException e) {
            conn.rollback();
            throw new IllegalArgumentException("유저 로우 개수를 가져오는데 문제가 발생했습니다.");
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto;
    }
}
