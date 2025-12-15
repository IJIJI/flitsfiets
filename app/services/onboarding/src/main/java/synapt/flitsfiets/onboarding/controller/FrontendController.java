package synapt.flitsfiets.onboarding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController
{

    //    @GetMapping(value = {"/", "/{path:^(?!api$|dev$|frontend$).+}/**", "/{path:^(?!api$|dev$|frontend$)[^\\.]*}"})
    @GetMapping(value = {
            "/",
            "/{path:^(?!api$|dev$|frontend$|assets$)[^\\.]*}",
            "/{path:^(?!api$|dev$|frontend$|assets$)[^\\.]*}/**",
    })
    public String forward()
    {
        return "forward:/index.html";
    }

}
