package org.binar.msib.CinemaApp.services.impl;

import org.binar.msib.CinemaApp.dto.RoleRequest;
import org.binar.msib.CinemaApp.dto.RoleResponse;
import org.binar.msib.CinemaApp.dto.SignupRequest;
import org.binar.msib.CinemaApp.dto.SignupResponse;
import org.binar.msib.CinemaApp.entity.Role;
import org.binar.msib.CinemaApp.entity.User;
import org.binar.msib.CinemaApp.repository.RoleRepository;
import org.binar.msib.CinemaApp.repository.UserRepository;
import org.binar.msib.CinemaApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public RoleResponse registerRole(RoleRequest roleRequest) {
        Role role = roleRequest.toRoles();

        try {
            roleRepository.save(role);
            return RoleResponse.build(role);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    @Override
    public SignupResponse registerUser(SignupRequest signupRequest) {
        User user = new User();
        String email = user.getEmail();
        if(email == null)
        {
            try {
                Role roles = roleRepository.findByName(String.valueOf(signupRequest.getRoleName()));
                if(roles != null)
                {
                    user = signupRequest.toUsers();
                    user.setPassword(encoder.encode(user.getPassword()));
                    userRepository.saveAndFlush(user);
                    return SignupResponse.build(user);
                }
                else
                    return null;
            }
            catch (Exception ignore){
                return null;
            }
        }
        else
            return null;
    }
}

