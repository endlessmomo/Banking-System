package login.dao;

import login.dto.MemberDto;
import login.dto.request.FindLoginIdRequestDto;
import login.dto.request.FindLoginPasswordRequestDto;
import login.dto.request.SignUpRequestDto;
import login.dto.response.FindLoginIdResponseDto;
import login.dto.response.FindLoginPasswordResponseDto;

public interface MemberDao {
    // insert
    public void save(SignUpRequestDto requestDto);


    // update
    public void updatePasswordByLoginIDAndRRN(FindLoginPasswordRequestDto requestDto);


    // select
    public MemberDto findById(String loginID);

    public FindLoginIdResponseDto findByRRN(FindLoginIdRequestDto requestDto);

    public FindLoginPasswordResponseDto findByLoginIdAndRRN(FindLoginPasswordRequestDto requestDto);

    // delete
}
