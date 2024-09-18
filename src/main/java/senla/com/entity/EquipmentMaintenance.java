package senla.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipment_maintenance")
public class EquipmentMaintenance {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "maintenance_date")
    private Date maintenanceDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
