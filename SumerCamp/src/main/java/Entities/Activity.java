package Entities;
public class Activity {
    private int idActivity;
    private String description;

    public Activity(int idActivity, String description) {
        this.idActivity = idActivity;
        this.description = description;
    }

    public Activity() {
    }
// Getters y setters

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Activity [idActivity=" + idActivity + ", description=" + description + "]";
    }
}