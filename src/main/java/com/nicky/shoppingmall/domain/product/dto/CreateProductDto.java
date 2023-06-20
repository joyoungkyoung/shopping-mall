package com.nicky.shoppingmall.domain.product.dto;

import java.util.List;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateProductDto {
    private Integer id;
    private Integer categoryId;
    private Integer discountId;
    private String name;
    private String description; // html
    private Integer mainImageId;
    private Integer thumbImageId;
    private Integer supplyPrice;
    private Integer consumerPrice;
    private Integer productPrice;
    private Integer tax;
    private String tag;
    private Integer margin;
    private Integer stock = 0;

    @Builder
    public CreateProductDto(Integer categoryId, Integer discountId, String name, String description, Integer mainImageId, Integer thumbImageId, Integer supplyPrice, Integer consumerPrice, Integer productPrice, Integer tax, Integer margin, String tag, Integer stock) {
        this.categoryId = categoryId;
        this.discountId = discountId;
        this.name = name;
        this.description = description;
        this.mainImageId = mainImageId;
        this.thumbImageId = thumbImageId;
        this.supplyPrice = supplyPrice;
        this.consumerPrice = consumerPrice;
        this.productPrice = productPrice;
        this.tax = tax;
        this.margin = margin;
        this.tag = tag;
        this.stock = stock;
    }

    public Integer getId() { return id; }

    @Getter
    public static class Request extends BusinessDto {
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
            if(isBlank(name)) return "name";
            if(isBlank(description)) return "description";

            return null;
        }
    }

    @Getter
    public static class Response {
        private Integer id;  

        public Response(Integer id) {
            this.id = id;
        }
    }

}
