package senla.com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfilesDto {
    @NotNull
    private Long id;
    private String address;
    private Date dateOfBirth;
    private String gender;
    private String phone;
    @NotNull
    private Long userId;
}
