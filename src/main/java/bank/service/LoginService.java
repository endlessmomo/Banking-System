package bank.service;

import bank.dao.LoginStatusDao;
import bank.dao.LoginStatusDaoImpl;
import bank.dao.MemberDao;
import bank.dao.MemberDaoImpl;
import bank.dao.dto.LoginDto;
import bank.dao.dto.MemberDto;
import bank.dao.dto.SignUpFormDto;
import bank.util.Crypt;

public class LoginService {
    private final MemberDao memberDao = new MemberDaoImpl();
    private final LoginStatusDao loginStatusDao = new LoginStatusDaoImpl();

    public void signUp(SignUpFormDto dto) {
        memberDao.insertMember(dto);
    }

    public void logIn(LoginDto loginDto) {
        MemberDto memberDto = memberDao.findMemberByID(loginDto.getUserId());

        try {
            if (!Crypt.decryptPassword(loginDto.getPassword(), memberDto.getPassword())) {
                throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
