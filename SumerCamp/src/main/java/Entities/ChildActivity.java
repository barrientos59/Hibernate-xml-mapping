package Entities;

import java.io.Serializable;

public class ChildActivity implements Serializable {
    private Child child;
    private Activity activity;

    public ChildActivity(Child child, Activity activity) {
        this.child = child;
        this.activity = activity;
    }

    public ChildActivity() {
    }
// Getters y setters

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "ChildActivity [child=" + child + ", activity=" + activity + "]";
    }
}
