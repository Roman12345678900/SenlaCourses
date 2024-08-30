package senla.com.entity;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private Long id;
    private String name;
    private String description;
    private Date purchaseDate;
    private String status;
    private List<EquipmentMaintenance> equipmentMaintenances;
}
