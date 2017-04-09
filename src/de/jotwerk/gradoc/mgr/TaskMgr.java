package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.shiro.SecurityUtils;

import de.jotwerk.gradoc.model.Item;
import de.jotwerk.gradoc.model.Task;

@ManagedBean(eager = true)
@SessionScoped
public class TaskMgr extends ItemMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<Task> getItemClass() { return Task.class; }
		
	@Override
	protected boolean checkPermission(String method) {
		return SecurityUtils.getSubject().hasRole("admin");
	}
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Task)item).setNumber("000000");
	}
	
}
