package rs.ac.uns.ftn.informatika.jpa.model;

import lombok.*;
import rs.ac.uns.ftn.informatika.jpa.model.enums.RatingStatus;
import rs.ac.uns.ftn.informatika.jpa.model.enums.RatingType;

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
    private RatingStatus status;
    private RatingType type;
    private int accommodationId;
    private int guestId;
    private String ratingDate;

    public Rating(int rating, String comment, RatingStatus status, RatingType type, int accommodationId, int guestId, String ratingDate) {
        this.rating = rating;
        this.comment = comment;
        this.status = status;
        this.type = type;
        this.accommodationId = accommodationId;
        this.guestId = guestId;
        this.ratingDate = ratingDate;
    }
}
