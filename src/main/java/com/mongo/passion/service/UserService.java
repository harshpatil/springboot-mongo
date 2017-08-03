package com.mongo.passion.service;

import com.mongo.passion.model.User;
import com.mongo.passion.repository.UserRepository;
import com.mongo.passion.util.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> deleteAll() {

        try{
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiError("Error in deleting User"), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Object> postCustomer(String firstName, String lastName) {

        try{
            User user = userRepository.save(new User(firstName, lastName));
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiError("Error in posting User"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getAllCustomers() {

        try{
            List<User> userList = userRepository.findAll();
            for (User user : userList) {
                log.info(user.getId());
            }
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiError("Error in getting all Customers"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getCustomerByFirstName(String firstName) {

        try{
            User user = userRepository.findByFirstName(firstName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiError("Error in getting User"), HttpStatus.BAD_REQUEST);
        }
    }

}
