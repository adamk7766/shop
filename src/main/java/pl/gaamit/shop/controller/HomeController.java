package pl.gaamit.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gaamit.shop.model.Product;
import pl.gaamit.shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/")
    public String redirectIndex(){
        return "add";
    }

    @GetMapping("/add")
    public String getAdd(){
        return "add";
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute Product product){
        productRepository.add(product);

        return "redirect:/all";
    }

    @GetMapping("/all")
    public String addAll(ModelMap modelMap) {
        modelMap.put("products", productRepository.findAll());
        return "productList";
    }

    @GetMapping("/productList")
    public String getProductList(){
        return "productList";
    }

    @GetMapping("/products/{name}")
    public String show(@PathVariable String name, ModelMap modelMap){

            try {
                Product product = productRepository.findProductByName(name);
                modelMap.put("product", product);
            } catch (ProductNotFoundException e) {
                modelMap.put("message", "Nie znaleziono produktu");
            }
            return "edit";

    }


    @GetMapping("/products/{name}/delete")
    public String delete(@PathVariable String name, RedirectAttributes redirectAttributes){
        try {
            productRepository.removeProductByName(name);
            redirectAttributes.addFlashAttribute("message","Product "+name+" succesfully deleted");
        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("message","Product was not found");
        }
        finally {
            return "redirect:/all";
        }
    }





}
