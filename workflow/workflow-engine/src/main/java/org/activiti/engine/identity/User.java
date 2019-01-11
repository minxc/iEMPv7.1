package org.activiti.engine.identity;

import java.io.Serializable;

import org.activiti.engine.IdentityService;


/**
 * Represents a user, used in {@link IdentityService}.
 * @author Tom Baeyens
 */
public interface User extends Serializable {

  String getId();
  void setId(String id);

  String getFirstName();
  void setFirstName(String firstName);
  
  void setLastName(String lastName);
  String getLastName();

  void setEmail(String email);
  String getEmail();
  
  String getPassword(); 
  void setPassword(String string);
  
  boolean isPictureSet();
}
