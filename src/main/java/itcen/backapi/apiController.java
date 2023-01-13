package itcen.backapi;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@ResponseBody
public class apiController {

    // 파라미터
    @RequestMapping(value = "/param1", method = RequestMethod.GET)
    public String param1(@RequestParam("userName") String userName,
                         @RequestParam("age") int age) {

        log.info("/param1?userName={}&age={} 가져오기", userName, age);
        return "welcome2param";
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class OrderInfo {
        private Long orderNo;
        private String goodsName;
        private Long goodsAmount;

    }

    // 파라미터 객체
    @GetMapping("/param2")
    public String parma2(OrderInfo orderInfo) {
        log.info("번호 {}, 상품명 {}, 상품개수 {}", orderInfo.orderNo, orderInfo.goodsName, orderInfo.goodsAmount);
        return "welcome";
    }

    // request Body 읽기 ( get - param 으로 안보내고 body 에 숨겨서 보낼 떄 - postman 실험할 때 body - raw - json 으로 보내자 )
    @RequestMapping(value = "/reqbody", method = RequestMethod.POST)
    public String reqbody(@RequestBody OrderInfo orderInfo) {
        log.info("===주문정보=====");
        log.info("번호 {}, 상품명 {}, 상품개수 {}", orderInfo.orderNo, orderInfo.goodsName, orderInfo.goodsAmount);
        return "welcome";
    }

}
