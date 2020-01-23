import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.pool.CustomConnectionPool;
import com.liukhtenko.ticket.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class TestUserService {
    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws DaoException {
        logger.log(Level.DEBUG, "Impossible to establish AutoCommit to value false!!!!!!!!!");
//        SomeService someService = new SomeService();
//        List<TypeSeat> list = someService.findAllTypeOfSeat();
//        System.out.println(list);
        UserService userService = new UserService();
        List<User> list = userService.findAllUsers();
        System.out.println(list);
//        User user = userService.findUserById( 2L);
//        System.out.println(user);

        userService.deleteUserById(7);
//        int i = 1;
//        for (Connection freeConnection : CustomConnectionPool.INSTANCE.freeConnections) {
//            System.out.println(i++);
//        }
//User user = new User(8,"375293457634","Виталий","Борунов","Владимирович", (byte) 1,"cvbdf","vl@gmail.com",2);

//        User user1 = new User(1,"375295658547","Ирина","Ковалева","Петровна", (byte) 2,"dsfds45d","Ipetr@gmail.com",2);
//        userService.createUser(user1);
//        userService.updateUser(user);
    }
}
