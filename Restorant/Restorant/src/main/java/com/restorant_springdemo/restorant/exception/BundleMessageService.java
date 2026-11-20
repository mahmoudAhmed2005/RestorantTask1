
package com.restorant_springdemo.restorant.exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class BundleMessageService {

    private  MessageSource messageSource;
@Autowired
    public BundleMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageResponse getMessage(String key) {

        String ar = messageSource.getMessage(
                key,
                null,
                Locale.forLanguageTag("ar")
        );

        String en = messageSource.getMessage(
                key,
                null,
                Locale.ENGLISH
        );

        return new MessageResponse(ar, en);
    }
}
