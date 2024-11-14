package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.db.UserRepository;
import kuit.springbasic.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kuit.springbasic.util.UserSessionUtils;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    /**
     * TODO: showUserForm
     */
    @RequestMapping("/form")
    public String showUserForm() {
        log.info("showUserForm");
        return "/user/form";
    }

    /**
     * TODO: createUser
     * createUserV1 : @RequestParam
     * createUserV2 : @ModelAttribute
     */
    //@RequestMapping("/signup")
    public String createUserV1(@RequestParam("userId") String userId,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email) {
        log.info("createUserV1");
        User user = new User(userId, password, name, email);
        userRepository.insert(user);
        log.info("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @RequestMapping("/signup")
    public String createUserV2(@ModelAttribute User user) {
        // @ModelAttribute:모델 객체에 파라미터들을 넘겨서 생성 및 초기화
        log.info("createUserV2");
        userRepository.insert(user);
        log.info("user 회원가입 완료");
        return "redirect:/user/list";
    }

    /**
     * TODO: showUserList
     */
    @RequestMapping("/list")
    public String showUserList(HttpServletRequest request, Model model) {
        log.info("showUserList");
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLoggedIn(session)) {
            model.addAttribute("users", userRepository.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

    /**
     * TODO: showUserUpdateForm
     */

    /**
     * TODO: updateUser
     * updateUserV1 : @RequestParam
     * updateUserV2 : @ModelAttribute
     */

}
