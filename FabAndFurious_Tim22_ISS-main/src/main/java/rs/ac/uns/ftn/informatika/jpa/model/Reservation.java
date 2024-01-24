package rs.ac.uns.ftn.informatika.jpa.model;

import lombok.*;
import rs.ac.uns.ftn.informatika.jpa.model.enums.ReservationRequestStatus;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accommodation_id")
    private Long accommodationId;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "status")
    private ReservationRequestStatus status;

    @Column(name = "guest_id")
    private Long guestId;

    @Column(name = "price")
    private int price;

    public Reservation(Long accommodationId, String start, String end, ReservationRequestStatus status, Long guestId, int price) {
        this.accommodationId = accommodationId;
        this.startDate = start;
        this.endDate = end;
        this.status = status;
        this.guestId = guestId;
        this.price = price;
    }
}
