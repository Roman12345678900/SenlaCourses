package senla.com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoDto {
    @NotNull
    private Long cardNumber;
    private String cardHolderName;
    private Date validityPeriod;
    @NotNull
    private Long userId;
}
