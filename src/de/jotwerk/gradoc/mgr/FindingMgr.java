package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Finding;
import de.jotwerk.gradoc.model.Item;

@ManagedBean(eager = true)
@SessionScoped
public class FindingMgr extends GeometryMgr implements Serializable {	
	
	private static final long serialVersionUID = 1L;
		
	@Override
	protected Class<Finding> getItemClass() { return Finding.class; }
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Finding)item).setCount(1);
	}
	
	//UPDATE `FINDING` SET `NAME`=concat('DeII15 ', MOD(FLOOR(`X`),10000), '/', MOD(FLOOR(`Y`),10000), '-', `NAME`) WHERE `TASK` = 1
	
	@Override
	protected String getItemQuery() {
		return super.getItemQuery() + " LEFT JOIN i.context c";
	}
	
	protected String getOrderClause() {
		return " ORDER BY i.campaign ASC, i.location ASC, i.number ASC";
	}
	
	@Override
	protected void prePersist(Item item) {
		super.prePersist(item);
		((Finding)item).setLocation(login.getLocation().getSignature());
	}
			
}
