package bank.dao;

import bank.dao.dto.LoginStatusDto;

import java.sql.SQLException;

public interface LoginStatusDao {
    public void updateLoginState(long id, boolean status) throws SQLException;

    public LoginStatusDto getLoginUserCountAndMemberId() throws SQLException;
}
