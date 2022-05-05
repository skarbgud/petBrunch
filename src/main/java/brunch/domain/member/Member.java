package brunch.domain.member;

import brunch.domain.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nickName;
    private String job;
    private String info;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Byte[] picture;

    @Builder
    private Member(String nickName, String job, String info, Byte[] picture) {
        this.nickName = nickName;
        this.job = job;
        this.info = info;
        this.picture = picture;
    }
}
