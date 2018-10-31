package app.laboat.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.laboat.domain.SignUpForm;
import app.laboat.domain.User;
import app.laboat.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
    private UserRepository userRepository; 
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
    @RequestMapping(value = "signupform")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignUpForm());
        return "signupform";
    }	
    
// Luo uusi käyttäjä
// Tarkista onko käyttäjä jo olemassa
// Lomakkeen validointi
// @param signupForm
// @param bindingResult
// @return

    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
    	
    	// validointivirheet
    	if (!bindingResult.hasErrors()) {
    		
    		// tarkista salasanojen mätsäys
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { 	
	    		String password = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPassword = bc.encode(password);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPassword);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	
		    	// Tarkista löytyykö käyttäjää
		    	if (userRepository.findByUsername(signupForm.getUsername()) == null) { 
		    		userRepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "* username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "* passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
}