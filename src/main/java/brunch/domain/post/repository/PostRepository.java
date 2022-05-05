package brunch.domain.post.repository;

import brunch.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @Modifying(clearAutomatically = true)
    @Query("update Post p set p.view = p.view + 1 where p.id = :postId")
    int addView(@Param("postId") Long postId); //조회수 1 증가

    @Modifying(clearAutomatically = true)
    @Query("update Post p set p.empathy = p.empathy + 1 where p.id = :postId")
    int addEmpathy(@Param("postId") Long postId); //공감수 1 증가

    @Modifying(clearAutomatically = true)
    @Query("update Post p set p.empathy = p.empathy - 1 where p.id = :postId")
    int removeEmpathy(@Param("postId") Long postId); //공감수 1 감소
}
