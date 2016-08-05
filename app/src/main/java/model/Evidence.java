package model;

/**
 * Created by User on 2016-04-05.
 */
public class Evidence {
    private double value;
    private String text;
    private String id;
    private String title;

    public double getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
