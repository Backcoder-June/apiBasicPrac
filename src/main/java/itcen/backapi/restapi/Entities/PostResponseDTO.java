package itcen.backapi.restapi.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder @EqualsAndHashCode
public class PostResponseDTO {
    private String author;
    private String title;
    private String content;
    private List<String> hashtags;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime regDate;



    // Entity => 응답용 form DTO 로 만들어주는 생성자 ( toDTO 메소드로 만들어도 되고, 이렇게 생성자로 돌려도 되고 )
    public PostResponseDTO(PostEntity entity) {
        this.author = entity.getWriter();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.regDate = entity.getCreatedDate();
        this.hashtags = entity.getHashTags();
    }



}
