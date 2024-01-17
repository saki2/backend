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
    //dodati bool da li je obrisano ili ne

}
