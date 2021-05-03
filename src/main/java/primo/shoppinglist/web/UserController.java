package primo.shoppinglist.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import primo.shoppinglist.data.bindings.UserRegisterBindingModel;
import primo.shoppinglist.data.services.UserServiceModel;
import primo.shoppinglist.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    private ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        modelAndView.addObject("userRegisterBindingModel", new UserRegisterBindingModel());
        return modelAndView;
    }

    @PostMapping("/register")
    private String registerConfirm(
            @Valid UserRegisterBindingModel userRegisterBindingModel
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()
                || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes
                    .addFlashAttribute(
                            "org.springframework" +
                                    ".validation.BindingResult" +
                                    ".userRegisterBindingModel"
                            , bindingResult);
            return "redirect:register";
        }


        userService.register(
                modelMapper.map(
                        userRegisterBindingModel,
                        UserServiceModel.class
                )
        );

        return "redirect:login";
    }
}
