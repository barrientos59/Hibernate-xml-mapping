package Entities;

import java.util.Date;

public class Child {
    private int idChild;
    private String name;
    private Date birthday;
    private boolean specialMenu;
    private Guardian guardian;
    private String dniguardian;

    public Child(int idChild, String name, Date birthday, boolean specialMenu, Guardian guardian) {
        this.idChild = idChild;
        this.name = name;
        this.birthday = birthday;
        this.specialMenu = specialMenu;
        this.guardian = guardian;
    }
    public Child() {
        // You can initialize fields here if needed
    }

    public Child(int idChild, String name, Date birthday, boolean specialMenu, String dniGuardian) {
        this.idChild = idChild;
        this.name = name;
        this.birthday = birthday;
        this.specialMenu = specialMenu;
        this.dniguardian = dniGuardian;
    }

    // Getters y setters

    public String getDniguardian() {
        return dniguardian;
    }

    public void setDniguardian(String dniguardian) {
        this.dniguardian = dniguardian;
    }

    public int getIdChild() {
        return idChild;
    }

    public void setIdChild(int idChild) {
        this.idChild = idChild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isSpecialMenu() {
        return specialMenu;
    }

    public void setSpecialMenu(boolean specialMenu) {
        this.specialMenu = specialMenu;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    @Override
    public String toString() {
        return "Child [idChild=" + idChild + ", name=" + name + ", birthday=" + birthday + ", specialMenu=" + specialMenu + ", guardian=" + guardian + "]";
    }
}