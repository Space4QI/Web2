package org.example.web.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.web.DTO.BrandDTO;
import org.example.web.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.UUID;


@Controller
@RequestMapping("/brands")
public class BrandController {

    private static final Logger LOG = LogManager.getLogger(Controller.class);

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
    public String addBrand(@Valid BrandDTO brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {

        LOG.log(Level.INFO, "Show add brand for " + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/add";
        }
        brandService.addBrand(brandModel);

        return "redirect:/brands/all";
    }

    @GetMapping("/brand-details/{brand-name}")
    public String brandDetails(@PathVariable("brand-name") String brandName, Model model) {
        model.addAttribute("brandDetails", brandService.brandDetails(brandName));

        return "brand-details";
    }

    @GetMapping("/brand-delete/{brand-name}")
    public String deleteBrand(@PathVariable("brand-name") String brandName) {
        brandService.deleteBrand(brandName);
        return "redirect:/brands/all";
    }

    @GetMapping("/all")
    public String getAll(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show all brand for " + principal.getName());
        model.addAttribute("brandPage", brandService.getAllBrands());
        return "brand-all";
    }


//    @GetMapping("/edit/{id}")
//    public String editBrandForm(@PathVariable UUID id, Model model) {
//        BrandDTO brandDTO = brandService.getBrandById(id);
//        model.addAttribute("brandDTO", brandDTO);
//        return "brand/editForm";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editBrand(@PathVariable UUID id, @ModelAttribute BrandDTO brandDTO) {
//        brandService.updateBrand(id, brandDTO);
//        return "redirect:/brands";
//    }
}
