package guru.springframework.controllers;

import guru.springframework.domain.Executant;
import guru.springframework.services.ExecutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by user on 13.06.2016.
 */

@Controller
public class ExecutantController {

    private ExecutantService executantService;

    @Autowired
    public void setExecutantService(ExecutantService executantService ) {
        this.executantService = executantService;
    }

    @RequestMapping(value ="/executants", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("executants", executantService.listAllExecutants());
        return "executants";
    }

    @RequestMapping("executant/{id}")
    public String showExecutant (@PathVariable Integer id, Model model){
        model.addAttribute("executant", executantService.getExecutantById(id));
        return "executantshow";
    }

    @RequestMapping("executant/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("executant", executantService.getExecutantById(id));
        return "executantform";
    }

    @RequestMapping("executant/new")
    public String newExecutant(Model model){
        model.addAttribute("executant", new Executant());
        return "executantform";
    }

    @RequestMapping(value ="executant", method = RequestMethod.POST)
    public String saveExecutant(Executant executant){
        executantService.saveExecutant(executant);
        return "redirect:/executant/" + executant.getId();
    }

    @RequestMapping("executant/delete/{id}")
    public String delete(@PathVariable Integer id){
        executantService.deleteExecutant(id);
        return "redirect:/executants";
    }


}
