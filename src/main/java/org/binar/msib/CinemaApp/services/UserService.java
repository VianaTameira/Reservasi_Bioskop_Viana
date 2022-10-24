package org.binar.msib.CinemaApp.services;

import org.binar.msib.CinemaApp.dto.UserDTO;
import org.binar.msib.CinemaApp.entity.EntityUser;

public interface UserService {
    public EntityUser insertUser(EntityUser entityUser);
    public EntityUser updateUser(Integer user_id, EntityUser entityUser);
    public boolean delete (Integer user_id);
    EntityUser findById(Integer user_id);
    UserDTO mapToDto(EntityUser entityUser);
    EntityUser mapToEntity(UserDTO userDTO);

}
