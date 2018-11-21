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
import app.laboat.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
    private UserRepository userRepository; 
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
    @RequestMapping(value = "signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }	
    

	// Luo uusi käyttäjä
	// Tarkista onko käyttäjä jo olemassa
	// Lomakkeen validointi
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // virheiden validointi
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // mätsääkö salasana	
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (userRepository.findByUsername(signupForm.getUsername()) == null) { // Onko käyttäjää olemassa
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