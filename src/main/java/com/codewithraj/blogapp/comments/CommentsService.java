package com.codewithraj.blogapp.comments;

import com.codewithraj.blogapp.users.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository){
        this.commentsRepository = commentsRepository;
    }
}
