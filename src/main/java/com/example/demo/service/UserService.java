package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	

    public Page<UserDTO> list(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        int totalElements = userPage.getNumberOfElements();

        return userMapper.userPageToUserDTOPage(userPage, pageable, totalElements);
    }
    
    @Transactional
    public UserDTO save(UserDTO userDTO) {
    	User user = userMapper.userDTOToUser(userDTO);
    	user = userRepository.save(user);
    	
    	return userMapper.userToUserDTO(user);
    }
    
    
    public UserDTO byId(Long id) {
    	User user = userRepository.getOne(id);   	
    	return userMapper.userToUserDTO(user);
  
    }
    
    public void delete(Long id) {
    	userRepository.deleteById(id);	
    }
}




















