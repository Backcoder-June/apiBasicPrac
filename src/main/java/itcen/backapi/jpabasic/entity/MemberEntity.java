package itcen.backapi.jpabasic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "userId")
@Entity
@Table(name = "jpamember")
@Builder
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String password;
    private String nickname;
    @Enumerated(EnumType.ORDINAL) //0, 1 - male, female
    private Gender gender; // enum 타입 만들면 => DB 에서는 integer 타입 => 해당 순번 enum

    @CreationTimestamp  //insert 시점 서버시간 stamp
    private LocalDateTime joinDate;

    @UpdateTimestamp //update 시점 서버시간 stamp
    private LocalDateTime modifedDate;


}
