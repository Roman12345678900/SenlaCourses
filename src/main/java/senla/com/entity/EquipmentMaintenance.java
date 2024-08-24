package senla.com.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentMaintenance {
    private Long id;
    private Date maintenanceDate;
    private String description;
}
