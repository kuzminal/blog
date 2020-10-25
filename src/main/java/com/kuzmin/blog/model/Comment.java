package com.kuzmin.blog.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kuzmin.blog.util.BlogUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

    private String id;

    private String blogId;
    private String parentId;
    private int childSequence;
    private String position;
    private String status;
    private int level;
    private String user;
    private String emailAddress;
    private String commentText;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy'T'HH:mm:ss")
    private Date createdDate;

    public String getCreatedDateForDisplay() {
        String returnDateStr="";
        if(this.getCreatedDate() !=null) {
            returnDateStr = BlogUtil.getFormattedDateForDisplayOnPage(createdDate);
        }
        return returnDateStr;
    }

    @Override
    public String toString() {
        return "Comment {" +
                "\"position\":" + position+ "\"" +
                "\"user\":" + user+ "\"" +
                "\"emailAddress\":" + emailAddress+ "\"" +
                "\"commentText\":" + commentText+ "\"" +
                "\"createdDate\":" + BlogUtil.getFormattedDateForElasticSearch(createdDate)+ "\"" +
                "})";
    }

}