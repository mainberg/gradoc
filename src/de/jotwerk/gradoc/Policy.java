package de.jotwerk.gradoc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class Policy implements RolePermissionResolver {
		
	@Override
	public Collection<Permission> resolvePermissionsInRole(String role) {
		
		String roles[] = role.split(":");
		String fundort = roles[0];
		String funktion = roles[1];
		
		List<Permission> result = new ArrayList<Permission>();
		if (funktion.equals("manager")) {
			result.add(new WildcardPermission(fundort + ":Descriptor:load"));
			result.add(new WildcardPermission(fundort + ":Descriptor:save"));
			result.add(new WildcardPermission(fundort + ":Location:*"));
			result.add(new WildcardPermission(fundort + ":Campaign:*"));
			result.add(new WildcardPermission(fundort + ":Protocol:*"));
		}
		if (funktion.equals("diver") || funktion.equals("manager")) {
			result.add(new WildcardPermission(fundort + ":Finding:*"));
			result.add(new WildcardPermission(fundort + ":Artifact:*"));
			result.add(new WildcardPermission(fundort + ":Pile:*"));
			result.add(new WildcardPermission(fundort + ":Plank:*"));
			result.add(new WildcardPermission(fundort + ":Photo:*"));
			result.add(new WildcardPermission(fundort + ":Sediment:*"));
			result.add(new WildcardPermission(fundort + ":Protocol:load"));
			result.add(new WildcardPermission(fundort + ":Protocol:save"));
			result.add(new WildcardPermission(fundort + ":Protocol:delete"));			
		}
		
		return result;
		
	}
	
	

}
