package senla.com.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonTicketsTypeDto {
    @NotNull
    private Long id;
    private String name;
    private Double price;
}
