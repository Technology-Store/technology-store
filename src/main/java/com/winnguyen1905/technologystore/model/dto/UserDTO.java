package com.winnguyen1905.technologystore.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO extends BaseObjectDTO<UserDTO> {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean status;
    private String phone;
    private RoleDTO role;
    private String type;
}