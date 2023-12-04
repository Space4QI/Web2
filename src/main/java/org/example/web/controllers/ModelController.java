package org.example.web.controllers;

import org.example.web.DTO.ModelDTO;
import org.example.web.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/model")
public class ModelController {


    private ModelService modelService;

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/view/{id}")
    public String viewModel(@PathVariable UUID id, Model model) {
        ModelDTO modelDTO = modelService.getModelById(id);
        model.addAttribute("brand", modelDTO);
        return "model/view";
    }

    @GetMapping("/create")
    public String createModelForm() {
        return "model/createForm";
    }

    @PostMapping("/create")
    public String createModel(@ModelAttribute ModelDTO modelDTO) {
        modelService.saveModel(modelDTO);
        return "redirect:/models";
    }

    @GetMapping("/all")
    public String getAllModels(Model model) {
        List<ModelDTO> models = modelService.getAllModels();
        model.addAttribute("models", models);
        return "model/allModels";
    }

    @GetMapping("/edit/{id}")
    public String editModelForm(@PathVariable UUID id, Model model) {
        ModelDTO modelDTO = modelService.getModelById(id);
        model.addAttribute("modelDTO", modelDTO);
        return "model/editForm";
    }

    @PostMapping("/edit/{id}")
    public String editModel(@PathVariable UUID id, @ModelAttribute ModelDTO modelDTO) {
        modelService.updateModel(modelDTO, id);
        return "redirect:/models";
    }

    @PostMapping("/delete/{id}")
    public String deleteModel(@PathVariable UUID id) {
        modelService.deleteModel(id);
        return "redirect:/models";
    }
}
