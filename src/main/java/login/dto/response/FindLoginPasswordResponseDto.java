package login.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FindLoginPasswordResponseDto {
    private String userName;
    private String loginID;
    private String password;
}
