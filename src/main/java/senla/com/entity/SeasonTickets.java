package senla.com.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonTickets {
    private Long id;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<SeasonTicketsType> seasonTicketsType;
}
