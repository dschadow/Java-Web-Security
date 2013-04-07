package de.dominikschadow.webappsecurity;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.owasp.esapi.User;
import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.reference.IntegerAccessReferenceMap;
import org.owasp.esapi.reference.RandomAccessReferenceMap;

public class AccessReferenceMapSample {
	private User user = null;
	Account a = new Account();
	
	public void addAccountsToSession(HttpSession session) throws AccessControlException {
		Set<Object> accountSet = loadUserAccounts(user);
//		IntegerAccessReferenceMap accounts = new IntegerAccessReferenceMap(accountSet);
		RandomAccessReferenceMap accounts = new RandomAccessReferenceMap(accountSet);
		
		
//		session.setAttribute("accounts", accounts);
		
//		Account account = accounts.getDirectReference("1");
		String reference = accounts.getIndirectReference(a);
		
		System.out.println("ref " + reference);
		
		user.getAccountId();
		
		
		// ...
	}
	
	public static void main(String[] args) {
		AccessReferenceMapSample s = new AccessReferenceMapSample();
		try {
			s.addAccountsToSession(null);
		} catch (AccessControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Set<Object> loadUserAccounts(User user) {
		Set<Object> accounts = new HashSet<>();
		accounts.add(a);
		// DB-Abfrage zum Laden der Objekte mit Beschränkung auf user
		
		return accounts;
	}
}
