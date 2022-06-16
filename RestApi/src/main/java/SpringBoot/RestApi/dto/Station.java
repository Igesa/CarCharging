package SpringBoot.RestApi.dto;

public class Station {
    int StationiD;
    String status;

    public Station() {
    }

    public Station(int stationiD, String status) {
        StationiD = stationiD;
        this.status = status;
    }

    public Station(int stationiD) {
        StationiD = stationiD;
    }
}
