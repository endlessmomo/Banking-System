package login.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindLoginIdRequestDto {
    private String userName;
    private String RRN;
}
