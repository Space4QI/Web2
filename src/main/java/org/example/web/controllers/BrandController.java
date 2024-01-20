package org.example.web.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.web.DTO.BrandDTO;
import org.example.web.models.Brand;
import org.example.web.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

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
    public String addBrand(Principal principal) {
        LOG.log(Level.INFO, "Show add brand for " + principal.getName());
        return "brand-add";
    }


    @ModelAttribute("brandModel")
    public BrandDTO initBrand() {
        return new BrandDTO();
    }

    @ModelAttribute("brand")
    public BrandDTO getUpdateBrandDTO() {
        return new BrandDTO();
    }

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
    public String brandDetails(@PathVariable("brand-name") String uuid, Model model, Principal principal) {
        LOG.log(Level.INFO, "Show details brand for " + principal.getName());
        model.addAttribute("brandDetails", brandService.brandDetails(uuid));

        return "brand-details";
    }

    @GetMapping("/brand-delete/{brand-name}")
    public String deleteBrand(@PathVariable("brand-name") String brandName, Principal principal) {
        LOG.log(Level.INFO, "Show delete brand for " + principal.getName());
        brandService.deleteBrand(brandName);
        return "redirect:/brands/all";
    }

    @GetMapping("/all")
    public String getAll(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show all brand for " + principal.getName());
        model.addAttribute("brandPage", brandService.getAllBrands());
        return "brand-all";
    }

    @GetMapping("/brand-edit/{brandId}")
    public String updateBrand(@PathVariable("brandId") String uuid, Model model) {
        BrandDTO brandDTO = brandService.findBrandByUuid(uuid);
        model.addAttribute("brand", brandDTO);
        return "brand-edit";
    }


    @PostMapping("/brand-edit")
    public String updateBrand(@ModelAttribute("brand") BrandDTO brandDTO) {
        LOG.info("Update Brand method called with brandDTO: " + brandDTO);
        brandService.saveBrand(brandDTO);
        return "redirect:/brands/all";
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
