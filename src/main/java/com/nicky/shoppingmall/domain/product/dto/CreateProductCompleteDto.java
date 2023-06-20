package com.nicky.shoppingmall.domain.product.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateProductCompleteDto {
    
    @Getter
    @NoArgsConstructor
    public static class SubImage {
        private Integer imageId;
        private boolean isMain; 
    }
    
    @Getter
    public static class Request {
        @NotNull
        private Integer productId;
        @NotNull
        private Integer mainImageId;
        @NotNull
        private Integer thumbImageId;

        // image's
        // sub image array [image_file, is_main]
        @NotNull
        private List<SubImage> subImageList; 
    }
}
