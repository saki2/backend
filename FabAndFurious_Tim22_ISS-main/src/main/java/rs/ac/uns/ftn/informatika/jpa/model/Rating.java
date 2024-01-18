package rs.ac.uns.ftn.informatika.jpa.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private String comment;
    private boolean deleted;
    private int accommodationId;

    public Rating(int rating, String comment, boolean deleted, int accommodationId) {
        this.rating = rating;
        this.comment = comment;
        this.deleted = deleted;
        this.accommodationId = accommodationId;
    }
}
