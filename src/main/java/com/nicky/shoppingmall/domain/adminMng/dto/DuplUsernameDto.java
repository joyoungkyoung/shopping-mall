package com.nicky.shoppingmall.domain.adminMng.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class DuplUsernameDto {
    @Getter
    public static class Request extends BusinessDto{
        @NotBlank
        private String username;

        @Override
        public String invalidBlank() {
            if(isBlank(username)) return "username";

            return null;
        }
    }

    @Getter
    public static class Response {
        private Boolean isExist;

        public Response(Boolean isExist) {
            this.isExist = isExist;
        }
    }
}
