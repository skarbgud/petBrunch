package brunch.domain.comment.dto;

import brunch.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private Long userId;
    private Long postId;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getMember().getId();
        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
    }
}
