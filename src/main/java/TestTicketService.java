import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.entity.TypeSeat;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;

public class TestTicketService {
    public static void main(String[] args) throws ServiceException {
        TicketService ticketService = new TicketService();
//        List<Ticket> list = ticketService.findTicketsByEventId(1);
//        System.out.println(list);
//        List<Ticket> list1 = ticketService.findTicketsByEventIdAndTypeSeat(1,"Сектор A");
//        System.out.println(list1);
//        int k = ticketService.buyTicket(1,1);
//        System.out.println(k);
//        Ticket ticket = new Ticket(1,2,TypeSeat.TRIBUNE_A,100,4);
//        System.out.println( ticketService.createTicket(ticket));
        //System.out.println( ticketService.deleteTicketByEventIdAndTypeSeat(2,"Сектор A"));
    }
}
