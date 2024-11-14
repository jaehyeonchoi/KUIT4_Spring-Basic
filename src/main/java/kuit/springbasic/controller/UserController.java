package kuit.springbasic.controller;

import kuit.springbasic.db.UserRepository;
import kuit.springbasic.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    //@RequestMapping("signup")
    public String createUserV1(@RequestParam("userId") String userId,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email) {
        log.info("createUser");
        User user = new User(userId, password, name, email);
        userRepository.insert(user);
        log.info("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @RequestMapping("signup")
    public String createUserV2(@ModelAttribute User user) {
        // @RequestParam을 쓸 때, 키 이름과 담을 변수명이 같으면 키 생략 가능
        log.info("createUser");
        userRepository.insert(user);
        log.info("user 회원가입 완료");
        return "redirect:/user/list";
    }

    /**
     * TODO: showUserList
     */

    /**
     * TODO: showUserUpdateForm
     */

    /**
     * TODO: updateUser
     * updateUserV1 : @RequestParam
     * updateUserV2 : @ModelAttribute
     */

}
