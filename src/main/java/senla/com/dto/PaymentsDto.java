package senla.com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
