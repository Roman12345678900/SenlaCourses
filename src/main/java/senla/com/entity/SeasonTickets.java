package senla.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "season_tickets")
public class SeasonTickets {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private Timestamp startTime;

    @Column(name = "end_date")
    private Timestamp endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_tickets_types_id")
    private SeasonTicketsType seasonTicketsType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
