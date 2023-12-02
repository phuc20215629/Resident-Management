package application.model;

public class LichSuThayDoi {
    private int hoKhauID;
    private String thayDoi;
    
    public LichSuThayDoi(int hoKhauID, String thayDoi) {
        this.hoKhauID = hoKhauID;
        this.thayDoi = thayDoi;
    }

    public int getHoKhauID() {
        return hoKhauID;
    }
    public void setHoKhauID(int hoKhauID) {
        this.hoKhauID = hoKhauID;
    }
    public String getThayDoi() {
        return thayDoi;
    }
    public void setThayDoi(String thayDoi) {
        this.thayDoi = thayDoi;
    }
    
}
