package com.zorba.myfinalproject.controller;

import com.zorba.myfinalproject.dto.CustomerDto;
import com.zorba.myfinalproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/saveRecords",consumes = "application/json", produces = "application/json")
    public Boolean saveRecords(@RequestBody List<CustomerDto> customerDetails){
        return customerService.saveRecords(customerDetails);
    }
   @GetMapping(value = "/fetchRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto fetchRecords(@RequestParam(name = "customerId")Integer customerId){
        CustomerDto customer= customerService.fetchItemsSelectedById(customerId);
        return customer;
    }

    @GetMapping(value = "/getAllRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllRecords(){
        return customerService.fetchAllRecords();
    }

    @DeleteMapping(value = "/deleteRecords")
    public Boolean deleteRecords(@RequestParam(name = "customerId")Integer customerId){
        return customerService.deleteCustomerRecords(customerId);

    }
    @PutMapping(value = "/updateRecords", consumes = "application/json", produces = "application/json")
    public String updateRecords(@RequestBody CustomerDto customerDto, @RequestParam Integer customerId){
        String print= "";
        if(customerService.updateRecords(customerDto,customerId)){
            print= "successfully updated";
        }else {
            print = "update failure";
        }
        return print;
    }

}
