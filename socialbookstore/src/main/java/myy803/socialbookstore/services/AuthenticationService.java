package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.User;

public interface AuthenticationService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
	public User findById(String username);
}
