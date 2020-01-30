package com.liukhtenko.ticket.entity;

import java.util.Date;

public class Event extends Entity {
    private long id;
    private String name;
    private String address;
    private String description;
    private TypeEvent typeEvent;
    private Date date;

    public Event() {
    }

    public Event(long id, String name, String address, String description, TypeEvent typeEvent, Date date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.typeEvent = typeEvent;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeEvent getTypeOfEvent() {
        return typeEvent;
    }

    public void setTypeOfEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        if (id != event.id) {
            return false;
        }
        if ((name != null && event.name == null) || (!name.equals(event.name))) {
            return false;
        }
        if ((address != null && event.address == null) || (!address.equals(event.address))) {
            return false;
        }
        if ((description != null && event.description == null) || (!description.equals(event.description))) {
            return false;
        }
        if ((typeEvent != null && event.typeEvent == null) || (!typeEvent.equals(event.typeEvent))) {
            return false;
        }
        return date != null ? date.equals(event.date) : event.date == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((typeEvent == null) ? 0 : typeEvent.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", typeEvent=").append(typeEvent);
        sb.append(", date=").append(date).append('}');
        return sb.toString();
    }
}