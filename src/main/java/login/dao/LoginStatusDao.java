package login.dao;

import login.dto.response.LoginStatusResponseDto;

import java.sql.SQLException;

public interface LoginStatusDao {
    public void updateLoginState(long id, boolean status) throws SQLException;

    public LoginStatusResponseDto getLoginUserCountAndMemberId() throws SQLException;
}
