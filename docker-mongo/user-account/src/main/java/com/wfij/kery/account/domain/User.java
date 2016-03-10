package com.wfij.kery.account.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.rest.core.annotation.RestResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wfij.kery.account.domain.Role;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="password")
public class User implements Serializable{

	private static final long serialVersionUID = -8881254891826611809L;
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String  email;   // 注册邮箱
	
	@Indexed(unique = true)
	private  String  phone;    //注册手机
	
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
	
	public boolean hasRole(Role role) {
        return (this.roles.contains(role));
    }
	
	 public void removeRole(Role role) {
		 
		 this.roles=this.roles.stream()
				 //将符合条件的相同role去掉
		           .filter(e->!e.equals(role))
		           .collect(Collectors.toList());		
		}
      
}
