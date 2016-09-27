package guru.springframework.controllers;

import guru.springframework.domain.*;
import guru.springframework.services.CategoryService;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ExecutantService;
import guru.springframework.services.MainDocService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import guru.springframework.util.FileValidator;

import guru.springframework.domain.FileBucket;
/**
 * Created by user on 13.06.2016.
 */

@Controller
public class MainDocController {

    @Autowired
    private FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

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

    @RequestMapping(value = "mainDoc/download/{id}", method = RequestMethod.GET)
    public void download(HttpServletResponse response, @PathVariable("id") Integer id) {
//        InputStream stream = new
        try {
            MainDoc doc = mainDocService.getMainDocById(id);
            String mimeType = "application/octet-stream";
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + doc.getContent() + "\" "));
            response.setContentType(mimeType);
            byte[] file = Files.readAllBytes(Paths.get("D:\\test\\" + doc.getContent()));
            FileCopyUtils.copy(file, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("mainDoc/new")
    public String newMainDoc (@Valid MainDoc mainDoc, BindingResult result, Model model){
        if (result.hasErrors()){
            return "mainDocform";
        }
      /*  try {
            System.err.println( Files.readAllBytes(Paths.get("D:\\test\\Kishman_CheckList.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        */
        //storage service/stor_file, bean. spring io,

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
    public String saveMainDoc (@ModelAttribute guru.springframework.domain.ui.MainDoc mainDoc,
                               @Valid @RequestParam("fileBucket") MultipartFile fileBucket,
                               BindingResult result,
                               Model model){

        MainDoc mainDocDB = null;
        try {
            Files.write(Paths.get("D:\\test\\" + fileBucket.getOriginalFilename()), fileBucket.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileBucket fileModel = new FileBucket();
        System.out.println("Fetching file: " + fileBucket);

//        MultipartFile multipartFile = fileBucket.getFile();
        model.addAttribute("fileBucket", fileModel);

        System.out.println("666: "+ mainDoc);

        if (mainDoc.getId()==null){
            mainDocDB = new MainDoc();
        } else {
            mainDocDB = mainDocService.getMainDocById(mainDoc.getId());
        }

        mainDocDB.setDocID(mainDoc.getDocID());
        mainDocDB.setShortDis(mainDoc.getShortDis());
        mainDocDB.setCustomer(customerService.getCustomerById(mainDoc.getCustomer()));
        mainDocDB.setContent(fileBucket.getOriginalFilename());
        mainDocDB.setCategory(categoryService.getCategoryById(mainDoc.getCategory()));
        mainDocDB.setExecutant(executantService.getExecutantById(mainDoc.getExecutant()));

        //saveDocument(fileBucket, );

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
