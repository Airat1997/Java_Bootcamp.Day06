package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.models.User.EntityNotFoundException;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {

    UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    boolean authenticate(String login, String password) {
        User user;
        try {
            user = usersRepository.findByLogin(login);
            if (user.isAuthenticationStatus()) {
                throw new AlreadyAuthenticatedException("Authenticated");
            }
            if (user.getPassword().equals(password)) {
                user.setAuthenticationStatus(true);
                usersRepository.update(user);
            } else {
                return false;
            }
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return true;
    }


}
