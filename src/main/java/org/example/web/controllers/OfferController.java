package org.example.web.controllers;

import org.example.web.DTO.OfferDTO;
import org.example.web.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/view/{id}")
    public String viewOffer(@PathVariable UUID id, Model model) {
        OfferDTO offerDTO = offerService.getOfferById(id);
        model.addAttribute("offer", offerDTO);
        return "offer/view";
    }

    @GetMapping("/create")
    public String createOfferForm() {
        return "userOffer/createForm";
    }

    @PostMapping("/create")
    public String createOffer(@ModelAttribute OfferDTO offerDTO) {
        offerService.saveOffer(offerDTO);
        return "redirect:/offer";
    }

    @GetMapping("/all")
    public String getAllOffer(Model model) {
        List<OfferDTO> offer = offerService.getAllOffer();
        model.addAttribute("offer", offer);
        return "offer/allOffer";
    }

    @GetMapping("/edit/{id}")
    public String editOfferForm(@PathVariable UUID id, Model model) {
        OfferDTO offerDTO = offerService.getOfferById(id);
        model.addAttribute("offerDTO", offerDTO);
        return "offer/editForm";
    }

    @PostMapping("/edit/{id}")
    public String editOffer(@PathVariable UUID id, @ModelAttribute OfferDTO offerDTO) {
        offerService.updateOffer(offerDTO, id);
        return "redirect:/offer";
    }

    @PostMapping("/delete/{id}")
    public String deleteOffer(@PathVariable UUID id) {
        offerService.deleteOffer(id);
        return "redirect:/offer";
    }
}
