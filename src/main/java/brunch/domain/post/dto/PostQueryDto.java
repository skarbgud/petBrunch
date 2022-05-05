package brunch.domain.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostQueryDto {

    //Post
    private Long postId;
    private String title;
    private String content;
    private int commentNum;
    private Byte[] picture;
    private LocalDateTime createTime;

    //Member
    private Long userId;
    private String nickName;

    @QueryProjection
    public PostQueryDto(Long postId, String title, String content, int commentNum, Byte[] picture, LocalDateTime createTime, Long userId, String nickName) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.commentNum = commentNum;
        this.picture = picture;
        this.createTime = createTime;
        this.userId = userId;
        this.nickName = nickName;
    }
}
