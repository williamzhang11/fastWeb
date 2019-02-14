package com.xiu.fastWeb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiu.fastWeb.annotation.OperateLogAnnotation;
import com.xiu.fastWeb.annotation.OperateLogAnnotation.Type;
import com.xiu.fastWeb.model.Address;
import com.xiu.fastWeb.service.AddressService;



@RestController
@RequestMapping(value="/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping(value="save")
	@OperateLogAnnotation(value= OperateLogAnnotation.Type.ADD)
	public String saveAddress() {
		
		Address address = new Address();
		
		address.setAddressName("shangdong");
		
		addressService.saveAddress(address);
		return "1111";
	}
	
	@GetMapping(value="exception")
	@OperateLogAnnotation(value= OperateLogAnnotation.Type.ADD)
	public String exceptionTest() {
		
		throw new IllegalArgumentException("eee");
		
	}
	
	
}
