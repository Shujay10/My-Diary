package com.example.mydiary.struct;

import java.util.Date;

public class EventStruct {
    String title;
    String content;
    //yyyy-mm-dd
    String issue;
    String end;

    public EventStruct() {
    }

    public EventStruct(String title,String content, String issue, String end ) {
        this.title = title;
        this.content = content;
        this.issue = issue;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIssue() {
        return issue.toString();
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getEnd() {
        return end.toString();
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
