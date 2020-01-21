import com.liukhtenko.ticket.entity.TypeSeat;
import com.liukhtenko.ticket.pool.CustomConnectionPool;
import com.liukhtenko.ticket.service.SomeService;

import java.sql.Connection;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SomeService someService = new SomeService();
        List<TypeSeat> list = someService.findAllTypeOfSeat();
        System.out.println(list);
        int i = 1;
        for (Connection freeConnection : CustomConnectionPool.INSTANCE.freeConnections) {
            System.out.println(i++);
        }
    }
}
