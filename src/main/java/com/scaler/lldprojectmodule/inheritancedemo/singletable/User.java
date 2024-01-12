package com.scaler.lldprojectmodule.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {

    private Long id;
    private String name;
    private String email;
}
