package itcen.backapi.rdbjpa.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
//@ToString(exclude = "employee") // emplyee department 서로 가지고있어서 stackoverFlow
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "deptId")
@Builder
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    private String deptName;
//    @OneToMany(mappedBy = "department")
//    private Employee employee;

}
