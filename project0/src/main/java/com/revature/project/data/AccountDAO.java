package com.revature.project.data;

import com.revature.project.ds.List;
import com.revature.project.models.Account;
import com.revature.project.models.Beneficiary;

public interface AccountDAO extends DataAccessObject<Account> {
	public List<Account> findByBeneficiary(Beneficiary beneficiary);
}
