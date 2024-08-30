package senla.com.entity;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonTicketsType {
    private Long id;
    private String name;
    private Double price;
}
