package Controller;

/**
 * Created by Jessie Obeng
 *
 * GSON Serializable interface used by ResPostQuestion
 * that allows for a Class to implement a toString method to turn GSON
 * into JSON
 */
public interface GSONSerializable {
    String toJson();
}
