package ACL.Service.services.ImpService;

import ACL.Service.models.Role;
import ACL.Service.models.User;
import ACL.Service.repositories.RoleRepository;
import ACL.Service.repositories.UserRepository;
import ACL.Service.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUSer(User user) {
        return userRepository.save(user);
    }

    public void initRoleAndUser(){
        Role allRoles = (Role) roleRepository.findAll();
        User adminUser = new User();
        adminUser.setUserName("Nobin922");
        adminUser.setUserFirstName("Arif");
        adminUser.setUserLastName("Shakil");
        adminUser.setUserPassword("123456");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(allRoles);
        userRepository.save(adminUser);




    }
}
