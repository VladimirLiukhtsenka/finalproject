package com.liukhtenko.ticket.validator;

public class FormRegexValidator {
    private FormRegexValidator(){}
    public final static String LOGIN = "^[\\wа-яА-ЯёЁ]{3,20}$";
    public final static String EMAIL = "^[\\w-\\+]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$";
    public final static String PASSWORD = "^[\\wа-яА-ЯёЁ]{8,40}$";
    public final static String PHONE = "^([+])*[\\d]{12,13}$";
    public final static String ALL = ".*";
}
