package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<UserDTO> list(Pageable pageable){
		return userService.list(pageable);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO getById(@PathVariable("id") Long id) {
			  return userService.byId(id);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public UserDTO add(@RequestBody UserDTO userDTO) {
		return userService.save(userDTO);
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserDTO update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
    	
    	UserDTO user = userService.byId(id);
    	
    	user.setName(userDTO.getName());
    	user.setLogin(userDTO.getLogin());
    	user.setPassword(userDTO.getPassword());
   
        return userService.save(user);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

	
	
}
