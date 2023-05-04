package bank.dao;

import bank.dao.dto.FindLoginIdResponseDto;
import bank.dao.dto.MemberDto;
import bank.dao.dto.SignUpFormDto;

public interface MemberDao {
    public void insertMember(SignUpFormDto signUpFormDto);
    public MemberDto findMemberByID(String id);
    public FindLoginIdResponseDto findMemberByRRN(String RRN);

}
