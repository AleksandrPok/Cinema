package philharmonic.service;

import philharmonic.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
