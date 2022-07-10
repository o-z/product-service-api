package com.example.productserviceapi.service;

import com.example.productserviceapi.model.dto.request.SaveUserRequest;
import com.example.productserviceapi.model.entity.UserNodeEntity;
import com.example.productserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserNodeEntity getUserById(UUID id) {
        Optional<UserNodeEntity> optionalUserNodeEntity = userRepository.findById(id);
        if (optionalUserNodeEntity.isEmpty()) {
            return null;
        }
        return optionalUserNodeEntity.get();
    }

    @Transactional(readOnly = true)
    public Map<UUID, UserNodeEntity> getAllUsers(Integer page, Integer size) {
        Page<UserNodeEntity> userNodeEntities = userRepository.getUserNodeEntitiesBy(Pageable.ofSize(size).withPage(page));
        return userNodeEntities.stream().collect(Collectors.toMap(UserNodeEntity::getId, userNodeEntity -> userNodeEntity));
    }


    @Transactional
    public UserNodeEntity saveUser(final SaveUserRequest request) {

        UserNodeEntity userNodeEntity = UserNodeEntity.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .build();
        return userRepository.save(userNodeEntity);
    }
}