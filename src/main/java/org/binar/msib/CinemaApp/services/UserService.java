package org.binar.msib.CinemaApp.services;

import org.binar.msib.CinemaApp.dto.RoleRequest;
import org.binar.msib.CinemaApp.dto.RoleResponse;
import org.binar.msib.CinemaApp.dto.SignupRequest;
import org.binar.msib.CinemaApp.dto.SignupResponse;


public interface UserService {
    RoleResponse registerRole(RoleRequest roleRequest);
    SignupResponse registerUser(SignupRequest signupRequest);
}
