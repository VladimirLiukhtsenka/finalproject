import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestEventService {


    public static void main(String[] args) throws ServiceException, ParseException {
        EventService eventService = new EventService();
//       List<Event> list = eventService.findEventByType("Спорт");
//        System.out.println(list);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone tz = TimeZone.getTimeZone("Europe/Minsk");
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(tz);
        Date moment = dateFormat.parse("2020-05-05 19:00:00");// in Bean
        Event event = new Event(1, "ывапыва вавып выв", "ваыпвап ывавыа  ыаывы", "Концерт Рояль",
                TypeEvent.CONCERTS, moment);
        eventService.createEvent(event);
//        Event event1 = new Event(3, "Музыка композиторов Ludovico ", "Дворец культуры профсоюзов Брест", "Концерт Рояль",
//                TypeEvent.CONCERTS, moment);
//        eventService.updateEvent(event1);
//eventService.deleteEventById(3);
    }
}
