package login.service;

import login.dao.LoginStatusDao;
import login.dao.LoginStatusDaoImpl;
import login.dao.MemberDao;
import login.dao.MemberDaoImpl;
import login.dto.MemberDto;
import login.dto.request.FindLoginIdRequestDto;
import login.dto.request.LoginRequestDto;
import login.dto.request.SignUpRequestDto;
import login.dto.response.FindLoginIdResponseDto;
import login.dto.response.LoginStatusResponseDto;
import login.util.Crypt;

import java.sql.SQLException;

public class LoginService {
    private final MemberDao memberDao = new MemberDaoImpl();
    private final LoginStatusDao loginStatusDao = new LoginStatusDaoImpl();

    public void signUp(SignUpRequestDto signUpFormDto) {
        memberDao.insertMember(signUpFormDto);
    }

    public void login(LoginRequestDto loginDto) throws SQLException {
        MemberDto memberDto = memberDao.findMemberByID(loginDto.getLoginID());
        LoginStatusResponseDto loginStatusResponseDto = loginStatusDao.getLoginUserCountAndMemberId();

        try {
            if (!Crypt.decryptPassword(loginDto.getPassword(), memberDto.getPassword())) {
                throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
            }

            if (loginStatusResponseDto != null && memberDto.getMemberID().equals(loginStatusResponseDto.getMemberID())) {
                throw new IllegalArgumentException("이미 로그인 된 상태입니다");
            }

            if (loginStatusResponseDto != null && loginStatusResponseDto.getCount() >= 1) {
                throw new IllegalArgumentException("다른 유저가 이용하고 있습니다");
            }

            loginStatusDao.updateLoginState(memberDto.getMemberID(), true);
        } catch (Exception e) {
            throw e;
        }
    }

    public void logout() throws SQLException {
        LoginStatusResponseDto loginStatusResponseDto = loginStatusDao.getLoginUserCountAndMemberId();

        try {
            if (loginStatusResponseDto == null || loginStatusResponseDto.getCount() != 1) {
                throw new IllegalArgumentException("로그인이 되어있지 않습니다.");
            }

            loginStatusDao.updateLoginState(loginStatusResponseDto.getMemberID(), false);
        } catch (Exception e) {
            throw e;
        }
    }

    public FindLoginIdResponseDto findMemberLoginId(FindLoginIdRequestDto requestDto) throws SQLException {
        FindLoginIdResponseDto findLoginIdResponseDto = memberDao.findMemberByRRN(requestDto.getRRN());

        try {
            if (findLoginIdResponseDto == null) {
                throw new IllegalArgumentException("회원 등록이 되어있지 않습니다.");
            }

            if (!requestDto.getUserName().equals(findLoginIdResponseDto.getUserName())) {
                throw new IllegalArgumentException("잘못된 정보를 입력하셨습니다.");
            }

            return findLoginIdResponseDto;
        } catch (Exception e) {
            throw e;
        }
    }
}
