package senla.com.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsDto {
    @NotNull
    private Long id;
    private Timestamp paymentsDate;
    private Double price;
}
