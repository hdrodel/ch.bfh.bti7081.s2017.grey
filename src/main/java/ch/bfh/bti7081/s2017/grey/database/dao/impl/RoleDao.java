package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import javax.persistence.EntityManager;

/**
 * Created by gabor on 29/05/17.
 */
public class RoleDao extends GenericDaoImpl<Role> implements GenericDao<Role> {

  public RoleDao(EntityManager em) {
    super(em);
  }
}
