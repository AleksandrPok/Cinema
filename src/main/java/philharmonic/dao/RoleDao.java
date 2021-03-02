package philharmonic.dao;

import philharmonic.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}
