package com.nextalien.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
@Data
public class AccountDto {
    @Schema(
            description = "Account Number of Eazy Bank account", example = "3454433243"
    )
    private Long accountNumber;

    @Schema(
            description = "Eazy Bank branch address", example = "123 NewYork"
    )
    private String accountType;

    @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
    private String branchAddress;
}
