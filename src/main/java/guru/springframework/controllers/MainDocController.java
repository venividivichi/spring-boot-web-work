package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Executant;
import guru.springframework.domain.MainDoc;
import guru.springframework.services.CategoryService;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ExecutantService;
import guru.springframework.services.MainDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 13.06.2016.
 */

@Controller
public class MainDocController {

    private MainDocService mainDocService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ExecutantService executantService;

    @Autowired
    public void setMainDocService (MainDocService mainDocService) {
        this.mainDocService = mainDocService;
    }

    @RequestMapping(value = "/mainDocs", method = RequestMethod.GET)
    public String list(@Valid Model model) {
        model.addAttribute("mainDocs", mainDocService.listAllMainDocs());
        return "mainDocs";
    }

    @RequestMapping("mainDoc/{id}")
    public String showMainDoc (@PathVariable Integer id, Model model){
        model.addAttribute("mainDoc", mainDocService.getMainDocById(id));



        return "mainDocshow";
    }

    @RequestMapping("mainDoc/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("mainDoc", mainDocService.getMainDocById(id));
        List<Category> categories = categoryService.listAllCategorys();
        List<Customer> customers = customerService.listAllCustomers();
        List<Executant> executants = executantService.listAllExecutants();
        model.addAttribute("customers", customers);
        model.addAttribute("categories", categories);
        model.addAttribute("executants", executants);
        return "mainDocform";
    }

    @RequestMapping("mainDoc/new")
    public String newMainDoc (@Valid MainDoc mainDoc, BindingResult result, Model model){
        if (result.hasErrors()){
            return "mainDocform";
        }

        List<Category> categories = categoryService.listAllCategorys();
        List<Customer> customers = customerService.listAllCustomers();
        List<Executant> executants = executantService.listAllExecutants();
        model.addAttribute("customers", customers);
        model.addAttribute("categories", categories);
        model.addAttribute("executants", executants);

        model.addAttribute("mainDoc", new MainDoc());
        return "mainDocform";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "mainDoc", method = RequestMethod.POST)
    public String saveMainDoc (@ModelAttribute guru.springframework.domain.ui.MainDoc mainDoc, BindingResult result, Model model){

        MainDoc mainDocDB = null;

        System.out.println("666: "+ mainDoc);

        if (mainDoc.getId()==null){
            mainDocDB = new MainDoc();
        } else {
            mainDocDB = mainDocService.getMainDocById(mainDoc.getId());
        }

        mainDocDB.setDocID(mainDoc.getDocID());
        mainDocDB.setShortDis(mainDoc.getShortDis());
        mainDocDB.setCustomer(customerService.getCustomerById(mainDoc.getCustomer()));

        mainDocDB.setCategory(categoryService.getCategoryById(mainDoc.getCategory()));
        mainDocDB.setExecutant(executantService.getExecutantById(mainDoc.getExecutant()));



        mainDocDB.setDateCome(mainDoc.getDateCome());
        mainDocDB.setDateDone(mainDoc.getDateDone());
        //TODO: fix date fields )))

        mainDocDB = mainDocService.saveMainDoc(mainDocDB);
        model.addAttribute("mainDoc", mainDocDB);
        //model.addAttribute("Caregory", categorys);
        //mainDocService.saveMainDoc(mainDoc);
        return "redirect:/mainDoc/" + mainDocDB.getId();
    }

    @RequestMapping("mainDoc/delete/{id}")
    public String delete(@PathVariable Integer id){
        mainDocService.deleteMainDoc(id);
        return "redirect:/mainDocs";
    }

}
