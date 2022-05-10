package brunch.domain.member.dto;

import brunch.domain.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class MemberRequestDto {

    @NotEmpty private String nickName;
    @NotEmpty private String job;
    @NotEmpty private String info;
    private Byte[] image;

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .job(job)
                .info(info)
                .image(image)
                .build();
    }
}
