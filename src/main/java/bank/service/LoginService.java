package bank.service;

import bank.dao.MemberDao;
import bank.dao.MemberDaoImpl;
import bank.dao.dto.LoginDto;
import bank.dao.dto.SignUpFormDto;

public class LoginService {
    private final MemberDao dao = new MemberDaoImpl();
    public void signUp(SignUpFormDto dto) {
        dao.insertMember(dto);
    }

    public void logIn(LoginDto dto) {

    }
}
