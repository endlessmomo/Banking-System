package bank.dao;

import bank.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginStatusDaoImpl implements LoginStatusDao {
    @Override
    public void updateLoginState(long id, boolean status) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "UPDATE login_status SET status = ? WHERE member_id = ?";
            ps = conn.prepareStatement(sql);

            ps.setBoolean(1, status);
            ps.setLong(2, id);

            ps.execute();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
