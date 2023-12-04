package org.example.web.controllers;

import org.example.web.DTO.UserRoleDTO;
import org.example.web.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/view/{id}")
    public String viewUserRole(@PathVariable UUID id, Model model) {
        UserRoleDTO userRoleDTO = userRoleService.getUserRoleById(id);
        model.addAttribute("userRole", userRoleDTO);
        return "userRole/view";
    }

    @GetMapping("/create")
    public String createUserRoleForm() {
        return "userRole/createForm";
    }

    @PostMapping("/create")
    public String createUserRole(@ModelAttribute UserRoleDTO userRoleDTO) {
        userRoleService.saveUserRole(userRoleDTO);
        return "redirect:/userRoles";
    }

    @GetMapping("/all")
    public String getAllUserRoles(Model model) {
        List<UserRoleDTO> userRoles = userRoleService.getAllUserRoles();
        model.addAttribute("userRoles", userRoles);
        return "userRole/allUserRoles";
    }

    @GetMapping("/edit/{id}")
    public String editUserRoleForm(@PathVariable UUID id, Model model) {
        UserRoleDTO userRoleDTO = userRoleService.getUserRoleById(id);
        model.addAttribute("userRoleDTO", userRoleDTO);
        return "userRole/editForm";
    }

    @PostMapping("/edit/{id}")
    public String editUserRole(@PathVariable UUID id, @ModelAttribute UserRoleDTO userRoleDTO) {
        userRoleService.updateUserRole(userRoleDTO, id);
        return "redirect:/userRoles";
    }

    @PostMapping("/delete/{id}")
    public String deleteUserRole(@PathVariable UUID id) {
        userRoleService.deleteUserRole(id);
        return "redirect:/userRoles";
    }
}
