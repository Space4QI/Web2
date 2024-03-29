package org.example.web.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.web.DTO.ModelDTO;
import org.example.web.services.BrandService;
import org.example.web.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/model")
public class ModelController {

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private ModelService modelService;

    private final BrandService brandService;

    @Autowired
    public ModelController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/view/{id}")
    public String viewModel(@PathVariable String id, Model model, Principal principal) {
        LOG.log(Level.INFO, "Show view model for " + principal.getName());
        ModelDTO modelDTO = modelService.getModelById(id);
        model.addAttribute("brand", modelDTO);
        return "model/view";
    }

    @GetMapping("/add")
    public String addModel(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show add model for " + principal.getName());
        model.addAttribute("availableBrand", brandService.getAllBrands());

        return "model-add";
    }

    @ModelAttribute("modelModel")
    public ModelDTO initModel() {
        return new ModelDTO();
    }

    @PostMapping("/add")
    public String addModel(@Valid ModelDTO modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {

        LOG.log(Level.INFO, "Show add model for " + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/model/add";
        }
        modelService.addModel(modelModel);

        return "redirect:/model/all";
    }

    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String name, Model model, Principal principal) {
        LOG.log(Level.INFO, "Show details model for " + principal.getName());
        model.addAttribute("modelDetails", modelService.modelDetails(name));

        return "model-details";
    }

    @GetMapping("/model-delete/{model-name}")
    public String deleteModel(@PathVariable("model-name") String modelName, Principal principal) {
        LOG.log(Level.INFO, "Show delete model for " + principal.getName());
        modelService.deleteModel(modelName);
        return "redirect:/model/all";
    }

    @GetMapping("/all")
    public String getAllModels(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show all model for " + principal.getName());
        List<ModelDTO> models = modelService.getAllModels();
        model.addAttribute("allModels", models);
        return "model-all";
    }

    @GetMapping("/model-edit/{model-name}")
    public String updateModel(@PathVariable("model-name") String name, Model model) {
        ModelDTO modelDTO = modelService.findModelByName(name);
        model.addAttribute("model", modelDTO);
        model.addAttribute("availableBrands", brandService.getAllBrands());
        return "model-edit";
    }

    @PostMapping("/model-edit")
    public String updateModel(@ModelAttribute("model") ModelDTO modelDTO) {
        LOG.info("Update Model method called with modelDTO: " + modelDTO);
        modelService.saveModel(modelDTO);
        return "redirect:/model/all";
    }


//    @GetMapping("/edit/{id}")
//    public String editModelForm(@PathVariable UUID id, Model model) {
//        ModelDTO modelDTO = modelService.getModelById(id);
//        model.addAttribute("modelDTO", modelDTO);
//        return "model/editForm";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editModel(@PathVariable UUID id, @ModelAttribute ModelDTO modelDTO) {
//        modelService.updateModel(modelDTO, id);
//        return "redirect:/models";
//    }
}
