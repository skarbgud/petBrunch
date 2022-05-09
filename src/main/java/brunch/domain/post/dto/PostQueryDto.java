package brunch.domain.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostQueryDto {

    //Post
    private Long id;
    private String title;
    private String content;
    private int commentNum;
    private LocalDateTime createTime;

    //Member
    private Long userId;
    private String nickName;

    @QueryProjection
    public PostQueryDto(Long id, String title, String content, int commentNum, LocalDateTime createTime, Long userId, String nickName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentNum = commentNum;
        this.createTime = createTime;
        this.userId = userId;
        this.nickName = nickName;
    }
}
