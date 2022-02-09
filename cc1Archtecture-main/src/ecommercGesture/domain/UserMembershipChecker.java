package ecommercGesture.domain;

import ecommercGesture.objects.User;

public class UserMembershipChecker {
	
	// Simulacre pour le test
    public static boolean userHasValidInformationsToApplyMembership(User u) {
        return u.getName().length() > 2 && u.getLastName().length() > 2 && u.getPassword().length() > 2 && u.getUserName().length() > 2;
    }
}
