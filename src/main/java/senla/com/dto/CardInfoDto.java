package senla.com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoDto {
    @NotNull
    private Long cardNumber;
    private String cardHolderName;
    private LocalDate validityPeriodStart;
    private LocalDate validityPeriodEnd;
    @NotNull
    private Long userId;
}
