package senla.com.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
    @NotNull
    private Long cardNumber;
    private String cardHolderName;
    private Date validityPeriod;
    private Long userId;
}
