package com.nextalien.accounts.mapper;

import com.nextalien.accounts.dto.AccountDto;
import com.nextalien.accounts.entity.Accounts;

public class AccountMapper {

    public static AccountDto mapTOAccountsDto(Accounts accounts){
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static Accounts mapToAccounts(AccountDto accountDto, Accounts accounts){
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());

        return accounts;
    }
}
