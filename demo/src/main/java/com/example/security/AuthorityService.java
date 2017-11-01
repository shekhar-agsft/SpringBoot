package com.example.security;

import java.util.List;



/**
 * Created by Aquib
 */
public interface AuthorityService {

	public List<Role> getAuthorityList();
	
	public Role getAuthorityByName(String roleName);

}
