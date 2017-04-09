package de.jotwerk.ressources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import de.jotwerk.gradoc.model.Term;

@WebListener
public class Signaturen implements ServletContextListener {

	private static List<Term> list;
	private static Map<String,Term> map;
	public static List<Term> getSignaturen() { return list; }

	@Override
	public void contextInitialized(ServletContextEvent event) {
		list = new ArrayList<Term>();
		list.add(new Term("_", "Keine Angabe"));
		list.add(new Term("A", "Glas"));
		list.add(new Term("A", "Glasindustrie"));
		list.add(new Term("C", "Keramik"));
		list.add(new Term("Cv", "Keramik mit Verzierung"));
		list.add(new Term("Cr", "Randscherbe"));
		list.add(new Term("Crv", "Randscherbe mit Verzierung"));
		list.add(new Term("Cp", "Profil"));
		list.add(new Term("Cpv", "Profil mit Verzierung"));
		list.add(new Term("Cw", "Wandscherbe"));
		list.add(new Term("Cwv", "Wandscherbe mit Verzierung"));
		list.add(new Term("Cb", "Bodenscherbe"));
		list.add(new Term("Cn", "Keramiknetzsenker"));
		list.add(new Term("Cs", "Spinnwirtel, Webgewicht"));
		list.add(new Term("Ct", "Gusstiegel, Düse etc."));
		list.add(new Term("E", "Erdproben"));
		list.add(new Term("Eb", "Botanische Erd-/Einzelprobe"));
		list.add(new Term("Es", "Sedimentologisch-pedologische Erdprobe"));
		list.add(new Term("Em", "Mollusken-Probe"));
		list.add(new Term("Ei", "Insekten-Probe"));
		list.add(new Term("Ek", "Koprolith-Probe"));
		list.add(new Term("F", "Felsgestein"));
		list.add(new Term("Fa", "Gesteinsabschlag"));
		list.add(new Term("Fak", "Kernstein, angeschlagenes Geröll"));
		list.add(new Term("Fc", "Kalksteinabschlag"));
		list.add(new Term("Fr", "Rötel"));
		list.add(new Term("Fi", "Felsgesteinindustrie allgemein"));
		list.add(new Term("Fb", "Steinbeil, Steinbeilrohling"));
		list.add(new Term("Fk", "Klopfstein, Stein mit Klopfspuren"));
		list.add(new Term("Fm", "Mahlstein"));
		list.add(new Term("Fs", "Säge"));
		list.add(new Term("Fn", "Netzsenker"));
		list.add(new Term("Fp", "Perle oder anderer Steinschmuck"));
		list.add(new Term("G", "Geweih"));
		list.add(new Term("Ga", "Rohmaterial, Abfallprodukte ohne Arbeitsspuren"));
		list.add(new Term("Gi", "Geweihindustrie"));
		list.add(new Term("Gh", "Geweihhacke"));
		list.add(new Term("Gm", "Geweihmeißel"));
		list.add(new Term("Gr", "Geweihretuscheur"));
		list.add(new Term("Gs", "Geweihspitze"));
		list.add(new Term("Gz", "Geweihzwischenfutter"));
		list.add(new Term("Gp", "Perle, Schmuckstück"));
		list.add(new Term("H", "Holz"));
		list.add(new Term("Hi", "Holzindustrie"));
		list.add(new Term("Hk", "Holzkohle"));
		list.add(new Term("Hp", "Holzprobe (einzelne Zweige und Kleinprobe, unbearbeitet)"));
		list.add(new Term("Hr", "Rinde"));
		list.add(new Term("Hs", "Späne, andere Kleinhölzer mit Schlagspuren"));
		list.add(new Term("K", "Knochen"));
		list.add(new Term("Kn", "Fauna, unbearbeitet"));
		list.add(new Term("Ki", "Knochenindustrie"));
		list.add(new Term("Km", "Knochenmeißel"));
		list.add(new Term("Kp", "Perle, Schmuckstücke"));
		list.add(new Term("Ks", "Knochenspitze"));
		list.add(new Term("Ob", "Pech/Harz"));
		list.add(new Term("Op", "Samenperle"));
		list.add(new Term("M", "Metall"));
		list.add(new Term("Mi", "Metallgeräte"));
		list.add(new Term("Ms", "Schlacken, Gusskuchen, Gusstropfen etc."));
		list.add(new Term("R", "Rezente Funde"));
		list.add(new Term("S", "Silex"));
		list.add(new Term("Sa", "Silex unretuschiert"));
		list.add(new Term("Sak", "Silexkern, angeschlagen"));
		list.add(new Term("Si", "Silex retuschiert"));
		list.add(new Term("Sb", "Bohrer"));
		list.add(new Term("Sk", "Kratzer"));
		list.add(new Term("Sm", "Messer"));
		list.add(new Term("Sp", "Pfeilspitze"));
		list.add(new Term("T", "Textilreste"));
		list.add(new Term("Ti", "Gewebe, Geflecht"));
		list.add(new Term("Tn", "Netz"));
		list.add(new Term("Tr", "Rindengefäße, Rindenrollen"));
		list.add(new Term("Ts", "einzelne Schnur, Zwirnfaden"));
		list.add(new Term("V", "Verziegelter Lehm, Hüttenlehm"));
		list.add(new Term("Vv", "Verziegelter Lehm, Hüttenlehm, verziert"));
		list.add(new Term("W", "Siebfundeinheit"));
		map = new HashMap<String,Term>();
		for (Term signatur : list) {
			map.put(signatur.getKey(), signatur);
		}	
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		list = null;		
	}
	
	public static Term getSignatur(String key) {
		return map.get(key);
	}
	
	

}
