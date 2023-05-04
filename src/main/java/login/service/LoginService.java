package login.service;

import login.dao.LoginStatusDao;
import login.dao.LoginStatusDaoImpl;
import login.dao.MemberDao;
import login.dao.MemberDaoImpl;
import login.dto.MemberDto;
import login.dto.request.FindLoginIdRequestDto;
import login.dto.request.FindLoginPasswordRequestDto;
import login.dto.request.LoginRequestDto;
import login.dto.request.SignUpRequestDto;
import login.dto.response.FindLoginIdResponseDto;
import login.dto.response.FindLoginPasswordResponseDto;
import login.dto.response.LoginStatusResponseDto;
import login.util.Crypt;
import login.util.RandomPassword;

import java.sql.SQLException;

public class LoginService {
    private final MemberDao memberDao = new MemberDaoImpl();
    private final LoginStatusDao loginStatusDao = new LoginStatusDaoImpl();

    public void signUp(SignUpRequestDto signUpFormDto) {
        String crypt = Crypt.encryptPassword(signUpFormDto.getPassword());
        signUpFormDto.setPassword(crypt);
        System.out.println(signUpFormDto.getPassword());
        memberDao.save(signUpFormDto);
    }

    public void login(LoginRequestDto loginDto) throws SQLException {
        MemberDto memberDto = memberDao.findById(loginDto.getLoginID());
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
        LoginStatusResponseDto responseDto = loginStatusDao.getLoginUserCountAndMemberId();

        try {
            if (responseDto == null || responseDto.getCount() != 1) {
                throw new IllegalArgumentException("로그인이 되어있지 않습니다.");
            }

            loginStatusDao.updateLoginState(responseDto.getMemberID(), false);
        } catch (Exception e) {
            throw e;
        }
    }

    public FindLoginIdResponseDto findLoginId(FindLoginIdRequestDto requestDto) throws SQLException {
        FindLoginIdResponseDto responseDto = memberDao.findByRRN(requestDto);

        try {
            if (responseDto == null) {
                throw new IllegalArgumentException("회원 등록이 되어있지 않습니다.");
            }

            if (!requestDto.getUserName().equals(responseDto.getUserName())) {
                throw new IllegalArgumentException("잘못된 정보를 입력하셨습니다.");
            }

            return responseDto;
        } catch (Exception e) {
            throw e;
        }
    }

    public FindLoginPasswordResponseDto findLoginPassword(FindLoginPasswordRequestDto requestDto) throws SQLException {
        // 정보를 저장해두고 암호화
        // String newPassword = RandomPassword.randomPassword().toLowerCase();
        String newPassword = requestDto.getPassword();
        requestDto.setPassword(Crypt.encryptPassword(newPassword));

        memberDao.updatePasswordByLoginIDAndRRN(requestDto);
        FindLoginPasswordResponseDto responseDto = memberDao.findByLoginIdAndRRN(requestDto);
        System.out.println(responseDto.getPassword());

        try {
            if (responseDto == null || !responseDto.getUserName().equals(requestDto.getUserName())) {
                throw new IllegalArgumentException("존재하지 않는 회원입니다.");
            }
        } catch (Exception e) {
            throw e;
        }

        responseDto.setPassword(newPassword);
        return responseDto;
    }
}
