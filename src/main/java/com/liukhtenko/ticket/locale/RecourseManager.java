package com.liukhtenko.ticket.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public enum  RecourseManager { // FIXME: 20.02.2020 Delete
    INSTANCE;
    private final static String PROPERTY_PATH ="locale/locale";
    private String baseName ;
    private ResourceBundle resourceBundle;
    private Locale locale;

    RecourseManager() {
        baseName = PROPERTY_PATH;
        setLocale(Locale.getDefault());
    }

   public void setLocale(Locale locale){
        this.locale = locale;
        resourceBundle = ResourceBundle.getBundle(baseName,locale);
    }

   public String get(String key) {
        return resourceBundle.getString(key);
    }
}
