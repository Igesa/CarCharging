package SpringBoot.RestApi.dto;

public class Invoice {

    public String invoiceID;
    public String userID;
    public int StationID;
    public int kWH;

    public Invoice(String invoiceID, String userID) {
        this.invoiceID = invoiceID;
        this.userID = userID;
    }

    public Invoice() {
    }

    public Invoice(String invoiceID, String userID, int stationID, int kWH) {
        this.invoiceID = invoiceID;
        this.userID = userID;
        StationID = stationID;
        this.kWH = kWH;
    }

    public Invoice(String userID) {
        this.userID = userID;
    }
}
