package edu.school21.services;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.models.User.EntityNotFoundException;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersServiceImplTest {

    private UsersRepository mockRepository;
    private UsersServiceImpl service;
    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(UsersRepository.class);
        service = new UsersServiceImpl(mockRepository);
    }

    @Test
    void correctLogin(){
        User user = new User(1, "user", "1", false);
        when(mockRepository.findByLogin("user")).thenReturn(user);
        service.authenticate("user", "1");
        Mockito.verify(mockRepository, Mockito.times(1)).update(user);
    }


    @Test
    void wrongLogin() {
        when(mockRepository.findByLogin("user")).thenThrow(new EntityNotFoundException("User not found"));
        assertThrows(EntityNotFoundException.class, () -> service.authenticate("user", "1"));
    }

    @Test
    void wrongPassword(){
        User user = new User(1, "user", "2", false);
        when(mockRepository.findByLogin("user")).thenReturn(user);
        service.authenticate("user", "1");
        Mockito.verify(mockRepository, Mockito.times(0)).update(user);
    }
}
