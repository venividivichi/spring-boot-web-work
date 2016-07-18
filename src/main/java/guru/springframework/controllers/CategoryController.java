package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
    @ModelAttribute("category")
    public List<Category> populateTypes() {
        return Arrays.asList(categoryService.listAllCategorys());
    }
*/

    @RequestMapping(value = "/categorys", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("categorys", categoryService.listAllCategorys());
        return "categorys";
    }

    @RequestMapping("category/{id}")
    public String showCategory (@PathVariable Integer id, Model model){
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categoryshow";
    }

    @RequestMapping("category/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categoryform";
    }

    @RequestMapping("category/new")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @RequestMapping(value = "category", method = RequestMethod.POST)
    public String saveCategory(Category category){
        categoryService.saveCategory(category);
        return "redirect:/category/" + category.getId();
    }

    @RequestMapping("category/delete/{id}")
    public String delete(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return "redirect:/categorys";
    }

}
