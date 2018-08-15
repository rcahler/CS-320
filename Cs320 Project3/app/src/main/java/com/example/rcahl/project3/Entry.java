package com.example.rcahl.project3;

public class Entry {
    public final String event;
    public final String summary;
    public final String effective;
    public final String expires;
    public final String urgency;
    public final String severity;
    public final String certainty;
    public final String link;


    public Entry(String event, String summary, String effective, String expires, String urgency, String severity, String certainty, String link) {
        this.event = event;
        this.effective = effective;
        this.expires = expires;
        this.urgency = urgency;
        this.severity = severity;
        this.certainty = certainty;
        this.summary = summary;
        this.link = link;
    }
}
