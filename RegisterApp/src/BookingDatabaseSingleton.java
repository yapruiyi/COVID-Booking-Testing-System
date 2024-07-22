import classObjects.Booking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BookingDatabaseSingleton {
    private static BookingDatabaseSingleton instance;
    protected static ObjectNode node = null; // node1 gets and stores the initial configuration from database
    protected static ObjectNode node2 = null; // node2 and node3 stores the updated configuration if booking is successful
    protected static ObjectNode node3 = null;

    public BookingDatabaseSingleton(ObjectNode value){
        if (node == null){
            System.out.println("node1 is filled");
            node  = value;
        }
    }

    public static BookingDatabaseSingleton getInstance(ObjectNode value){
        if( instance == null){
            System.out.println("instance is created");
            instance = new BookingDatabaseSingleton(value);
        }

        return instance;
    }
}
