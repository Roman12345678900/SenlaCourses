package senla.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedules {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;
}
