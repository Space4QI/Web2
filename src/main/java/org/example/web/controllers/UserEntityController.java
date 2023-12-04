package org.example.web.controllers;

import org.example.web.DTO.UserEntityDTO;
import org.example.web.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/userEntity")
public class UserEntityController {

    private UserEntityService userEntityService;

    @Autowired
    public void setUserEntityService(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/view/{id}")
    public String viewUserEntity(@PathVariable UUID id, Model model) {
        UserEntityDTO userEntityDTO = userEntityService.getUserEntityById(id);
        model.addAttribute("userEntity", userEntityDTO);
        return "userEntity/view";
    }

    @GetMapping("/create")
    public String createUserEntityForm() {
        return "userEntity/createForm";
    }

    @PostMapping("/create")
    public String createUserEntity(@ModelAttribute UserEntityDTO userEntityDTO) {
        userEntityService.saveUserEntity(userEntityDTO);
        return "redirect:/userEntity";
    }

    @GetMapping("/all")
    public String getAllUserEntity(Model model) {
        List<UserEntityDTO> userEntity = userEntityService.getAllUserEntity();
        model.addAttribute("userEntity", userEntity);
        return "userEntity/allUserEntity";
    }

    @GetMapping("/edit/{id}")
    public String editUserEntityForm(@PathVariable UUID id, Model model) {
        UserEntityDTO userEntityDTO = userEntityService.getUserEntityById(id);
        model.addAttribute("userEntityDTO", userEntityDTO);
        return "userEntity/editForm";
    }

    @PostMapping("/edit/{id}")
    public String editUserEntity(@PathVariable UUID id, @ModelAttribute UserEntityDTO userEntityDTO) {
        userEntityService.updateUserEntity(userEntityDTO, id);
        return "redirect:/userEntity";
    }

    @PostMapping("/delete/{id}")
    public String deleteUserEntity(@PathVariable UUID id) {
        userEntityService.deleteUserEntity(id);
        return "redirect:/userEntity";
    }
}
