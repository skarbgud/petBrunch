package brunch.domain.member;

import brunch.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void uploadImage(Long id, MultipartFile file) throws Exception {
        Member member = this.findMember(id);
        Byte[] image = new Byte[file.getBytes().length];
        int i = 0;

        for (Byte b : image) {
            image[i++] = b;
        }

        member.updateImage(image);
    }

    @Transactional(readOnly = true)
    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Can't find this member." + id));
    }
}
