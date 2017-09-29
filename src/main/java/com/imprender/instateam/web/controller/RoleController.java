package com.imprender.instateam.web.controller;


import com.imprender.instateam.model.Role;
import com.imprender.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/roles")
    public String listRoles(Model model) {

        if (!model.containsAttribute("role")) {
            model.addAttribute("role", new Role());
        }

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @RequestMapping(value = "/roles/add", method = RequestMethod.POST)
    public String addRole(Model model, @Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            redirectAttributes.addFlashAttribute("role", role);
            redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage("The role couldn't be added", FlashMessage.Status.FAILURE));
            return "redirect:/roles";
        }
        roleService.save(role);
        return "redirect:/roles";
    }
}
