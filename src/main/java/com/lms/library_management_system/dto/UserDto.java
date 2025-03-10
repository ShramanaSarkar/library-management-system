package com.lms.library_management_system.dto;

import com.lms.library_management_system.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private int userId;

	private String name;

	private String phoneNumber;

	private String email;

	private Address address;
}
