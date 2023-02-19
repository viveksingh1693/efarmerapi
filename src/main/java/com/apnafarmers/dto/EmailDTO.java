package com.apnafarmers.dto;

import java.util.List;

import org.springframework.context.annotation.Description;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Description(value = "EmailDTO DTO class.")
public class EmailDTO {

    private List<String> recipients;
    private List<String> ccList;
    private List<String> bccList;
    private String subject;
    private String body;
    private Boolean isHtml;
    private String attachmentPath;
   
}
