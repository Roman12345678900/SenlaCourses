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
public class SeasonTicketsDto {
    @NotNull
    private Long id;
    private Timestamp startTime;
    private Timestamp endTime;
}
