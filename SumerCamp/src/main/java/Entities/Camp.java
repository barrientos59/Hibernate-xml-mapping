package Entities;
import java.util.Date;

public class Camp {
    private int idCamp;
    private String site;
    private Date fromDate;
    private Date toDate;

    public Camp(int idCamp, String site, Date fromDate, Date toDate) {
        this.idCamp = idCamp;
        this.site = site;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Camp() {
    }

    // Getters y setters

    public int getIdCamp() {
        return idCamp;
    }

    public void setIdCamp(int idCamp) {
        this.idCamp = idCamp;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Camp [idCamp=" + idCamp + ", site=" + site + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
    }
}