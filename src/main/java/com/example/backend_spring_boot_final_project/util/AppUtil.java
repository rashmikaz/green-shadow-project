package com.example.backend_spring_boot_final_project.util;

import java.util.Base64;

public class AppUtil {
    public static String cropImageToBase64(byte [] cropImage){
        return Base64.getEncoder().encodeToString(cropImage);
    }

}
