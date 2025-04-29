package com.alirizakaygusuz.validator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.repository.CustomerRepository;
import com.alirizakaygusuz.repository.StoreRepository;


@Component
public class AccountAssignmentValidator {

	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private CustomerRepository customerRepository;

	//Save Store
	public void ensureAssignableForStore(Long accountId) {
		boolean accountInStore = storeRepository.existsByAccountId(accountId);
		boolean accountInCustomer = customerRepository.existsByAccountsId(accountId);
		if (accountInStore || accountInCustomer) {
			throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_ALREADY_ASSIGNED,
					"Account id " + accountId ));
		}
	}

	//Update Store
	public void ensureAssignableForStore(Long accountId, Long currentStoreId) {
		boolean accountInOtherStore = storeRepository.existsByAccountIdAndIdNot(accountId, currentStoreId);
		boolean accountInCustomer = customerRepository.existsByAccountsId(accountId);
		if (accountInOtherStore || accountInCustomer) {
			throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_ALREADY_ASSIGNED,
					"Account id " + accountId ));
		}
	}

	//Save Customer
	public void ensureAssignableForCustomer(Collection<Long> accountIds) {
		for (Long accountId : accountIds) {
			boolean accountInStore = storeRepository.existsByAccountId(accountId);
			boolean accountInCustomer = customerRepository.existsByAccountsId(accountId);
			if (accountInStore || accountInCustomer) {
				throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_ALREADY_ASSIGNED,
						"Account id " + accountId ));
			}
		}
	}

	//Update Customer
	public void ensureAssignableForCustomer(Collection<Long> accountIds, Long currentCustomerId) {
		for (Long accountId : accountIds) {
			boolean accountInStore = storeRepository.existsByAccountId(accountId);
			boolean accountInOtherCustomer = customerRepository.existsByAccountsIdAndIdNot(accountId, currentCustomerId);
			if (accountInStore || accountInOtherCustomer) {
				throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_ALREADY_ASSIGNED,
						"Account id " + accountId));
			}
		}
	}
}
