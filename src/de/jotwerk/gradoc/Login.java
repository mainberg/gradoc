package de.jotwerk.gradoc;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import de.jotwerk.gradoc.model.Account;
import de.jotwerk.gradoc.model.Campaign;
import de.jotwerk.gradoc.model.Location;
import de.jotwerk.gradoc.model.Task;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userid = "";
	public String getUserid() { return userid; }
	public void setUserid(final String userid) { this.userid = userid; }
	
	private String password = "";
	public void setPassword(final String password) { this.password = password; }
	public String getPassword() { return password; }
	
	private String message;
	public String getMessage() { return this.message; }
	public void setMessage(String message) { this.message = message; }
	
	private Account account = null;
	public Account getAccount() { return account; }
	public void setAccount(Account account) { this.account = account; }
	
	private Task task = null;
	public Task getTask() { return task; }
	public void setTask(Task task) { this.task = task; }
	
	private Location location = null;
	public Location getLocation() { return location; }
	public void setLocation(final Location location) { this.location = location; }
	
	private Campaign campaign= null;
	public Campaign getCampaign() { return campaign; }
	public void setCampaign(final Campaign campaign) { this.campaign = campaign; }
	
	private String locale = "en";
	public String getLocale() { return this.locale; }
	public void setLocale(final String locale) { this.locale = locale;	}
	
	transient private EntityManager em = null;
	public EntityManager getEntityManager() {
		if (em == null) {
			em = Emf.createEntityManager();
		}
		return em;
	}
	
	@PreDestroy
	public void tearDown() {
		if (em != null) {
			em.close();
		}
	}
	
	
	public String login() {
		
		Subject subject = SecurityUtils.getSubject();		
		try {
			subject.login(new UsernamePasswordToken(userid, password));
			this.message = "login_sucessful";
			EntityManager em = Emf.createEntityManager();
			TypedQuery<Account> aq = em.createQuery("SELECT a FROM Account a WHERE a.name = :userid", Account.class);
			aq.setParameter("userid", userid);
			account = aq.getSingleResult();			
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.userid = "";
			this.task = null;
			this.message = "login_error";
		} finally {
			password = "";
		}
		
		return "";
	}
	
	public String logout() {		
		SecurityUtils.getSubject().logout();		
		this.message = "Bitte einloggen!";
		this.account = null;
		return "/index.xhtml?faces-redirect=true";
	}
		
	public void choose() { ; }
	
	public boolean isLoggedin() { return SecurityUtils.getSubject().isAuthenticated();}
	public boolean isAdmin() { return SecurityUtils.getSubject().hasRole("admin");}
	public boolean isManager() { 
		return this.task != null && (SecurityUtils.getSubject().hasRole(task.getCode() + ":manager") || isAdmin());
	}
	public boolean isEditor() { 
		return this.task != null && (SecurityUtils.getSubject().hasRole(task.getCode() + ":editor") || isManager());
	}
	
	public boolean existsTask() { return task != null; }
	public boolean existsCampaign() { return campaign != null; }
	public boolean existsLocation() { return location != null; }
	
	
	
	
}
