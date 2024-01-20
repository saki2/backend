package rs.ac.uns.ftn.informatika.jpa.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Integer ratingId;

    @Column(nullable = true)
    private Integer hostId;

    @Column(nullable = true)
    private Integer guestId;

    public Report(Integer ratingId, Integer hostId, Integer guestId) {
        this.ratingId = ratingId;
        this.hostId = hostId;
        this.guestId = guestId;
    }
}
