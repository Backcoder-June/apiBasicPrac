package itcen.backapi.restapi.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @Builder
public class PostEntity {
    public static long sequence = 1L;
    private Long id;
    private String writer;
    private String title;
    private String content;
    private List<String> hashTags;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;



}
