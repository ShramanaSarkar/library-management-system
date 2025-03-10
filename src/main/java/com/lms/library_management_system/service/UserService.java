package com.lms.library_management_system.service;

import com.lms.library_management_system.dao.AddressDao;
import com.lms.library_management_system.dao.UserDao;
import com.lms.library_management_system.dto.UserDto;
import com.lms.library_management_system.entity.Address;
import com.lms.library_management_system.entity.User;
import com.lms.library_management_system.util.ResponseStructure;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<ResponseStructure<User>> saveUser(UserDto userDto, int addressId) {
        Address dbAddress= addressDao.findByAddressId(addressId);
        User user = modelMapper.map(userDto, User.class);

        user.setAddress(dbAddress);
        User savedUser = userDao.saveUser(user);

        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User Saved");
        response.setData(savedUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<User>> findUserById(int userId) {
        User dbUser = userDao.findByUserId(userId);
        ResponseStructure<User> response = new ResponseStructure<>();

        if (dbUser != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("User Found");
            response.setData(dbUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("User Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseStructure<User>> updateUser(int userId, UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User updatedUser = userDao.updateUser(userId, user);

        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User Updated");
        response.setData(updatedUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<User>> deleteUser(int userId) {
        User deletedUser = userDao.deleteUser(userId);
        ResponseStructure<User> response = new ResponseStructure<>();

        if (deletedUser != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("User Deleted");
            response.setData(deletedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("User Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseStructure<List<User>>> fetchAllUsers() {
        List<User> users = userDao.fetchAllUser();
        ResponseStructure<List<User>> response = new ResponseStructure<>();

        if (!users.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Users Retrieved");
            response.setData(users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("No Users Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
