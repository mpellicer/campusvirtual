package cat.udl.asic.app.einaenqvirtual;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.util.Locale;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ResourceBundle;
import cat.udl.asic.app.einaenqvirtual.GuardarBean;
import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.application.Application;

import org.sakaiproject.tool.api.Placement;
import org.sakaiproject.site.api.ToolConfiguration;
import org.sakaiproject.site.cover.SiteService;
import org.sakaiproject.tool.cover.ToolManager;
import org.sakaiproject.tool.api.Placement;


import org.apache.myfaces.custom.popup.HtmlPopup;
import java.io.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import java.lang.*;
import javax.faces.component.UIComponent;

public class GestorBotons implements ActionListener {
		
	public static HashMap respostes;
	public static HashMap enq_codnum;
	public static HashMap prg_codnum;
	public static HashMap tipus_prg;
	public static EinaEnqVirtualService s;	
	public static String dni;
	
	private String bundleId = null;
	
	
	
	public void omplirParametre(HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg,EinaEnqVirtualService s,String dni)
	{
		this.respostes=respostes;			
		this.enq_codnum=hmEnq_codnum;
		this.prg_codnum=hmPrg_codnum;
		this.tipus_prg=hmTipus_prg;
		this.s=s;
		this.dni=dni;
	}
	
	public int totContestat(ActionEvent event)
	{
		
		// totple: si es 0 es que no esta tot ple, si es 1 es que si que esta ple
		int totple=1;
		String boto = event.getComponent().getId();
		String boto2;
		if (boto.charAt(0)=='g')
		{
			boto2= boto.substring(0,boto.length()-1)+'b';
		}
		else
		boto2= boto.substring(0,boto.length()-1)+'g';		
		if (respostes!=null && respostes.size()!=0 && enq_codnum!=null && enq_codnum.size()!=0 && prg_codnum!=null && prg_codnum.size()!=0 && tipus_prg!=null && tipus_prg.size()!=0)
		{
			Set keySet = respostes.keySet();
			for ( Iterator iter = keySet.iterator(); iter.hasNext(); )
	  		{
		 		String key = (String) iter.next().toString();
		 		String clau = key.substring(0,boto.toString().length());
		 		String bo = boto2.substring(0,boto2.toString().length());				
				if (clau.equals(bo))	//Per a que només grabi els inputs de boto que apreta
				{
		
					Integer t_prg = (Integer)tipus_prg.get(key);
					String text = new String("");
					Integer opcio = new Integer(0);
					List<Integer> e_codnum = (List<Integer>)enq_codnum.get(key);
					Integer p_codnum = (Integer)prg_codnum.get(key);
					if (t_prg.intValue()!=7)	// Si no es una pregunta de text lliure
					{
						String op = (String)respostes.get(key);
						// Comprovem que esta a null o en blanc
						if (op==null || op.equals(""))
						{
							totple = 0;
						}
					}
				}
	  		}
			
		}
		return totple;
	}

