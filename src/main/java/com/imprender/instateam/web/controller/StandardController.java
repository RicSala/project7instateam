package com.imprender.instateam.web.controller;


import com.imprender.instateam.model.StandardModel;
import com.imprender.instateam.service.StandarService;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
public class StandardController {

    //GENERAL SCHEMA OF LAYERS: database (we dont code this)--> Hibernate (we dont code this one) --> (autowired in) DAO --> (autowired in) Service --> (autowired in)
    //We configure our database with the dataSource() bean, we use that to configure our ORM - Hibernate - in sesionfactorybean to configure that,
    //the dao layer uses the session factory (autowired) to communicate with hibernate throug sessions, and the service layer use the autowire dao to access that data
    //in our case our controller.

    //Autowiring a SessionFactory in our Autowiring controller, we can do it because it is defined as a Spring Bean! :)
    //Spring will use the reutrn value of the Bean method in our data config class because its return type (a local session factory bean)
    //implements an interface named factory bean

    //We change, an instead of autowiriring a session factory, we AW a Service.

    @Autowired
    private StandarService standarService;

    //By default, this is a get request!
    @RequestMapping("/standardUri")
    public String standardController(Model model) {

        List<StandardModel> standards = standarService.findAll();
        //Review methods to retrieve and save data in hibernate --> Criteria is deprecated
        //Warning? https://teamtreehouse.com/library/fetching-data-with-hibernate-in-spring 2:45

        model.addAttribute("name", standards);
        return "standardHtmlTemplate";
    }


    @RequestMapping(value = "standard/add", method = RequestMethod.POST)
    //Spring will assemble the Object for ur
    public String addStandardController(@Valid StandardModel standardModel, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            //Redirect back to the from
            //We want to pass the data of to the redirection, so we can keep what the user just entered, but we can't
            //put it in a redirection (get method does not have payload). For that, we have the redirect attributes (Todo: check how they work)

            //and add a flash message --> CHECK THE LAST APPENDED TEXT! tODO: CHECK THIS!
            //In the html, we can do--> th:if="${#fields.hasErrors('name')} th:errors="*{name}"
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.standardModel", result);

            //add category to redirectparameter if invalid data was received
            redirectAttributes.addFlashAttribute("standardModel", standardModel);

            //flash attributes only survives ONE redirection

            return "redirect:/standard/add";
        }
        standarService.save(standardModel);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Your standard was succesfully added!", FlashMessage.Status.SUCCESS));

        //Be careful, this is the URI, not the view to render!
        return "redirect:/standardUri";

        //Todo: why do we need to do th:action="@{categories}" if it just a text..?
        //Forms: inputs and selects require name attributes in order to be in the payload of the request
        //FORMS: button type="submit"
        //Instead of add name attributes ourselves, let thymeleaf to it --> object binding th:object...
        //...
    }


    @RequestMapping("standard/add")
    public String newStandard(Model model) {

        if (!model.containsAttribute("standard")) {

            //If model does not contain object call ... create a new one and include it in the model
            model.addAttribute("standard", new StandardModel());
        }

        //we give a new category so we can bind it in the view of th:object and recover it from the post easily
        // putting th:field="*{field}" Advantages: if field have any value, those values would be use in the form by thymeleaf
        // name attributes are auto, will allow us to use the same html element for editing (because of the first adv!) and to redirect again...
        // min 5:50 https://teamtreehouse.com/library/spring-with-hibernate/getting-started-with-crud-in-spring-hibernate/html-form-for-adding-a-category


        return "standard/form";
    }
}




