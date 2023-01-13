package itcen.backapi.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import itcen.backapi.apiController;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class respController {
    @RequestMapping(value = "/resp1", method = RequestMethod.GET)
    public String resp1() {
        return "welcome2";
    }

    @RequestMapping(value = "/resp2", method = RequestMethod.GET, produces = "application/json")
    public List<String> resp2() {
        log.info("/resp2 요청");
        List<String> list = List.of("자장면", "잠봉", "당슈육", "단무지");
        return list;
    }

    @RequestMapping(value = "/resp3", method = RequestMethod.GET, produces = "application/json") //application/xml 형태
    public apiController.OrderInfo resp3() {
        apiController.OrderInfo orderInfo = new apiController.OrderInfo(12L, "세탁기", 3L);
        return orderInfo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Employee {
        private String empName;
        private String deptName;
        private LocalDate hireDate;
    }

    public List<Employee> empList() {
        return List.of(
                new Employee("순양", "순양자동차", LocalDate.of(2011, 12, 2)),
                new Employee("현다이", "현대자동차", LocalDate.of(2012, 2, 3)),
                new Employee("샘송", "반도체", LocalDate.of(2002, 1, 2))
        );
    }


    // List 객체도 바로 Json 형태로 보내짐
    @RequestMapping(value = "/resp4", method = RequestMethod.GET)
    public List<Employee> allEmployees() {
        return empList();
    }

    // ResponseEntity 타입 사용 => 오류 처리/ header 보내주기 / 등 처리하고 나서 원하는 데이터를 전송할 수 있다.
    @GetMapping("/hi")
    public ResponseEntity<?> hello(String nickname) {
        if (nickname ==null || nickname.equals("")){
            return ResponseEntity.
//                    status(400).
                    badRequest().
                    body("이름을 입력해야 합니다.");
//                    build();
        }

        // ResponseEntity객체 사용하면 응답시에 헤더로 키밸류값 줄 수도 있음
        HttpHeaders headers = new HttpHeaders();
        headers.set("jsoninhead", "value1");
        headers.set("secondjson", "value2");


        return ResponseEntity.
                ok()
                .headers(headers)
                .body(nickname + "님 성공!");
    }











    //





}
