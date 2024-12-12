package com.lms.library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.library_management_system.dto.AddressDto;
import com.lms.library_management_system.entity.Address;
import com.lms.library_management_system.service.AddressService;
import com.lms.library_management_system.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody AddressDto addressDto){
		return addressService.saveAddress(addressDto);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> findByAddressId(@PathVariable int adddressId){
		return addressService.findByAddressId(adddressId);
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@PathVariable int addressId,@RequestBody AddressDto addressDto){
		return addressService.updateAddress(addressId, addressDto);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(@PathVariable int addressId) {
		try {
			return addressService.deleteAddress(addressId);
		} catch (Exception e) {
			ResponseStructure<Address> errorResponse = new ResponseStructure<>();
			errorResponse.setMessage(e.getMessage());
			errorResponse.setStatusCode(404);
			errorResponse.setData(null);
			return ResponseEntity.status(404).body(errorResponse);
		}
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Address>>> fetchAllAddress(){
		return addressService.fetchAllAddress();
	}
	
}
