package com.mongo.passion.controller;

import com.mongo.passion.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/mongo/users", produces= MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Delete All Users", notes = "")
    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAll() {
        return userService.deleteAll();
    }

    @ApiOperation(value = "Create New User", notes = "")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@RequestParam(value = "firstName", required = true) String firstName,
                                              @RequestParam(value = "lastName", required = true) String lastName) {
        return userService.postCustomer(firstName, lastName);
    }

    @ApiOperation(value = "Get All Users", notes = "")
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllCustomers();
    }

    @ApiOperation(value = "Get User Using first name", notes = "")
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByFirstName(@RequestParam(value = "firstName", required = true) String firstName) {
        return userService.getCustomerByFirstName(firstName);
    }
}
