package com.example.springboot312.dao;

import com.example.springboot312.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createRole(Role role) {
        em.persist(role);
    }


    @Override
    public void updateRole(Role role) {
        em.merge(role);
    }

    @Override
    public void removeRole(int id) {
        em.remove(em.find(Role.class, id));
    }


    @Override
    public List<Role> getRoles() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return em.createQuery("select r from Role r where r.role=:role", Role.class).setParameter("role", name).getSingleResult();
    }
}
