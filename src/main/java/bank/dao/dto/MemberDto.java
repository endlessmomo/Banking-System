package bank.dao.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {
    private Long memberId;
    private String loginId;
    private String password;
    private String userName;
    private String RRN;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

}
