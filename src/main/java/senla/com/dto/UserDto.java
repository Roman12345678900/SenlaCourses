package senla.com.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
