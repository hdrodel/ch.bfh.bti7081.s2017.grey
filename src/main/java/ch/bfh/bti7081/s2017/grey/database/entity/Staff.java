package ch.bfh.bti7081.s2017.grey.database.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @Author Quentin
 */
@Entity
public class Staff {

  @Id
  @GeneratedValue
  private long id;
  private String firstname;
  private String lastname;
  private String pwhash;
  private String login;
  private String salt;
  @ManyToOne
  private Role role;
  private Timestamp created;
  private Timestamp changed;

  public Staff() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPwhash() {
    return pwhash;
  }

  public void setPwhash(String pwhash) {
    this.pwhash = pwhash;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Role getRoles() {
    return role;
  }

  public void setRoles(Role role) {
    this.role = role;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public Timestamp getChanged() {
    return changed;
  }

  public void setChanged(Timestamp changed) {
    this.changed = changed;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
