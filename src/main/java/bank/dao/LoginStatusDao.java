package bank.dao;

import java.sql.SQLException;

public interface LoginStatusDao {
    public void updateLoginState(long id, boolean status) throws SQLException;

    public int getLoginUserCount() throws SQLException;
}
