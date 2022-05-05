package brunch.domain.post.repository;

import brunch.domain.post.dto.PostQueryDto;

import java.util.List;

public interface PostRepositoryCustom {

    List<PostQueryDto> searchByAccuracyOrder(String keyword); //정확도순
    List<PostQueryDto> searchByDateOrder(String keyword); //최신순
}
