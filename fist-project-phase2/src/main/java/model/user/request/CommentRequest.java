package model.user.request;

import model.item.Comment;

public class CommentRequest extends Request{
    public CommentRequest(Comment comment) {
        this.comment = comment;
    }

    private final Comment comment;

    public Comment getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return ("\nRequest ID : " + super.idRequest +
                comment.toString());
    }
}
