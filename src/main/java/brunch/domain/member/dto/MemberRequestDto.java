package brunch.domain.member.dto;

import brunch.domain.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequestDto {

    private String nickName;
    private String job;
    private String info;
    private Byte[] picture;

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .job(job)
                .info(info)
                .picture(picture)
                .build();
    }
}
