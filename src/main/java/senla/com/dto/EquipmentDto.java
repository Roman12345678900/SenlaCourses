package senla.com.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {
    @NotNull
    private Long id;
    private String name;
    private String description;
    private Date purchaseDate;
    private String status;
}
