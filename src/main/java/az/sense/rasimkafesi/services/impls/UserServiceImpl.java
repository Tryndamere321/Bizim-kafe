package az.sense.rasimkafesi.services.impls;

import az.sense.rasimkafesi.dtos.userDto.UserRegisterDto;
import az.sense.rasimkafesi.models.Role;
import az.sense.rasimkafesi.models.UserEntity;
import az.sense.rasimkafesi.repositories.RoleRepository;
import az.sense.rasimkafesi.repositories.UserRepository;
import az.sense.rasimkafesi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(BCryptPasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        UserEntity findUser=userRepository.findByEmail(userRegisterDto.getEmail());
        if(findUser!=null){
         throw new UsernameNotFoundException("bu email artiq movcuddur");
        }else {
            UserEntity user=modelMapper.map(userRegisterDto, UserEntity.class);
            user.setPassword(encoder.encode(userRegisterDto.getPassword()));
            Role role =RoleRepository.findByName("ROLE_USER");
            user.setRoles(Collections.singletonList(role));
            userRepository.save(user);
        }


    }
}
