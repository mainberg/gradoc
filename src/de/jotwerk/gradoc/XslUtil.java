package de.jotwerk.gradoc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.jotwerk.gradoc.model.Location;
import de.jotwerk.ressources.Signaturen;

public class XslUtil {	
	
	public static String toCsv(String src) {
		StringBuilder result = new StringBuilder();
		for (char c : src.toCharArray()) {
			switch((int)c) {
			case 59:
				result.append(",");
				break;
			case 13:
				result.append(" ");
				break;
			case 10:
				break;
			default:
				result.append(c);
			}
		}
		return result.toString();
	}
	
	public static String toQ(String src) {
		Double d = Double.valueOf(src);
		int i = d.intValue();
		return "" + (i % 10000);
	}
	
	public static String toQ4(String src) {
		return tail(src, 4);
	}
	
	public static String toQ3(String src) {
		return tail(src, 3);
	}
	
	public static String tail(String src, int anz) {
		int dot = src.indexOf(".");
		return dot - anz > -1 ? src.substring(dot - anz, dot) : src.substring(dot - 1);
	}
	
	public static String toVq(String xs, String ys) {
		int x = ((int)(Double.valueOf(xs) * 100)) % 100;
		int y = ((int)(Double.valueOf(ys) * 100)) % 100;
		return (x < 50) ? ((y < 50) ? "c" : "a" ) : ((y < 50) ? "d" : "b");  
	}
	
	public static String normDate(String src) {
		return src.substring(0, src.indexOf('T')).replaceAll("-", ":");
	}
	
	public static String findDescription(String key) {
		return Signaturen.getSignatur(key).getDescription();
	}
	
	public static String findSchnitt(String xs, String ys) {
		EntityManager em = Emf.createEntityManager();
		TypedQuery<Location> tq = em
				.createQuery("select l from Location l "
					+ "where l.category = 'Schnitt' "
					+ "and l.x <= :x and :x < l.x + l.a "
					+ "and l.y <= :y and :y < l.y + l.b", Location.class);
		tq.setParameter("x", Double.valueOf(xs));
		tq.setParameter("y", Double.valueOf(ys));
		List<Location> result = tq.getResultList();
		if (result.size() > 1) {
			return "mehrdeutig";
		} else if (result.size() == 1) {
			return result.get(0).getName();
		} else {
			return "keine Angabe";
		} 
	}
	

}
