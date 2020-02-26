package com.liukhtenko.ticket.validator;
/**
 * The class that contains regular expressions
 * for form validation.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class FormRegexValidator {

    public final static String LOGIN = "[\\wа-яА-ЯёЁ]{3,20}$";
    public final static String EVENT_NAME = "^[\\p{Punct} \\wа-яА-ЯёЁ]{3,45}$";
    public final static String EVENT_ADDRESS = "^[\\p{Punct} \\wа-яА-ЯёЁ]{3,45}$";
    public final static String EVENT_DESCRIPTION = "^[\\p{Punct} \\wа-яА-ЯёЁ]{3,60}$";
    public final static String EMAIL = "^[\\w-\\+]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$";
    public final static String PASSWORD = "^[\\wа-яА-ЯёЁ]{8,45}$";
    public final static String PHONE = "^([+])*[\\d]{12,13}$";

    private FormRegexValidator() {
    }
}
