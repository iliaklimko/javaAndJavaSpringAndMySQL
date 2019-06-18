package IliaKlimko.controller;

import IliaKlimko.repos.UserRepo;
import IliaKlimko.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    AuthenticationManager authManager;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping (value = {"/", "/login"}, method = RequestMethod.POST)
    public String login(User user,HttpServletRequest req, Model model) {
        if (userRepo.findByUsername(user.getUsername())==null) {
            model.addAttribute("message","Invalid username or password");
            return "login";
        }
        if (userRepo.findUserByPassword(user.getPassword())==null){
            model.addAttribute("message","Invalid username or password");
            return "login";
        }
        if (!(userRepo.findUserByPassword(user.getPassword())).isActive()) {
            model.addAttribute("message","Account is blocking");
            return "login";
        }
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication auth = authManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);

        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return "redirect:/main";
    }

    @RequestMapping(value = {"/main"}, method = RequestMethod.GET)
    public String main(@AuthenticationPrincipal User user, Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        if ((userRepo.findByEmail(user.getEmail()) != null) && (userRepo.findByEmail(user.getEmail()).isActive()))
            return "main";
        return "login";
    }


    @RequestMapping(value = {"panel"}, params = {"blockUser"}, method = RequestMethod.POST)
    public String blockUser(@RequestParam(required = false, name = "box") Set<Long> IDs, Model model) {

        if (IDs != null) {
            for (Long id : IDs) {
                User user = userRepo.findUserById(id);
                user.setActive(false);
                userRepo.save(user);


            }

        }


        return "redirect:/main";
    }

    @RequestMapping(value = {"panel"}, params = {"activateUser"}, method = RequestMethod.POST)
    public String activateUser(@RequestParam(required = false, name = "box") Set<Long> IDs, Model model) {

        if (IDs != null) {
            for (Long id : IDs) {
                User user = userRepo.findUserById(id);
                user.setActive(true);
                userRepo.save(user);


            }

        }


        return "redirect:/main";
    }

    @RequestMapping(value = {"panel"}, params = {"deleteUser"}, method = RequestMethod.POST)
    public String deleteUser(@RequestParam(required = false, name = "box") Set<Long> IDs, Model model) {

        if (IDs != null) {
            for (Long id : IDs) {
                userRepo.deleteById(id);
            }

        }


        return "redirect:/main";
    }

    @RequestMapping(value = {"panel"}, params = {"logout"}, method = RequestMethod.POST)
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler();
        ctxLogOut.logout(httpServletRequest, httpServletResponse, auth);
        return "redirect:/login";
    }


}
