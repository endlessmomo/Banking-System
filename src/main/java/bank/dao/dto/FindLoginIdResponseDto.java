package bank.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindLoginIdResponseDto {
    private String userName;
    private String loginId;
}
