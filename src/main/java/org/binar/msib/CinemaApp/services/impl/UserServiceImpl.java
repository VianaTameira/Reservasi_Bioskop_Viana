package org.binar.msib.CinemaApp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.msib.CinemaApp.dto.UserDTO;
import org.binar.msib.CinemaApp.entity.EntityUser;
import org.binar.msib.CinemaApp.repository.EntityUserRepository;
import org.binar.msib.CinemaApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    EntityUserRepository entityUserRepository;

    @Override
    public EntityUser insertUser(EntityUser entityUser) {
        EntityUser result = entityUserRepository.save(entityUser);
        return result;
    }

    @Override
    public EntityUser updateUser(Integer user_id, EntityUser entityUser) {
        EntityUser result = findById(user_id);
        if(result != null){
            result.setUsername(entityUser.getUsername());
            result.setPassword(entityUser.getPassword());
            result.setEmail(entityUser.getEmail());
            result.setOrders(entityUser.getOrders());
            entityUserRepository.save(entityUser);
        }
        return null;
    }

    @Override
    public boolean delete(Integer user_id) {
        EntityUser result = findById(user_id);
        if (result != null){
            entityUserRepository.deleteById(user_id);
            return true;
        }
        return false;
    }

    @Override
    public EntityUser findById(Integer user_id) {
        Optional<EntityUser> result = entityUserRepository.findById(user_id);
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public UserDTO mapToDto(EntityUser entityUser) {
        return mapper.convertValue(entityUser, UserDTO.class);
    }

    @Override
    public EntityUser mapToEntity(UserDTO userDTO) {
        return mapper.convertValue(userDTO, EntityUser.class);
    }

}
