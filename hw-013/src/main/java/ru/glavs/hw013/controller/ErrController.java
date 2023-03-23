package ru.glavs.hw013.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrController implements ErrorController {

    @GetMapping("/err")
    public String errorPage(HttpServletRequest request, Authentication authentication, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("statusCode", status);
        model.addAttribute("username", authentication.getName());
        if ((Integer) status == 403) {
            return "forbidden";
        }
        return "err";
    }
}
