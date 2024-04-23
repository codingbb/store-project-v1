package com.example.store.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {

//    @Data
//    public static class UploadDTO {
//        //        private String title;            //키값 - 타이틀 ex.딸기 //안받음
//        private MultipartFile imgFile;    //키값 photo ex.이미지 파일 정보가 담김
//
//    }

    @Data
    public static class UpdateDTO {
        private String name;
        private Integer price;
        private Integer qty;
    }

    @Data
    public static class SaveDTO {
        private String name;
        private Integer price;
        private Integer qty;
        private MultipartFile imgFile;

    }


}
