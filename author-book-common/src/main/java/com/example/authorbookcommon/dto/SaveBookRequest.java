package com.example.authorbookcommon.dto;

import com.example.authorbookcommon.entity.Author;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveBookRequest {

    private int id;
    private String title;
    @Min(value = 1, message = "Price should be >= 1")
    private double price;
    @Min(value = 1, message = "Qty should be >= 1")
    private int qty;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private Author author;
}