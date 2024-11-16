package com.example.backend_spring_boot_final_project.util;

import java.util.regex.Pattern;

public class Regex {
    public static boolean cropCodeMatcher(String cropCode) {
        String regexForCropCode = "^CROP-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(cropCode).matches();
    }
}
