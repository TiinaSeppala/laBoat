package app.laboat.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import app.laboat.domain.Boat;
import app.laboat.domain.BoatRepository;
import app.laboat.domain.TypeRepository;

@Controller
public class BoatController {
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	//Kaikkien veneitten listaus thymeleafin kautta
    @RequestMapping(value="/boatlist")
    public String boatList(Model model) {	
     	model.addAttribute("boats", boatRepository.findAll());
        return "boatlist";   
    }
    
    //Veneiden haku RESTfulin avulla JSON muodossa
    @GetMapping("/boats")
    public @ResponseBody List<Boat> boatListRest() {
    	return(List<Boat>) boatRepository.findAll();
    }
    
    //Veneen haku ID numeron avulla RESTfullin kautta
    @GetMapping("/boats/{bID}")
    public @ResponseBody Optional<Boat> findBoatRest(@PathVariable("bID") Long bID) {
    	return boatRepository.findById(bID);
    }
    
    
    //lisää vene
    @RequestMapping(value="/addboat")
    public String addBoat(Model model) {
    	model.addAttribute("boat", new Boat());
    	model.addAttribute("types",typeRepository.findAll());
    	return "addboat";
    }
    
    //veneen tallennus
    @PostMapping("/saveboat")
    public String saveBoat(@Valid Boat boat, BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) {
        	model.addAttribute("types",typeRepository.findAll());
            return "addboat";
        } else {
    	boatRepository.save(boat);
    	return "redirect:boatlist";
        }
    }
    

    
    //poisto
    @RequestMapping(value="/deleteboat/{bID}", method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBoat(@PathVariable("bID") Long bID, Model model) {
    	boatRepository.deleteById(bID);
    	return "redirect:../boatlist";
    }
}
