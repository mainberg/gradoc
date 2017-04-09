package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.shiro.SecurityUtils;

import de.jotwerk.gradoc.model.Account;
import de.jotwerk.gradoc.model.Role;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2015-04-26
 * @modified 2015-05-23
 * 
 * Ein AccountMgr muss SessionScoped sein, da er das gerade berarbeitete curItem und die gerade 
 * bearbeitete Rolle curRole über requests hinweg verfügbar hält. Denkbar wäre evtl. auch ViewScoped
 * Er delegiert seine Funktionalität an den ItemMgr, von dem er erbt. Da er task-übergreifend ist,
 * erweitert er nicht HoldingMgr, sondern ItemMgr.
 * Das er das hinzufügen von Rollen zum Account verwalten muss, überschreibt er eine Reihe der 
 * nicht abstrakten Routinen des ItemMgr.
 * Nachdem Rollen recht einfach aufgebaut sind, wird auf eine Änderungsmöglichkeit verzichtet. 
 *   
 */

@ManagedBean(eager = true)
@SessionScoped
public class AccountMgr extends ItemMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Role curRole;
	
	@Override
	protected void init() {
		super.init();
		curRole = new Role();
	}
	
	@Override
	protected Class<Account> getItemClass() { return Account.class; }
	
	@Override
	protected String getItemQuery() {
		return "select i from Account i";
	}
	
	@Override
	protected String getWhereClause() {
		return " i.name <> 'uwarc'";
	}	
							
	@Override
	protected boolean checkPermission(String method) {
		return SecurityUtils.getSubject().hasRole("admin");
	}

	public Role getCurRole() { return curRole; }
	public void setCurRole(final Role curRole) { this.curRole = curRole; }
	
	/* Die übliche JSF-Validierung muss nachgeholt werden, weil wir sie für Rollen
	 * nicht "scharf" stellen können, sonst müsste ein Nutzer immer auch eine Rolle
	 * bekommen (was ja auch nicht falsch wäre), aber wir kämen auch sonst nicht aus 
	 * dem Formular raus... Muss man sich noch überlegen.
	*/
	public String addRole() {
		if (checkPermission("save")) {
			if (curRole.getTask() != null && curRole.getPermission() != null) {
				((Account)curItem).getRoles().add(curRole);
				curRole.setAccount((Account)curItem);
				curRole = new Role();
				saveItem(curItem);
			} else {
				if (curRole.getTask() == null) {
					FacesMessage msg = new FacesMessage("Maßnahme darf nicht _ bleiben");
					FacesContext.getCurrentInstance().addMessage("formular:task", msg);
				}
				if (curRole.getTask() == null) {
					FacesMessage msg = new FacesMessage("Recht darf nicht _ bleiben");
					FacesContext.getCurrentInstance().addMessage("formular:permission", msg);
				}
			}
		}
		return "";
	}
	
	public String deleteRole(long id) {
		if (checkPermission("save")) {
			EntityManager em = login.getEntityManager();
			Role toDelete = em.find(Role.class, id);
			((Account)curItem).getRoles().remove(toDelete);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			saveItem(curItem);
		}
		return "";
	}
		
}
