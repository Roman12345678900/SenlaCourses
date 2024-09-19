package senla.com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
