package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    /* @Autowired
    * given an instance of this class by the framework.
    * The Framework is going to make a class that implements the CheeseDao interface
    * It's going to create and instance of that class
    * Then it's going to populate this field through dependency injection.
    */

    @Autowired
    CheeseDao cheeseDao;

    @Autowired
    CategoryDao categoryDao;


    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    // handler to display the add cheese form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());// Add Cheese: Type dropdown display
        return "cheese/add";
    }

    // handler to process the add form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);

        cheeseDao.save(newCheese);
        return "redirect:";

        // Spring Boot Binds model class to request handler with the annotation @ModelAttribute
        /* Creates a new object - Cheese newCheese = new Cheese(); (call the default constructor)
         * Goes to the request and looks for a param of the field name -  newCheese.setName(Request.getParameter("name"));
         * set description - newCheese.setDescription(Request.getParameter("description")
         */
        // the field name and the post form name need to match up
    }

    // handler to display the remove form
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    // handler to process the remove form
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    // handler to display the category form
    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    public String category(Model model, @PathVariable int categoryId){
        Category cat = categoryDao.findOne(categoryId);
        model.addAttribute("cheeses", cat.getCheeses());
        model.addAttribute("title", cat.getName() + "Cheeses");
        return "cheese/index";
    }

    // handler to display the cheese edit form
    @RequestMapping(value="edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        // TODO look for cheeseId
        // There was an unexpected error (type=Bad Request, status=400).
        // Required int parameter 'cheeseId' is not present
        model.addAttribute("cheese", cheeseDao.findOne(cheeseId) );
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/edit";
    }

    // handler to process the edit form
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Cheese cheese,
                                  Errors errors, @RequestParam int cheeseId){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese" + cheese.getName());
            return "cheese/edit";  // or add
        }
        Cheese cheese1 = cheeseDao.findOne(cheeseId);
        cheese1.setName(cheese.getName());
        cheese1.setDescription(cheese.getDescription());

        cheeseDao.save(cheese1);
        return "redirect:";
    }


}

///**
// * Created by LaunchCode
// */
//@Controller
//@RequestMapping("cheese")
//public class CheeseController {
//
//    @Autowired
//    private CheeseDao cheeseDao;
//
//    // Request path: /cheese
//    @RequestMapping(value = "")
//    public String index(Model model) {
//
//        model.addAttribute("cheeses", cheeseDao.findAll());
//        model.addAttribute("title", "My Cheeses");
//
//        return "cheese/index";
//    }
//
//    @RequestMapping(value = "add", method = RequestMethod.GET)
//    public String displayAddCheeseForm(Model model) {
//        model.addAttribute("title", "Add Cheese");
//        model.addAttribute(new Cheese());
//        model.addAttribute("cheeseTypes", CheeseType.values());
//        return "cheese/add";
//    }
//
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
//                                       Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Cheese");
//            return "cheese/add";
//        }
//
//        cheeseDao.save(newCheese);
//        return "redirect:";
//    }
//
//    @RequestMapping(value = "remove", method = RequestMethod.GET)
//    public String displayRemoveCheeseForm(Model model) {
//        model.addAttribute("cheeses", cheeseDao.findAll());
//        model.addAttribute("title", "Remove Cheese");
//        return "cheese/remove";
//    }
//
//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
//
//        for (int cheeseId : cheeseIds) {
//            cheeseDao.delete(cheeseId);
//        }
//
//        return "redirect:";
//    }
