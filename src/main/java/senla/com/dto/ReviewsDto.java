package senla.com.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDto {
    @NotNull
    private Long id;
    private Integer rating;
    private String reviewText;
    private Date reviewDate;
}
