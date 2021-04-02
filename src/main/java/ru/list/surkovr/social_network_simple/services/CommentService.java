package ru.list.surkovr.social_network_simple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.list.surkovr.social_network_simple.domain.Comment;
import ru.list.surkovr.social_network_simple.domain.User;
import ru.list.surkovr.social_network_simple.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        return commentRepository.save(comment);
    }
}
