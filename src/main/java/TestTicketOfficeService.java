import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketOfficeService;


import java.util.List;

public class TestTicketOfficeService {
    public static void main(String[] args) throws ServiceException {
        TicketOffice ticketOffice = new TicketOffice("test","test","test");
        TicketOfficeService ticketOfficeService = new TicketOfficeService();
        //ticketOfficeService.createTicketOffice(ticketOffice);
//        ticketOfficeService.deleteTicketOfficeByPhone("test");
        List<TicketOffice> ticketOffices = ticketOfficeService.findAllTicketOffice();
        System.out.println(ticketOffices);
    }
}
