package senla.com.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_info")
public class CardInfo {
    @Id
    @NotNull
    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "validity_period_start")
    private LocalDate validityPeriodStart;

    @Column(name = "validity_period_end")
    private LocalDate validityPeriodEnd;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
