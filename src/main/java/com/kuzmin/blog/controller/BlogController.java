package com.kuzmin.blog.controller;

import com.kuzmin.blog.BlogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    private Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping("/")
    public String showHomePage(Model model) {
        logger.info("This is show home page method ");
        setProcessingData(model, BlogConstants.TITLE_HOME_PAGE);
        return "home";
    }

    @GetMapping("/controlPage")
    public String showControlPage(Model model) {
        logger.info("This is control page ");
        setProcessingData(model, BlogConstants.TITLE_LANDING_CONTROL_PAGE);
        return "control-page";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout, Model model) {
        logger.info("This is login page URL ");
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials provided.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out");
        }
        setProcessingData(model, BlogConstants.TITLE_LOGIN_PAGE);
        return "login";
    }

    @ModelAttribute("validUserLogin")
    public boolean isUserLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    @ModelAttribute("currentUserName")
    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute("hasAdminRole")
    public boolean checkIfUserHasAdminRole() {
        return checkIfUserHasRole(BlogConstants.ROLE_ADMIN);
    }

    @ModelAttribute("hasUserRole")
    public boolean checkIfUserHasUserRole() {
        return checkIfUserHasRole(BlogConstants.ROLE_USER);
    }

    private boolean checkIfUserHasRole(String roleName) {
        boolean hasUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals(roleName));
        return hasUserRole;
    }

    /**
     * This method stores various data which are required on presentation layer.
     * @param model
     * @param pageTitle
     */
    private void setProcessingData(Model model,String pageTitle) {
        model.addAttribute(BlogConstants.PAGE_TITLE, pageTitle);
    }
}
