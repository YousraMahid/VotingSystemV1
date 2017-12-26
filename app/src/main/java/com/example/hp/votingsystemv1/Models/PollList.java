package com.example.hp.votingsystemv1.Models;

/**
 * Created by hp on 12/26/2017.
 */

public class PollList {
    private String title;
    private String date;

    public PollList(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
