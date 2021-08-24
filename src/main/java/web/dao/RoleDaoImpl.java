package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role").getResultList();
    }

    @Override
    public Role findRoles(String role) {
        return entityManager.createQuery("select role from Role role where role.role = :name",Role.class).setParameter("name",role).getSingleResult();
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }
}
