//package rs.ac.uns.ftn.informatika.jpa.model;
//
//import lombok.*;
//import rs.ac.uns.ftn.informatika.jpa.model.enums.ReservationRequestStatus;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@ToString
//@Entity
//public class Reservation {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne(cascade = {CascadeType.ALL})
//    private Accommodation accommodation;
//    private Date from;
//    private Date to;
//    private Date cancellationDeadline; //nepotrebno, ima u Accomodation
//    private ReservationRequestStatus status;
//    @ManyToOne(cascade = {CascadeType.ALL})
//    private Guest guest;
//  dodati cenu
//}
