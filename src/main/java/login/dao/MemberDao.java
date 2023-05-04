package login.dao;

import login.dto.MemberDto;
import login.dto.request.SignUpRequestDto;
import login.dto.response.FindLoginIdResponseDto;

public interface MemberDao {
    public void insertMember(SignUpRequestDto signUpFormDto);

    public MemberDto findMemberByID(String id);

    public FindLoginIdResponseDto findMemberByRRN(String RRN);

}
