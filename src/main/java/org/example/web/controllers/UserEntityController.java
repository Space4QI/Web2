package org.example.web.controllers;

import jakarta.validation.Valid;
import org.example.web.DTO.BrandDTO;
import org.example.web.DTO.ModelDTO;
import org.example.web.DTO.UserEntityDTO;
import org.example.web.models.UserEntity;
import org.example.web.models.UserRole;
import org.example.web.services.UserEntityService;
import org.example.web.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/userEntity")
public class UserEntityController {

    private UserEntityService userEntityService;

    private final UserRoleService userRoleService;

    @Autowired
    public UserEntityController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @Autowired
    public void setUserEntityService(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

//    @GetMapping("/view/{id}")
//    public String viewUserEntity(@PathVariable UUID id, Model model) {
//        UserEntityDTO userEntityDTO = userEntityService.getUserEntityById(id);
//        model.addAttribute("userEntity", userEntityDTO);
//        return "userEntity/view";
//    }

    @GetMapping("/add")
    public String addUserEntity(Model model) {
        model.addAttribute("availableUserRole", userRoleService.getAllUserRoles());

        return "userEntity-add";
    }

    @ModelAttribute("userModel")
    public UserEntityDTO initUser() {return new UserEntityDTO();}

    @PostMapping("/add")
    public String addUser(@Valid UserEntityDTO userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/userEntity/add";
        }
        userEntityService.addUserEntity(userModel);

        return "redirect:/userEntity/all";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
       model.addAttribute("allUsers", userEntityService.getAllUserEntity());
        return "userEntity-all";
    }

//    @GetMapping("/edit/{id}")
//    public String editUserEntityForm(@PathVariable UUID id, Model model) {
//        UserEntityDTO userEntityDTO = userEntityService.getUserEntityById(id);
//        model.addAttribute("userEntityDTO", userEntityDTO);
//        return "userEntity/editForm";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editUserEntity(@PathVariable UUID id, @ModelAttribute UserEntityDTO userEntityDTO) {
//        userEntityService.updateUserEntity(userEntityDTO, id);
//        return "redirect:/userEntity";
//    }

    @GetMapping("/userEntity-details/{userEntity-username}")
    public String userEntityDetails(@PathVariable("userEntity-username") String userEntityUsername, Model model) {
        model.addAttribute("userEntityDetails", userEntityService.userEntityDetails(userEntityUsername));

        return "userEntity-details";
    }

    @GetMapping("/userEntity-delete/{userEntity-username}")
    public String deleteUserEntity(@PathVariable("userEntity-username") String userEntityUsername) {
        userEntityService.deleteUserEntity(userEntityUsername);
        return "redirect:/userEntity/all";
    }
}
