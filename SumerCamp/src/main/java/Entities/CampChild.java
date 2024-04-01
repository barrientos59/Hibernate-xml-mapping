package Entities;

import java.io.Serializable;

public class CampChild implements Serializable {
    private Camp camp;
    private Child child;

    public CampChild() {
    }

    public CampChild(Camp camp, Child child) {
        this.camp = camp;
        this.child = child;
    }

    // Getters y setters

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "CampChild [camp=" + camp + ", child=" + child + "]";
    }
}
