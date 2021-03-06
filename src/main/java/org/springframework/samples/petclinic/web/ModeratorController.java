package org.springframework.samples.petclinic.web;
//package org.springframework.samples.petclinic.web;
//
//import java.util.Collection;
//import java.util.Map;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.samples.petclinic.model.Owner;
//import org.springframework.samples.petclinic.service.AuthoritiesService;
//import org.springframework.samples.petclinic.service.OwnerService;
//import org.springframework.samples.petclinic.service.VetService;
//import org.springframework.samples.petclinic.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * @author Juergen Hoeller
// * @author Ken Krebs
// * @author Arjen Poutsma
// * @author Michael Isvy
// */
//@Controller
//public class ModeradorController {
//
//	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
//
//	private final OwnerService ownerService;
//
//	@Autowired
//	public moderadorController(OwnerService ownerService, UserService userService, AuthoritiesService authoritiesService) {
//		this.ownerService = ownerService;
//	}
//
//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}
//
//	@GetMapping(value = "/owners/new")
//	public String initCreationForm(Map<String, Object> model) {
//		Owner owner = new Owner();
//		model.put("owner", owner);
//		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
//	}
//
//	@PostMapping(value = "/owners/new")
//	public String processCreationForm(@Valid Owner owner, BindingResult result) {
//		if (result.hasErrors()) {
//			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//			//creating owner, user and authorities
//			this.ownerService.saveOwner(owner);
//			
//			return "redirect:/owners/" + owner.getId();
//		}
//	}
//
//	@GetMapping(value = "/owners/find")
//	public String initFindForm(Map<String, Object> model) {
//		model.put("owner", new Owner());
//		return "owners/findOwners";
//	}
//
//	@GetMapping(value = "/owners")
//	public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {
//
//		// allow parameterless GET request for /owners to return all records
//		if (owner.getLastName() == null) {
//			owner.setLastName(""); // empty string signifies broadest possible search
//		}
//
//		// find owners by last name
//		Collection<Owner> results = this.ownerService.findOwnerByLastName(owner.getLastName());
//		if (results.isEmpty()) {
//			// no owners found
//			result.rejectValue("lastName", "notFound", "not found");
//			return "owners/findOwners";
//		}
//		else if (results.size() == 1) {
//			// 1 owner found
//			owner = results.iterator().next();
//			return "redirect:/owners/" + owner.getId();
//		}
//		else {
//			// multiple owners found
//			model.put("selections", results);
//			return "owners/ownersList";
//		}
//	}
//
//	@GetMapping(value = "/owners/{ownerId}/edit")
//	public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
//		Owner owner = this.ownerService.findOwnerById(ownerId);
//		model.addAttribute(owner);
//		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
//	}
//
//	@PostMapping(value = "/owners/{ownerId}/edit")
//	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
//			@PathVariable("ownerId") int ownerId) {
//		if (result.hasErrors()) {
//			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//			owner.setId(ownerId);
//			this.ownerService.saveOwner(owner);
//			return "redirect:/owners/{ownerId}";
//		}
//	}
//
//	/**
//	 * Custom handler for displaying an owner.
//	 * @param ownerId the ID of the owner to display
//	 * @return a ModelMap with the model attributes for the view
//	 */
//	@GetMapping("/owners/{ownerId}")
//	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
//		ModelAndView mav = new ModelAndView("owners/ownerDetails");
//		mav.addObject(this.ownerService.findOwnerById(ownerId));
//		return mav;
//	}
//
//}
