package com.crossover.imageupload.validator;

import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.crossover.imageupload.bean.UploadedImage;

public class UploadedImageValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return UploadedImage.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        UploadedImage request = (UploadedImage) o;

        if (request.getDescription() == null) {
            errors.rejectValue("description", "uploadedImage");
        }

        if (request.getFileType() == null) {
            errors.rejectValue("fileType", "uploadedImage");
        }

        if (request.getFileSize() == null) {
            errors.rejectValue("fileSize", "uploadedImage");
        }
    }
}
