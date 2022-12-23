package com.example.helloworld.Settings;

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

    // Language
    public static final String CUSTOM_LANG = "customLang";
    public static final String ENG_LANG = "langEng";
    public static final String TAG_LANG = "langTag";

    // Text Size
    public static final String CUSTOM_SIZE = "customSize";
    public static final String SMALL_SIZE = "sizeSmall";
    public static final String MEDIUM_SIZE = "sizeMedium";
    public static final String LARGE_SIZE = "sizeLarge";

    // Theme
    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }

    // Notification
    private String customNotif;

    public String getCustomNotif() {
        return customNotif;
    }

    public void setCustomNotif(String customNotif) {
        this.customNotif = customNotif;
    }

    // Language
    private String customLang;

    public String getCustomLang() {
        return customLang;
    }

    public void setCustomLang(String customLang) {
        this.customLang = customLang;
    }

    // Text Size
    private String customSize;

    public String getCustomSize() {
        return customSize;
    }

    public void setCustomSize(String customSize) {
        this.customSize = customSize;
    }
}
