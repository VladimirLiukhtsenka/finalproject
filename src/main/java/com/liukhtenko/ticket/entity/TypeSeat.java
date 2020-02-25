package com.liukhtenko.ticket.entity;

public enum TypeSeat {
    PARTERRE("Партер"),
    LODGE("Ложа"),
    MEZZANINE("Бельэтаж"),
    BALCONY("Балкон"),
    SECTOR_A("Сектор A"),
    SECTOR_B("Сектор B"),
    SECTOR_C("Сектор C"),
    SECTOR_D("Сектор D"),
    TRIBUNE_A("Трибуна A"),
    TRIBUNE_B("Трибуна B"),
    TRIBUNE_C("Трибуна C"),
    TRIBUNE_D("Трибуна D"),
    FAN_ZONE("Фанзона"),
    DANCE_FLOOR("Танцпол"),
    ROW_1("Ряд 1"),
    ROW_2("Ряд 2"),
    ROW_3("Ряд 3"),
    ROW_4("Ряд 4"),
    ROW_5("Ряд 5"),
    ROW_6("Ряд 6"),
    ROW_7("Ряд 7"),
    ROW_8("Ряд 8"),
    ROW_9("Ряд 9"),
    ROW_10("Ряд 10");
    private String value;

    TypeSeat() {
    }

    TypeSeat(String value) {
        this.value = value;
    }

    public static TypeSeat findByType(String type) {
        for (TypeSeat typeSeat : values()) {
            if (typeSeat.getValue().equalsIgnoreCase(type)) {
                return typeSeat;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
