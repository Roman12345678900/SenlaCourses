package senla.com.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.collections4.Factory;

import java.sql.Date;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
@NamedEntityGraph(name = "Reviews.withAssociations",
        attributeNodes = {
        @NamedAttributeNode("classes"),
        @NamedAttributeNode("user")
        })
public class Reviews {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "review_date")
    private Date reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
