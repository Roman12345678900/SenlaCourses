package senla.com.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
    private List<Reviews> reviews;
    private List<Payments> payments;
    private List<SeasonTickets> seasonTickets;
    private List<TrainerSchedules> trainerSchedules;
}
