package brunch.domain.post.dto;

import brunch.domain.comment.dto.CommentResponseDto;
import brunch.domain.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private int view;
    private int empathy;
    private int commentNum;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.userId = post.getMember().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.view = post.getView();
        this.empathy = post.getEmpathy();
        this.commentNum = post.getCommentNum();
        this.comments = post.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
