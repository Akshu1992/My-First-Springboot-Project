package com.akshatha.akbancsapp.model; //this is the enitity class means interacting directly with database.

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //links this class "Customer" to database. This class is called as entity class and direclty interacting with database.
@Table(name = "AK_CUSTOMERS") //Customize table name - default it takes class and table name as same
@Data // Generates getter, setter, toString and hashCode methods
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

   @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Auto Id generation
    private Long customerId; //primary key in table, one of the column in the table.

    @Column(name = "AK_CUSTOMER_NAME")
    private String customerName; //another column in the table
}
