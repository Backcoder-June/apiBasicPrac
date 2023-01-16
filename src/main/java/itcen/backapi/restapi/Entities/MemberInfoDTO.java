package itcen.backapi.restapi.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class MemberInfoDTO {

    @NotBlank
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String userName;
    @JsonFormat(pattern = "yyMMdd")
    @Past // 과거 날짜인지 검사
    private LocalDate birthday;


    //@NotBlank => Address 에는 두 개 이상의 정보를 가지고 있는데, 이 상위 객체에다가 @NotBlank 붙이면 안에 어떤 필드에 적용하라고 하는지 모름
    // => 클래스 안에 들어가서 직접 하나하나 @NotBlank 등 검사 해주고, 여기 상위 객체에서는 @Valid  붙여줘서 안에걸 검사하게 하는 것.
    @Valid
    private Address address; // 주소정보 ( 도로명 주소 + 우편번호 )
    @Valid
    private Card card; // 결제 카드 정보 ( 카드번호 + 카드만료 기간 )
}
