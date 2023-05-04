package bank.service;

import bank.dao.LoginStatusDao;
import bank.dao.LoginStatusDaoImpl;
import bank.dao.MemberDao;
import bank.dao.MemberDaoImpl;
import bank.dao.dto.LoginDto;
import bank.dao.dto.LoginStatusDto;
import bank.dao.dto.MemberDto;
import bank.dao.dto.SignUpFormDto;
import bank.util.Crypt;

import java.sql.SQLException;

public class LoginService {
    private final MemberDao memberDao = new MemberDaoImpl();
    private final LoginStatusDao loginStatusDao = new LoginStatusDaoImpl();

    public void signUp(SignUpFormDto signUpFormDto) {
        memberDao.insertMember(signUpFormDto);
    }

    public void logIn(LoginDto loginDto) throws SQLException {
        MemberDto memberDto = memberDao.findMemberByID(loginDto.getUserId());
        LoginStatusDto loginStatusDto = loginStatusDao.getLoginUserCountAndMemberId();

        try {
            if (!Crypt.decryptPassword(loginDto.getPassword(), memberDto.getPassword())) {
                throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
            }

            if(memberDto.getMemberId().equals(loginStatusDto.getMember_id())){
                throw new IllegalArgumentException("이미 로그인 된 상태입니다");
            }

            if (loginStatusDto.getCount()>= 1) {
                throw new IllegalArgumentException("다른 유저가 이용하고 있습니다");
            }


            loginStatusDao.updateLoginState(memberDto.getMemberId(), true);
        } catch (Exception e) {
            throw e;
        }
    }

//    public void logOut() {
//        if (loginStatusDao.getLoginUserCount() = 1) {
//
//        }
//    }
}
