package pl.altkom.CarShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping(path = "/")
    public String prepareMenuPage(final Model model){
        return "index.html";
    }
}
