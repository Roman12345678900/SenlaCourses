package senla.com.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentMaintenanceDto {
    @NotNull
    private Long id;
    private Date maintenanceDate;
    private String description;
}
