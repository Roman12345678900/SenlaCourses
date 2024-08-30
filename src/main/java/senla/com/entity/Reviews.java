package senla.com.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {
    private Long id;
    private Integer rating;
    private String reviewText;
    private Date reviewDate;
}
