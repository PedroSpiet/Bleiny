package com.bleiny.communities.application.services;

import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.UserRepositoryPort;
import com.bleiny.communities.application.ports.UserServicePort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort, ModelMapper modelMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createUser(Users dto) throws ApiException {
        try {
            if (dto == null) {
                throw ApiException.badRequest("Error on save user", "Usuário nulo");
            }
            userRepositoryPort.save(dto);
        } catch (ApiException e) {
            e.printStackTrace();
            throw ApiException.badRequest("Error on save user", e.getMessage());
        }
    }

    @Override
    public Users findById(Long id) throws ApiException {
        try {
            return userRepositoryPort.findById(id).orElseThrow(() ->
                    ApiException.notFound("User not found", "Usuário não existe na base"));
        } catch (Exception e) {
           throw ApiException.notFound("User not found", "Usuário não existe na base");
        }
    }
}
