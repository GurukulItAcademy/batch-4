package com.nextalien.accounts.controller;

import com.nextalien.accounts.dto.AccountContactInfoDto;
import com.nextalien.accounts.dto.CustomerDto;
import com.nextalien.accounts.dto.ErrorResponseDto;
import com.nextalien.accounts.dto.ResponseDto;
import com.nextalien.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.nextalien.accounts.constants.AccountConstants.*;

@Tag(
        name = "CRUD REST APIs for Account in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api")
@Validated
public class AccountController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    Environment environment;

    @Autowired
    private AccountContactInfoDto accountDetails;

    private final IAccountService iAccountService;


    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }


    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, STATUS_MESSAGE));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile no must be 10 digit") String mobileNumber) {

     CustomerDto customerDto =    iAccountService.fetchAccount(mobileNumber);

     return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, "Account updated successfully"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, "Failed to update account"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, "Account deleted successfully"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, "Failed to delete account: looks like mobile number is null | empty"));
        }
    }

    @GetMapping("/build-version")
    public ResponseEntity<String> fetchBuildVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @GetMapping("/java-home")
    public ResponseEntity<String> fetchJavaVersion() {
        String javaHome = environment.getProperty("My_Env_Var");
        return ResponseEntity.status(HttpStatus.OK).body(javaHome);
    }

    @GetMapping("/account-details")
    public ResponseEntity<AccountContactInfoDto> fetchAccountDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(accountDetails);
    }

}

// localhost:8080/api/create