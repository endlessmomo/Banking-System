package login.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindLoginIdResponseDto {
    private String userName;
    private String loginID;
}
