package com.liukhtenko.ticket.entity;
/**
 * This is enum that that contains type event.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public enum TypeEvent {
    SPORT("Спорт"),
    CONCERTS("Концерты"),
    FESTIVALS("Фестивали"),
    THEATER("Театр"),
    FOR_CHILDREN("Для детей"),
    MOVIE("Кино");
    private String value;

    TypeEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeEvent findByType(String type) {
        for (TypeEvent typeEvent : values()) {
            if (typeEvent.getValue().equalsIgnoreCase(type)) {
                return typeEvent;
            }
        }
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
