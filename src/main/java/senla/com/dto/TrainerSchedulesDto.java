package senla.com.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerSchedulesDto {
    @NotNull
    private Long id;
    private Timestamp startTime;
    private Timestamp endTime;
}
