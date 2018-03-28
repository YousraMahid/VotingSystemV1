package com.example.hp.votingsystemv1.Models;

/**
 * Created by hp on 12/26/2017.
 */

public class Home {
    private String id;
    private String subject;

    private String question;
    private String startTime;

    public String getEndTime() {
        return endTime;
    }

    private String endTime;



    public Home(String id,String subject, String question, String startTime, String endTime) {
        this.id=id;
        this.subject = subject;
        this.startTime = startTime;
        this.question=question;
        this.endTime = endTime;
    }

    public String getQuestion() {
        return question;
    }


    public String getSubject() {
        return subject;
    }



    public String getStartTime() {
        return startTime;
    }

    public String getId() {
        return id;
    }
}
