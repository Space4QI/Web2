package org.example.web.controllers;

import jakarta.validation.Valid;
import org.example.web.DTO.OfferDTO;
import org.example.web.DTO.UserEntityDTO;
import org.example.web.models.Offer;
import org.example.web.services.ModelService;
import org.example.web.services.OfferService;
import org.example.web.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private OfferService offerService;

    private final ModelService modelService;

    private final UserEntityService userEntityService;

    public OfferController(ModelService modelService, UserEntityService userEntityService) {
        this.modelService = modelService;
        this.userEntityService = userEntityService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

//    @GetMapping("/view/{id}")
//    public String viewOffer(@PathVariable UUID id, Model model) {
//        OfferDTO offerDTO = offerService.getOfferById(id);
//        model.addAttribute("offer", offerDTO);
//        return "offer/view";
//    }

    @ModelAttribute("offerModel")
    public OfferDTO initOffer() {return new OfferDTO();}

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("sellers", userEntityService.getAllUserEntity());

        return "offer-add";
    }

    @PostMapping("/add")
    String addOffer(@Valid OfferDTO offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offer/add";
        }
        offerService.addOffer(offerModel);
        return "redirect:/offer/all";
    }


    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("allOffers", offerService.getAllOffer());
        return "offer-all";
    }

//    @GetMapping("/edit/{id}")
//    public String editOfferForm(@PathVariable UUID id, Model model) {
//        OfferDTO offerDTO = offerService.getOfferById(id);
//        model.addAttribute("offerDTO", offerDTO);
//        return "offer/editForm";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editOffer(@PathVariable UUID id, @ModelAttribute OfferDTO offerDTO) {
//        offerService.updateOffer(offerDTO, id);
//        return "redirect:/offer";
//    }

    @GetMapping("/getFilteredOffers")
    public String getFilteredOffers(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                    @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                    Model model) {
        List<Offer> filteredOffers = offerService.getOffersInPriceRange(minPrice, maxPrice);
        model.addAttribute("allOffers", filteredOffers);

        return "offer-all";
    }

    @GetMapping("/all/{minPrice}/{maxPrice}")
    public String getOfferInPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice, Model model) {
        List<Offer> offers = offerService.getOffersInPriceRange(minPrice, maxPrice);
        model.addAttribute("allOffers", offers);
        return "model-all";
    }

    @GetMapping("/offer-details/{offer-id}")
    public String offerDetails(@PathVariable("offer-id") String offerId, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(offerId));

        return "offer-details";
    }

    @GetMapping("/offer-delete/{offer-offerId}")
    public String deleteOffer(@PathVariable("offer-offerId") String offerId) {
        offerService.deleteOffer(offerId);
        return "redirect:/offer/all";
    }
}
