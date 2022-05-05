package brunch.domain.post.dto;

import brunch.domain.member.Member;
import brunch.domain.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class PostRequestDto {

    @NotEmpty private Member member;
    @NotEmpty private String title;
    @NotEmpty private String content;
    private Byte[] image;

    public Post toEntity() {
        return Post.builder()
                .member(member)
                .title(title)
                .content(content)
                .view(0)
                .empathy(0)
                .commentNum(0)
                .image(image)
                .build();
    }
}
