package senla.com.dto;

import lombok.*;

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
