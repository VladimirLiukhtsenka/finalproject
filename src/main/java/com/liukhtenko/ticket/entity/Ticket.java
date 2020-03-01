package com.liukhtenko.ticket.entity;

/**
 * This is an entity class that describes ticket.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class Ticket extends Entity {
    private long id;
    private long eventId;
    private TypeSeat typeSeat;
    private int numberOfTickets;
    private double price;

    public Ticket() {
    }

    public Ticket(long id, long eventID, TypeSeat typeSeat, int numberOfTickets, double price) {
        this.id = id;
        this.eventId = eventID;
        this.typeSeat = typeSeat;
        this.numberOfTickets = numberOfTickets;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public TypeSeat getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(TypeSeat typeSeat) {
        this.typeSeat = typeSeat;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        if (id != ticket.id) {
            return false;
        }
        if (eventId != ticket.eventId) {
            return false;
        }
        if (numberOfTickets != ticket.numberOfTickets) {
            return false;
        }
        if (price != ticket.price) {
            return false;
        }
        return typeSeat != null ? typeSeat.ordinal() == ticket.typeSeat.ordinal() :
                ticket.typeSeat == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        result = (int) (prime * result + eventId);
        result = prime * result + ((typeSeat == null) ? 0 : typeSeat.hashCode());
        result = (prime * result + numberOfTickets);
        result = (int) (prime * result + price);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket{").append("id=").append(id);
        sb.append(", eventId=").append(eventId);
        sb.append(", typeSeat=").append(typeSeat);
        sb.append(", numberOfTickets=").append(numberOfTickets);
        sb.append(", price=").append(price).append('}');
        return sb.toString();
    }
}
