package com.example.helloworld;

import android.app.Application;

public class ThemeSettings extends Application {

    public static final String PREFERENCES = "preferences";

    // Theme
    public static final String CUSTOM_THEME = "customTheme";
    public static final String LIGHT_THEME = "lightTheme";
    public static final String DARK_THEME = "darkTheme";

    // Notification
    public static final String CUSTOM_NOTIF = "customNotif";
    public static final String NOTIF_ON = "onNotification";
    public static final String NOTIF_OFF = "offNotification";


    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }

    private String customNotif;

    public String getCustomNotif() {
        return customNotif;
    }

    public void setCustomNotif(String customNotif) {
        this.customNotif = customNotif;
    }
}
