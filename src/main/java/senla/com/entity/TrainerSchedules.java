package senla.com.entity;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerSchedules {
    private Long id;
    private Timestamp startTime;
    private Timestamp endTime;
}
