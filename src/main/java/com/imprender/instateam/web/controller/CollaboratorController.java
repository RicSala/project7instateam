package com.imprender.instateam.web.controller;

import com.imprender.instateam.model.Collaborator;
import com.imprender.instateam.service.CollaboratorService;
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
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/collaborators")
    public String collaborators(Model model) {

        if (!model.containsAttribute("collaborator")) {
            model.addAttribute("collaborator", new Collaborator());
        }
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "collaborators";
    }

    @RequestMapping(value = "/collaborators/add", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        System.out.println("Executed");
        if (bindingResult.hasErrors()) {
            System.out.println("but with errors");
            System.out.println(bindingResult.getFieldError());
            redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage("The collaborator was not created", FlashMessage.Status.FAILURE));
            redirectAttributes.addFlashAttribute("collaborator", collaborator);
            return "redirect:/collaborators";
        }

        collaboratorService.save(collaborator);
        redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage("The collaborator was created", FlashMessage.Status.SUCCESS));
        return "redirect:/collaborators";
    }
}