	public void processAction(ActionEvent event) 
	{
		String boto2;
		// Per a buscar els missatges de sortida
		ResourceBundle messageBundle = ResourceBundle.getBundle("cat.udl.asic.app.einaenqvirtual.Messages");
		
		
		// Recuperem el id del boto per saber en quina pestanya estem

		String boto = event.getComponent().getId();
		if (boto.charAt(0)=='g')
		{
			boto2= boto.substring(0,boto.length()-1)+'b';
		}
		else
		 boto2= boto.substring(0,boto.length()-1)+'g';
//		String boto2= boto.substring(0,boto.length()-1)+'g';
		
		HashMap tque_contestat = new HashMap();
		FacesMessage facesMessage;

		
		/******** Accedim a les propietats de configuració de l'eina ***/		
		Placement p = ToolManager.getCurrentPlacement();
		ToolConfiguration toolConfig = SiteService.findTool(p.getId());		
		/******** Recuperem el codi del model d'enquesta ****************/
		String model = toolConfig.getPlacementConfig().getProperty("codimodel");
		int codiMod = Integer.parseInt(model);
		/******** Busquem el tipparam a partir del model *****************/
		String tipParam = s.GetTipParam(codiMod);		
		

			//************** Fem el bucle per recorrer els HashMaps i guardar-ho a la bbdd ************************
	
			fi: if (respostes!=null && respostes.size()!=0 && enq_codnum!=null && enq_codnum.size()!=0 && prg_codnum!=null && prg_codnum.size()!=0 && tipus_prg!=null && tipus_prg.size()!=0)
			{
				//************** Comprovem previament que s'han contestat totes les preguntes *************************
				if (totContestat(event)==1)
				{
					
					Set keySet = respostes.keySet();
					for ( Iterator iter = keySet.iterator(); iter.hasNext(); )
			  		{
				 		String key = (String) iter.next().toString();
				 		
				 //		String clau = key.substring(0,2);
				 //		String bo = boto.substring(0,2);
				 		String clau = key.substring(0,boto.toString().length());
				 		String bo = boto2.substring(0,boto2.toString().length());
						if (clau.equals(bo))	//Per a que només grabi els inputs de boto que apreta
						{
							Integer t_prg = (Integer)tipus_prg.get(key);
							String text = new String("");
							Integer opcio = new Integer(0);
							List<Integer> e_codnum = (List<Integer>)enq_codnum.get(key);
							Integer p_codnum = (Integer)prg_codnum.get(key);
			
							if (t_prg.intValue()==7)	// text lliure
							{
								text = (String)respostes.get(key);			
							}
							else	// opcio radio
							{			
								String op = (String)respostes.get(key);			
								if (op==null || op.equals(""))
								{						
									// Missatge d'error de que falten omplir opcions
									FacesContext ctx = FacesContext.getCurrentInstance();
									//Application application = ctx.getApplication();																	
									//ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, "error");
									
							 		facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,messageBundle.getString ("marcarRespostes"), messageBundle.getString ("marcarRespostes"));
							 		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(ctx), facesMessage);					 		
							 		opcio = new Integer(0);
							 		break fi; 
								}
								else				
									opcio = Integer.valueOf(op);				
							}
							
							// Insertem a la bbdd
							for (int j=0; j<e_codnum.size();j++)	//Per cada enq_codnum fer una inserció
							{
								Integer e_cod = (Integer)e_codnum.get(j);
								s.setResposta(e_cod,p_codnum,opcio,text);
								
								// Guardem els diferents enq_codnum per afegir-los a la taula TQUE_CONTESTAT				
								if (!tque_contestat.containsKey(e_cod)) 				
									tque_contestat.put(e_cod,"");
												
							}
						}
			  		}
						
					// Ara afegim a la taula TQUE_CONTESTAT els enq_codnum per a que no s'introdueixi mes vegades
					Set keySet_contestat = tque_contestat.keySet();
					for ( Iterator iter_contestat = keySet_contestat.iterator(); iter_contestat.hasNext(); )
		  			{
				 		Integer key_contestat = (Integer) iter_contestat.next();
				 		s.setTQue_contestat(key_contestat,dni);
					 					 		
				 		// Actualitzem el camp NCONTESTATS del tipparam que li pertoqui (segons el model)			 					 					 	
				 		s.setNContestats(key_contestat,tipParam);
					 		
		  			}
						
					// LLimpiem o actualitzem per a que al carregar posi que ja s'ha grabat.
					FacesContext ctx = FacesContext.getCurrentInstance();
					Application application = ctx.getApplication(); 		
					ctx.getViewRoot().getChildren().clear();
				}
				else {					
					// Missatge d'error de que falten omplir opcions
					FacesContext ctx = FacesContext.getCurrentInstance();
					//Application application = ctx.getApplication();																	
					//ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, "error");
					
			 		facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,messageBundle.getString ("marcarRespostes"), messageBundle.getString ("marcarRespostes"));
			 		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(ctx), facesMessage);					 					 	 
				}
			
		}
	}
}