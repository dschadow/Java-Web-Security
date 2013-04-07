package de.dominikschadow.webappsecurity;

import org.owasp.esapi.User;
import org.owasp.esapi.errors.AccessControlException;

public class Customer {

	public void showCustomerOverview(User user) throws AccessControlException {
		if (user.isInRole("AccountManager")) {
			// ...
		} else {
			throw new AccessControlException(
					"You do not have the rights to access this page",
					"User " + user.getAccountId() + " tried to access showCustomerOverview()");
		}
	}
}
