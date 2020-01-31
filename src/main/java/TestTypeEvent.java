import com.liukhtenko.ticket.entity.TypeEvent;

public class TestTypeEvent {
    public static void main(String[] args) {
        TypeEvent typeEvent = TypeEvent.findByType("Для детей");
        System.out.println(typeEvent);
    }
}
