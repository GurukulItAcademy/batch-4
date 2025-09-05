package com.nextalien.accounts.controller;

import com.nextalien.accounts.dto.CustomerDto;
import com.nextalien.accounts.dto.ResponseDto;
import com.nextalien.accounts.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nextalien.accounts.constants.AccountConstants.STATUS_201;
import static com.nextalien.accounts.constants.AccountConstants.STATUS_MESSAGE;

@RestController
@RequestMapping(path = "/api")
public class AccountController {

    @Autowired
    IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){

        iAccountService.createAccount(customerDto);

       return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ResponseDto(STATUS_201,STATUS_MESSAGE));
    }
}

// localhost:8080/api/create