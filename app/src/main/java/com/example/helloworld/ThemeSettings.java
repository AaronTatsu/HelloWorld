package com.example.helloworld;

import android.app.Application;

public class ThemeSettings extends Application {

    public static final String PREFERENCES = "preferences";

    public static final String CUSTOM_THEME = "customTheme";
    public static final String LIGHT_THEME = "lightTheme";
    public static final String DARK_THEME = "darkTheme";

    public static final String SWITCH_STATUS = "switchStatus";

    public boolean switchStatus;

    public void setSwitchStatus(boolean switchStatus) {
        this.switchStatus = switchStatus;
    }

    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}
