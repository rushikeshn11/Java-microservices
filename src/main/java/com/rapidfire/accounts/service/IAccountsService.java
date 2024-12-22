package com.rapidfire.accounts.service;

import com.rapidfire.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
}
