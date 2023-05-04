package bank.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindLoginIdRequestDto {
    private String userName;
    private String RRN;
}
