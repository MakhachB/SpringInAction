package com.tacocloud8rest.model.dto;

import com.tacocloud8rest.security.Role;
import com.tacocloud8rest.security.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    private String role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullname = user.getFullname();
        this.street = user.getStreet();
        this.city = user.getCity();
        this.state = user.getState();
        this.zip = user.getZip();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole().name();
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setFullname(this.fullname);
        user.setStreet(this.street);
        user.setCity(this.city);
        user.setState(this.state);
        user.setZip(this.zip);
        user.setPhoneNumber(this.phoneNumber);
        user.setRole(Role.valueOf(this.role));
        return user;
    }
}
