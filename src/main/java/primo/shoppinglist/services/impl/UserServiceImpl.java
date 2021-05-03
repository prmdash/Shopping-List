package primo.shoppinglist.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import primo.shoppinglist.data.entities.UserEntity;
import primo.shoppinglist.data.services.UserServiceModel;
import primo.shoppinglist.repositories.UserRepository;
import primo.shoppinglist.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        userRepository
                .saveAndFlush(
                        modelMapper.map(
                                userServiceModel
                                , UserEntity.class
                        )
                );
    }
}
