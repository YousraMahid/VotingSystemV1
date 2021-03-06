package com.example.hp.votingsystemv1.Models;

import com.example.hp.votingsystemv1.R;

public class Theme {

    public static int theme;
    private final static int THEME_LIGHT = 1;
    private final static int THEME_DARK = 2;

    public static void setTheme(int themeId) {
        switch (themeId) {
            default:
            case THEME_LIGHT:
                theme = R.style.LightCustom;
                break;
            case THEME_DARK:
                theme = R.style.DarkCustom;
                break;
        }
    }
}
