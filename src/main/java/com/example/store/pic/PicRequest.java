package com.example.store.pic;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class PicRequest {

    @Data
    public static class UploadDTO {
//        private String title;            //키값 - 타이틀 ex.딸기 //안받음
        private MultipartFile imgFile;    //키값 photo ex.이미지 파일 정보가 담김

    }

}
