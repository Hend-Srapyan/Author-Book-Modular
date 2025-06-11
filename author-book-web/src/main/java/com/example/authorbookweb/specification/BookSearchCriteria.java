package com.example.authorbookweb.specification;

import com.example.authorbookcommon.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchCriteria {

    private String title;
    private Double price;
    private Integer qty;
    private Author author;

}
