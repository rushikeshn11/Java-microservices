package com.rapidfire.accounts.service.impl;

import com.rapidfire.accounts.constants.AccountsConstants;
import com.rapidfire.accounts.dto.CustomerDto;
import com.rapidfire.accounts.entity.Accounts;
import com.rapidfire.accounts.entity.Customer;
import com.rapidfire.accounts.exception.CustomerAlreadyExistsException;
import com.rapidfire.accounts.mapper.CustomerMapper;
import com.rapidfire.accounts.repository.AccountsRepository;
import com.rapidfire.accounts.repository.CustomerRepository;
import com.rapidfire.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IAccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
