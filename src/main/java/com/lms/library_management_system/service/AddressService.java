package com.lms.library_management_system.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.library_management_system.dao.AddressDao;
import com.lms.library_management_system.dto.AddressDto;
import com.lms.library_management_system.entity.Address;
import com.lms.library_management_system.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto){
		Address address=modelMapper.map(addressDto, Address.class);
		Address dbAddress=addressDao.saveAddress(address);
		ResponseStructure<Address> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Address Saved");
		responseStructure.setData(dbAddress);
		
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Address>> findByAddressId(int addressId){
		Address dbAddress=addressDao.findByAddressId(addressId);
		ResponseStructure<Address> responseStructure=new ResponseStructure<>();
		if(dbAddress!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Address Found");
			responseStructure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Address not Found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(int addressId, AddressDto addressDto){
		Address address=modelMapper.map(addressDto, Address.class);
		Address dbAddress=addressDao.updateAddress(addressId, address);
		ResponseStructure<Address> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Address Updated");
		responseStructure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(int addressId){
		Address dbAddress=addressDao.deleteAddress(addressId);
		ResponseStructure<Address> responseStructure=new ResponseStructure<>();
		if(dbAddress!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Address Deleted");
			responseStructure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Address not Found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<Address>>> fetchAllAddress(){
		List<Address> addressList=addressDao.fetchAllAddress();
		ResponseStructure<List<Address>> responseStructure=new ResponseStructure<>();
		if(addressList!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Address Found");
			responseStructure.setData(addressList);
			return new ResponseEntity<ResponseStructure<List<Address>>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Address not Found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<List<Address>>>(responseStructure,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

}
