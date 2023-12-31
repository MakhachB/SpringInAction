package com.cassandra.model.udt;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("user")
@Data
public class UserUDT {
    private final String username;
    private final String fullname;
    private final String phoneNumber;
}
