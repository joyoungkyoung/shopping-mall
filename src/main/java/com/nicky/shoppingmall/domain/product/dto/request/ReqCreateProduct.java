package com.nicky.shoppingmall.domain.product.dto.request;

import java.util.List;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ReqCreateProduct extends BusinessDto {


    @Getter
    @NoArgsConstructor
    public static class Metadata {
        private String fieldName;
        private String fieldValue;
    }

    @Getter
    @NoArgsConstructor
    public static class Option {
        private String optName;
        private String optValue;
    }

    // product's
    // category_id, discount_id, name, description, main_image_file, thumb_image_file, supply_price, consumer_price, product_price, tax, margin, stock
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer discountId;
    @NotBlank
    private String name;
    @NotBlank
    private String description; // html
    @NotNull
    private Integer supplyPrice;
    @NotNull
    private Integer consumerPrice;
    @NotNull
    private Integer productPrice;
    @NotNull
    private Integer tax;
    @NotNull
    private Integer margin;
    @NotNull
    private Integer stock;

    // metadata's
    // metadata array [field_name, field_value]
    @NotNull
    private List<Metadata> metadataList;
    
    // option's
    // opt array [opt_name, opt_value]
    @NotNull
    private List<Option> optionList;
    
    @Override
    public String invalidBlank() {
        if(name.startsWith(" ") || name.endsWith(" ")) {
            return "name";
        }
        if(description.startsWith(" ") || description.endsWith(" ")) {
            return "description";
        }

        return null;
    }
    

}

