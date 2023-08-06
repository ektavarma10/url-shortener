package com.project.URLShortener.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class URLRequest {
    private String url;

    public static void validateRequest(URLRequest request) {
        if(!StringUtils.hasText(request.getUrl())) {
            throw new ValidationException("url cannot be blank");
        }
    }

    public static void validateRequest(String shortId) {
        if(!StringUtils.hasText(shortId)) {
            throw new ValidationException("shortId cannot be blank");
        }
    }
}
