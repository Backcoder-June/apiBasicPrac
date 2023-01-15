package itcen.backapi.restapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class viewController {

    // 게시물 등록 form
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String writingForm() {
        return "writingForm";
    }



}
