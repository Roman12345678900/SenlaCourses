package senla.com.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classes {
    private Long id;
    private String name;
    private String description;
    private List<User> users;
    private List<Reviews> reviews;
}
