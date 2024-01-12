package com.scaler.lldprojectmodule.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.management.DescriptorKey;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "1")
public class Mentor extends User {
    private double averageRating;
}
