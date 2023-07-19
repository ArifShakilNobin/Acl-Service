package ACL.Service.services.ImpService;

import ACL.Service.models.Role;
import ACL.Service.repositories.RoleRepository;
import ACL.Service.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);

    }
}
