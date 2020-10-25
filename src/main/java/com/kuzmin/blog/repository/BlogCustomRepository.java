package com.kuzmin.blog.repository;

import com.kuzmin.blog.model.Blog;
import com.kuzmin.blog.model.Comment;

import java.io.IOException;
import java.util.List;

public interface BlogCustomRepository {
    List<Comment> getAllComments(int from, int size);

    List<Comment> getCommentsForStatus(String status, int from, int size);

    int getCurrentChildSequence(String blogId, String parentCommentId);

    List<Blog> search(String searchTxt);
}
