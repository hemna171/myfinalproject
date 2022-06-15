package com.zorba.myfinalproject.service;

import com.zorba.myfinalproject.dto.CustomerDto;
import com.zorba.myfinalproject.dto.ItemsDto;
import com.zorba.myfinalproject.entity.Customer;
import com.zorba.myfinalproject.entity.Items;
import com.zorba.myfinalproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    public Boolean saveRecords(List<CustomerDto> customerDtos){

        for(CustomerDto custDTO:customerDtos){
            Customer customer= new Customer();
            if(this.validateCustomerName(custDTO.getCustName())&& this.validateMobNum(custDTO.getMobileNumber())){
                customer.setCustName(custDTO.getCustName());
                customer.setCountry(custDTO.getCountry());
                customer.setMobNum(custDTO.getMobileNumber());

                List<ItemsDto> itemsDtos= custDTO.getItems();
                for(ItemsDto itemsDto: itemsDtos){
                    Items items= new Items();
                    items.setFoodName(itemsDto.getName());
                    items.setQuantity(itemsDto.getQuantity());

                    customer.addItems(items);
                    customerRepository.save(customer);
                }
            }

        }
        return true;
    }
    public  CustomerDto fetchItemsSelectedById(Integer customerId){
        Customer customer= new Customer();
        CustomerDto customerDto= new CustomerDto();
        Optional<Customer> customer1= customerRepository.findById(customerId);
        if(customer1!= null){
            customer= customer1.get();
            customerDto.setCustName(customer.getCustName());
            customerDto.setMobileNumber(customer.getMobNum());
            customerDto.setCountry(customer.getCountry());

            List<ItemsDto> itemsDtoList= new ArrayList<>();
            for(Items items:customer.getItemsList()){
                ItemsDto itemsDto= new ItemsDto();
                itemsDto.setName(items.getFoodName());
                itemsDto.setQuantity(items.getQuantity());

                itemsDtoList.add(itemsDto);
            }
            customerDto.setItems(itemsDtoList);
        }
        return customerDto;
    }
    public List<CustomerDto> fetchAllRecords(){
        Iterable<Customer> customerList=customerRepository.findAll();
        List<CustomerDto> customerDtoList= new ArrayList<>();
        Iterator iterator = customerList.iterator();
        while (iterator.hasNext()){
            Customer customer= (Customer) iterator.next();
            CustomerDto customerDto= new CustomerDto();
            customerDto.setCustName(customer.getCustName());
            customerDto.setMobileNumber(customer.getMobNum());
            customerDto.setCountry(customer.getCountry());

            List<ItemsDto> itemsDtoList= new ArrayList<>();
            for(Items items: customer.getItemsList()){
                ItemsDto itemsDto= new ItemsDto();
                itemsDto.setName(items.getFoodName());
                itemsDto.setQuantity(items.getQuantity());

                itemsDtoList.add(itemsDto);
            }
            customerDto.setItems(itemsDtoList);
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }

    public Boolean deleteCustomerRecords(Integer customerId){
        customerRepository.deleteById(customerId);

        return true;
    }
    public Boolean updateRecords(CustomerDto customerDto,Integer customerId){

        Optional<Customer> customer1= customerRepository.findById(customerId);
        if(customer1!= null) {
            Customer customer= customer1.get();

           customer.setCustName(customerDto.getCustName());
           customer.setMobNum(customerDto.getMobileNumber());
           customer.setCountry(customerDto.getCountry());

           List<ItemsDto> itemsDtoList= customerDto.getItems();
           for (ItemsDto dto:itemsDtoList){
               Items items= new Items();
               items.setFoodName(dto.getName());
               items.setQuantity(dto.getQuantity());

               customer.addItems(items);
           }
           customerRepository.save(customer);
        }
        return true;
    }
    public Boolean validateCustomerName(String name){
        Boolean validate= false;
        if(name!= null){
            validate= true;
        }
        return validate;
    }

    public  Boolean validateMobNum(Long mobNum){
        Boolean number= false;
        if(mobNum>0l){
            number=true;
        }
        return number;
    }

    

}