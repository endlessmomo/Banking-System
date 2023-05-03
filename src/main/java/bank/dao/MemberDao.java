package bank.dao;

import bank.dao.dto.MemberDto;
import bank.dao.dto.SignUpFormDto;

public interface MemberDao {
    public void insertMember(SignUpFormDto dto);
    public MemberDto findMemberByID(String id);

}
