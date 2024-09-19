package senla.com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import senla.com.entity.Schedules;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulesDto {
    @NotNull
    private Long id;
    private String status;
    private String type;
    private Timestamp startTime;
    private Timestamp endTime;
    private Schedules schedules;
}
