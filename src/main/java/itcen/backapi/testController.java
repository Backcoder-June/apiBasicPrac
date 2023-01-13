package itcen.backapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.io.Console;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@Controller
@Slf4j
@RequestMapping("/backapi")
public class testController {

    @RequestMapping(value = "/welcome", method = {GET, POST})
    public String welcome(HttpServletRequest request) {
        log.info("여기에 메세지 넣어줌 {}", request.getMethod());
        return "welcome2";
    }

    @ResponseBody
    @RequestMapping(value = "/hi", method = GET)
    public String hi() {
        return "hihihihihi";
    }


    @ResponseBody
    @RequestMapping(value = "/header", method = GET)
    public String header(HttpServletRequest request) {
        String host = request.getHeader("Host");
        String accept = request.getHeader("Accept");
        log.info("===========header info============");
        log.info("# header-host : {}", host);
        log.info("# header-accept : {}", accept);
        return "hgh";
    }
}
