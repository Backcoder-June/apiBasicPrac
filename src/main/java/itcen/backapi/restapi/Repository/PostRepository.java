package itcen.backapi.restapi.Repository;

import itcen.backapi.restapi.Entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO post (writer, title, content, createdDate) " +
            "VALUES (:#{#postEntity.writer}, :#{#postEntity.title}, :#{#postEntity.content}, now())", nativeQuery = true)
    void savePost(@Param("postEntity")PostEntity postEntity);

    @Query(value = "SELECT * FROM post WHERE title = :#{#title}", nativeQuery = true)
    PostEntity findByTitle(@Param("title")String title);

}
