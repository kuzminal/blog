package com.kuzmin.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Document(indexName = "blog", type = "blog")
@Getter
@Setter
public class Blog {
    @Id
    private String id;
    private String title;
    private String body;
    private String status;
    private String createdBy;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy'T'HH:mm:ss")
    private Date createdDate;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy'T'HH:mm:ss")
    private Date publishDate;
    @Field(includeInParent=true, type = FieldType.Nested)
    private List<Comment> comments;
}
