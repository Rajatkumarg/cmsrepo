package com.cms.validators;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB

    // type

    // height

    // width

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if (file == null || file.isEmpty()) {

//             context.disableDefaultConstraintViolation();
//             context.buildConstraintViolationWithTemplate("File cannot be empty").addConstraintViolation();
            return true;

        }

        // file size

        System.out.println("file size: " + file.getSize());

        if (file.getSize() > MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 2MB").addConstraintViolation();
            return false;
        }

        // resolution

//         try {
//         BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
//
//         if(bufferedImage.getHeight()>500) {
//
//         } 
//         }catch (IOException e) {
//        	 e.printStackTrace();
//         }
        return true;
    }

}
