package brunch.domain.member.dto;

import brunch.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long userId;
    private String nickName;
    private String job;
    private String info;
    private Byte[] picture;

    public MemberResponseDto(Member member) {
        this.userId = member.getId();
        this.nickName = member.getNickName();
        this.job = member.getJob();
        this.info = member.getInfo();
        this.picture = member.getPicture();
    }
}
