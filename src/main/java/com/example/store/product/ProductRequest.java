package com.example.store.product;

import lombok.Data;

public class ProductRequest {

    @Data
    public static class UpdateDTO {
        private String name;
        private String price;
        private String qty;
    }

    @Data
    public static class SaveDTO {
        private String name;
        private String price;
        private String qty;

    }


}
