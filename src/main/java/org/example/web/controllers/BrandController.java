package org.example.web.controllers;

import jakarta.validation.Valid;
import org.example.web.DTO.BrandDTO;
import org.example.web.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    private ModelAndView modelAndView;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addBrand() {
        return "brand-add";
    }

    @ModelAttribute("brandModel")
    public BrandDTO initBrand() {return new BrandDTO();}

    @PostMapping("/add")
    public String addBrand(@Valid BrandDTO brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/add";
        }
        brandService.addBrand(brandModel);

        return "redirect:/";
    }

//    @GetMapping("/view/{id}")
//    public String viewBrand(@PathVariable UUID id, Model model) {
//        BrandDTO brandDTO = brandService.getBrandById(id);
//        model.addAttribute("brand", brandDTO);
//        return "brand/view";
//    }

    @GetMapping("/brand-details/{id}")
    public String brandDetails(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("brandDetails", brandService.getBrandById(id));

        return "brand-details";
    }

//    @GetMapping("/add")
//    public String createBrandForm() {
//        return "brand/createForm";
//    }

//    @PostMapping("/create")
//    public String createBrand(@ModelAttribute BrandDTO brandDTO) {
//        brandService.saveBrand(brandDTO);
//        return "redirect:/brands";
//    }

//    @GetMapping("/all")
//    public String getAllBrands(Model model) {
//        List<BrandDTO> brands = brandService.getAllBrands();
//        model.addAttribute("brands", brands);
//        return "brand/allBrands";
//    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("brandPage", brandService.getAllBrands());
        return "brands-all";
    }


    @GetMapping("/edit/{id}")
    public String editBrandForm(@PathVariable UUID id, Model model) {
        BrandDTO brandDTO = brandService.getBrandById(id);
        model.addAttribute("brandDTO", brandDTO);
        return "brand/editForm";
    }

    @PostMapping("/edit/{id}")
    public String editBrand(@PathVariable UUID id, @ModelAttribute BrandDTO brandDTO) {
        brandService.updateBrand(id, brandDTO);
        return "redirect:/brands";
    }

    @PostMapping("/delete/{id}")
    public String deleteBrand(@PathVariable("id") UUID id) {
        brandService.deleteBrand(id);
        return "redirect:/brands/all";
    }
}
