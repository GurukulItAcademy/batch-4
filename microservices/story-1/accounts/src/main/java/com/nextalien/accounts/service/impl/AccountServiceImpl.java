package com.nextalien.accounts.service.impl;

import com.nextalien.accounts.dto.AccountDto;
import com.nextalien.accounts.dto.CustomerDto;
import com.nextalien.accounts.entity.Accounts;
import com.nextalien.accounts.entity.Customer;
import com.nextalien.accounts.exception.CustomerAlreadyExistsException;
import com.nextalien.accounts.exception.ResourceNotFoundException;
import com.nextalien.accounts.mapper.AccountMapper;
import com.nextalien.accounts.mapper.CustomerMapper;
import com.nextalien.accounts.repository.AccountRepository;
import com.nextalien.accounts.repository.CustomerRepository;
import com.nextalien.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.nextalien.accounts.constants.AccountConstants.*;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer =  CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer is Already registered with this mobile number: "+customer.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy(SERVICE_NAME);
        Customer savedDetails  = customerRepository.save(customer);
        Accounts newAccounts =  createNewAccount(savedDetails);

        accountRepository.save(newAccounts);
    }

    private Accounts createNewAccount(Customer customer){
        Long randomAccountNumber = 1000000000L* new Random().nextInt(900000000);

        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(ACCOUNT_TYPE);
        account.setBranchAddress(BRANCH_ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy(SERVICE_NAME);
        return account;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber
        ));

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()
                ));

        CustomerDto customerDto = CustomerMapper.mapTOCustomerDto(customer);
        customerDto.setAccountDto(AccountMapper.mapTOAccountsDto(accounts));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto != null){
            Accounts accountsDb = accountRepository.findById(customerDto.getAccountDto().getAccountNumber())
                    .orElseThrow(()-> new ResourceNotFoundException("Accounts", "customerId", accountDto.getAccountNumber().toString()
                    ));

            AccountMapper.mapToAccounts(accountDto, accountsDb);
            accountsDb =   accountRepository.save(accountsDb);


            Long customerId = accountsDb.getCustomerId();
            Customer customerDb = customerRepository.findById(customerId)
                    .orElseThrow(()-> new ResourceNotFoundException("Customer", "customerId", customerId.toString()
                    ));
            CustomerMapper.mapToCustomer(customerDto, customerDb);
            customerRepository.save(customerDb);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber
                ));

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }


}
