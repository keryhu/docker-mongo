package com.wfij.kery.account.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wfij.kery.account.domain.Role;

@Document(collection="docker-user")
public class User implements Serializable{

	private static final long serialVersionUID = -8881254891826611809L;
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String  email;   // 注册邮箱
	
	@Indexed(unique = true)
	private String  phone;    //注册手机
	
	@JsonIgnore
	@RestResource(exported = false)
	private String  password;    
	
	private List<Role> roles=new ArrayList<Role>();   //权限
	
	public User(){
	this.id=UUID.randomUUID().toString();
	}
	
	
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public boolean hasRole(Role role) {
        return (this.roles.contains(role));
    }
	 public void removeRole(Role role) {
			//use iterator to avoid java.util.ConcurrentModificationException with foreach
		 Iterator<Role> iter = this.roles.iterator();
		 while(iter.hasNext()){
			 Role r=iter.next();
			 if(r.equals(role)){
				 iter.remove();
			 }
		 }
			
		}
	 
	 public List<Role> getRoles() {
		 return Collections.unmodifiableList(this.roles);
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	


}
