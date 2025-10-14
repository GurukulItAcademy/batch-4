package com.nextalien.accounts.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Data
public class AccountContactInfoDto {
    private String version;
    private String msg;
    private Map<String,String> contactDetails;
    private List<String> callSupport;

}
