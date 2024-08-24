package rs.ac.uns.ftn.informatika.jpa.dto.response;

public class ReservationAndAccommodationDTO {
    private Long id;
    private Long accommodationId;
    private String accommodationName;
    private String startDate;
    private String endDate;
    private String status;
    private int price;
    private int numberOfPeople;

    private Long guestId;


    public ReservationAndAccommodationDTO() {}

    public ReservationAndAccommodationDTO(Long id, Long accommodationId, String accommodationName, String startDate, String endDate, String status, int price, int numberOfPeople, Long guestId) {
        this.id = id;
        this.accommodationId = accommodationId;
        this.accommodationName = accommodationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.numberOfPeople = numberOfPeople;
        this.guestId = guestId;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long reservationId) {
        this.id = reservationId;
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }
}
