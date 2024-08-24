package senla.com.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {
    private Long id;
    private String status;
    private String type;
    private Timestamp startTime;
    private Timestamp endTime;
    private Schedules schedules;
    private List<Classes> classes;
}
