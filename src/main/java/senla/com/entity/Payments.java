package senla.com.entity;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payments {
    private Long id;
    private Timestamp paymentsDate;
    private Double price;
}
