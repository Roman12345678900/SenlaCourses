package senla.com.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profiles {
    private Long id;
    private String address;
    private Date dateOfBirth;
    private String gender;
    private String phone;
    private Long userId;
}
