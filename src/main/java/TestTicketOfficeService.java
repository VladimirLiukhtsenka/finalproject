import com.liukhtenko.ticket.command.CommandFactory;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketOfficeService;

import java.util.HashSet;
import java.util.Set;

public class TestTicketOfficeService {
    public static void main(String[] args) throws ServiceException {
   //     TicketOffice ticketOffice = new TicketOffice("test","test","test");
        TicketOfficeService ticketOfficeService = new TicketOfficeService();
        //ticketOfficeService.createTicketOffice(ticketOffice);
//        ticketOfficeService.deleteTicketOfficeByPhone("test");
//        List<TicketOffice> ticketOffices = ticketOfficeService.findAllTicketOffice();
//        System.out.println(ticketOffices);
        Set<String> l = new HashSet<>();
        l.add("sds");
        Set<String> k = new HashSet<>();
        k.add("qq");
        k.addAll(l);
        System.out.println(k);

    }
}
