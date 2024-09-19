package senla.com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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
