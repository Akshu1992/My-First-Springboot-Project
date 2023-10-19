package com.akshatha.akbancsapp.dto;

import jakarta.persistence.*;
import lombok.*;

//DTO -data transfer object, this is used wherever we need to interact with any other layer except database layer (only entity class is used tp interact with database).
@Data // Generates constructors, getter and setter methods
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {
    private Long customerId; //primary key in table, one of the column in the table.
    private String customerName; //another column in the table
}
