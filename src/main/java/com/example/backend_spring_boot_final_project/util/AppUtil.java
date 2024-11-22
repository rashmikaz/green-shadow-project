package com.example.backend_spring_boot_final_project.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String cropImageToBase64(byte [] cropImage){
        return Base64.getEncoder().encodeToString(cropImage);
    }

    public static String generateCropId(){
        return "CROP-" + UUID.randomUUID();
    }

    public static String generateStaffId(){
        return "STAFF-" + UUID.randomUUID();
    }

    public static String generateFieldId(){
        return "FIELD-" + UUID.randomUUID();
    }


}
