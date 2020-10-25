package com.kuzmin.blog.service;

import com.kuzmin.blog.model.Blog;
import com.kuzmin.blog.model.Comment;
import com.kuzmin.blog.repository.BlogRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public void addUpdateBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        List<Blog> blogList = new ArrayList<Blog>();
        Iterable<Blog> blogIterable = blogRepository.findAll();
        Iterator<Blog> blogIterator = blogIterable.iterator();
        while(blogIterator.hasNext()) {
            blogList.add(blogIterator.next());
        }
        return blogList;
    }

    public Blog getBlog(String blogId) {
        Optional<Blog> blogObj = blogRepository.findById(blogId);
        if(blogObj.isPresent()) {
            return blogObj.get();
        }else {
            return null;
        }
    }

    public void updateCommentStatus(String blogId,String commentId, List<Comment> commentList, String updatedStatus) {
        if(commentList !=null) {
            for(Comment comment: commentList) {
                if(comment.getId().equals(commentId)) {
                    comment.setStatus(updatedStatus);
                    break;
                }
            }
            Blog blog = this.getBlog(blogId);
            blog.setComments(commentList);
            blogRepository.save(blog);
        }

    }

    public int getCurrentChildSequence(String blogId,String parentCommentId) {
        return blogRepository.getCurrentChildSequence(blogId,parentCommentId);
    }

    public List<Comment> getAllComments(int from, int size){
        return blogRepository.getAllComments(from, size);
    }

    public List<Comment> getCommentsForStatus(String status, int from, int size){
        return blogRepository.getCommentsForStatus(status, from, size);
    }

    public List<Blog> search(String searchTxt){
        return this.blogRepository.search(searchTxt);
    }

    public void deleteBlog(String blogId) {
        Optional<Blog> blogObj = this.blogRepository.findById(blogId);
        if(blogObj.isPresent()) {
            this.blogRepository.delete(blogObj.get());
        }
    }
}
