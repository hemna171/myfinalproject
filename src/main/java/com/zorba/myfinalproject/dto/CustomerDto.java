package com.zorba.myfinalproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class CustomerDto {

    private String custName;
    private Long mobileNumber;
    private String country;
    private List<ItemsDto> items;


}
