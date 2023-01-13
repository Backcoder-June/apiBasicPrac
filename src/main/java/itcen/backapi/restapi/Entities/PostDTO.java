package itcen.backapi.restapi.Entities;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PostDTO {  // 실제로 조작할 필요한 데이터들로만 추려서 만든 Entity 의 작은 버전 그릇

    private Long id;
    private String writer;
    private String title;
    private String content;
    private List<String> hashTags;


    //toEntity
    public PostEntity toEntity() {
        return PostEntity.builder()
                .id(PostEntity.sequence++)
                .title(this.title)
                .content(this.content)
                .hashTags(this.hashTags)
                .writer(this.writer)
                .createdDate(LocalDateTime.now())
                .build();
    }

    public PostEntity patchToEntity() {
        return PostEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .hashTags(this.hashTags)
                .writer(this.writer)
                .createdDate(LocalDateTime.now())
                .build();
    }



}




