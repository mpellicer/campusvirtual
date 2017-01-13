package cat.udl.asic.jobs;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
	
// serveis que necessitem
import org.sakaiproject.authz.api.SecurityService;
import org.sakaiproject.user.api.User;
import org.sakaiproject.db.api.SqlService;
import org.sakaiproject.authz.api.SecurityAdvisor;
import org.sakaiproject.authz.api.SecurityAdvisor.SecurityAdvice;

import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.exception.IdUsedException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.entity.api.ResourceProperties;
import org.sakaiproject.entity.api.ResourcePropertiesEdit;

import org.sakaiproject.entity.api.EntityManager;
import org.sakaiproject.entity.api.Reference;
import org.sakaiproject.announcement.api.AnnouncementService;
import org.sakaiproject.announcement.api.AnnouncementChannel;
import org.sakaiproject.announcement.api.AnnouncementMessage;
import org.sakaiproject.announcement.api.AnnouncementMessageHeader;

import org.sakaiproject.authz.api.AuthzGroupService;
import org.sakaiproject.authz.api.AuthzGroup;
import org.sakaiproject.authz.api.Member;

import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.site.api.Site;
import org.sakaiproject.site.api.ToolConfiguration;

import org.sakaiproject.component.api.ServerConfigurationService;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

// codi per a les consultes sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringEscapeUtils;

//Codi per les connexions al servei de missatgeria
import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope; 
import org.apache.commons.httpclient.methods.StringRequestEntity;
import java.io.IOException;

import org.sakaiproject.time.api.Time;
import org.sakaiproject.time.api.TimeService;

public class EnviaNotificacionsAppMobil implements Job {

	//Inicialitzem el log
	static Logger M_log = Logger.getLogger(EnviaNotificacionsAppMobil.class);

	//Serveis requerits per executar el JOB
	private SecurityService securityService;
	private SqlService sqlService;
	private SecurityAdvisor securityAdvisor;
	private AuthzGroupService instanciaAuthzGroupService;
	protected EntityManager entityManager;
	private AnnouncementService instanciaAnnouncementService;
	private SiteService instanciaSiteService;
	private ServerConfigurationService instanciaServerConfigurationService;
	private TimeService instanciaTimeService;
	
	
	
	//GETTERS i SETTERS dels serveis de Sakai
	public void setSecurityService(SecurityService securityService) {
	    this.securityService = securityService;
	}

	//Carreguem el sqlService. Cuidado pq aquests esta configurat diferent al components i configurat al sakai.properties
	public void setSqlService(SqlService sqlService) {
	    this.sqlService = sqlService;
	}
	
	public void setAuthzGroupService(AuthzGroupService instanciaAuthzGroupService) {
		this.instanciaAuthzGroupService = instanciaAuthzGroupService;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void setAnnouncementService(AnnouncementService instanciaAnnouncementService) {
	    this.instanciaAnnouncementService = instanciaAnnouncementService;
	}
	
	public void setSiteService(SiteService instanciaSiteService) {
	    this.instanciaSiteService = instanciaSiteService;
	}
	
	public void setServerConfigurationService(ServerConfigurationService instanciaServerConfigurationService) {
	    this.instanciaServerConfigurationService = instanciaServerConfigurationService;
	}
	
	public void setTimeService(TimeService instanciaTimeService) {
	    this.instanciaTimeService = instanciaTimeService;
	}
	

	//Habilita i deshabilita el supervisor de seguretat per permetre desar dades del model
	private void enableSecurityAdvisor() {
		securityService.pushAdvisor(securityAdvisor);
	}

	private void disableSecurityAdvisor() {
		securityService.popAdvisor();
	}

	//SQLs que s'hauran d'executar 
	static String MESSAGES_TO_PUSH = "SELECT ENTITYREFERENCE FROM ENTITY_TO_PUSH";
	static String MESSAGES_TO_DELETE = "DELETE FROM ENTITY_TO_PUSH WHERE ENTITYREFERENCE = ?";
	
	//Inicialització del JOB 
	public void init() {

		//Create our security advisor.
		securityAdvisor = new SecurityAdvisor() {
			public SecurityAdvice isAllowed(String userId, String function,
					String reference) {
				return SecurityAdvice.ALLOWED;
			}
		};
		/* Preparem entorn per que log4j trobi el fitxer de configuració al directori /conf del tomcat */
		PropertyConfigurator.configure(System.getProperty("catalina.home") + "/conf/log4j.properties");
		
		M_log.setAdditivity(false);
        M_log.debug("Inicia JOB Envia Notificacions App Mobil");
    	
	}
	
	
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		M_log.debug("EnviaNotificacionsAppMobil: Execucio JOB - Enviament de notificacions al servei App Mobil");

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String entityReference = "";
		
		
		//Activem l'opció per permetre canviar dades
		enableSecurityAdvisor();
		
		try {
			HttpClient httpclient = authenticate();
			if (httpclient == null) {
				M_log.warn("EnviaNotifAppMobil: Authentication to server failed");
				//return;
			}
			connection = sqlService.borrowConnection();
			M_log.debug ("EnviaNotifAppMobil: Preparem statement "+MESSAGES_TO_PUSH);
			stmt = connection.prepareStatement(MESSAGES_TO_PUSH);

			// Recuperem els identificadors dels missatges que cal enviar
			rst = stmt.executeQuery();

			while (rst.next()) {
				
				PreparedStatement deleteStmt = null;
				boolean published = false;
				try {
					entityReference = rst.getString("ENTITYREFERENCE");
					if (entityReference != null) {
						M_log.debug ("EnviaNotifAppMobil: Processant " + entityReference);
						Reference ref = entityManager.newReference(entityReference);
						String msgId = ref.getId();
						String notificationUrl = "";
						String channelRefTemp = entityReference.replace("/"+msgId,"");
						String channelRef = channelRefTemp.replace("msg","channel");
						String msgContext = ref.getContext();
						AnnouncementChannel channel = instanciaAnnouncementService.getAnnouncementChannel(channelRef);
						AnnouncementMessage msg = channel.getAnnouncementMessage(msgId);
						AnnouncementMessageHeader msgHeader = msg.getAnnouncementHeader();
						boolean isDraft = msgHeader.getDraft();
						// comprovem que no sigui un esborrany
						if (!isDraft) {
							// comprovem la release date per saber si cal enviar aquest missatge al servei de missatgeria
							// primer comprovem si hi ha release date definida
							if (msg.getProperties().get(instanciaAnnouncementService.RELEASE_DATE) != null) {
								Time releaseDate = msg.getProperties().getTimeProperty(instanciaAnnouncementService.RELEASE_DATE);
								M_log.debug ("EnviaNotifAppMobil: releaseDate " +releaseDate.toStringLocal());
								Time now = instanciaTimeService.newTime();
								if (now.after(releaseDate)) {
									M_log.debug ("EnviaNotifAppMobil: Hem superat la releaseDate ");
									published = true;
								}
							}
							else {
								M_log.debug ("EnviaNotifAppMobil: releaseDate not defined");
								published = true;
							}
						}
						if (published) {
							// recuperem el cos del missatge
							String body = msg.getBody();
							// recuperem l'assumpte del missatge
							String subject = msgHeader.getSubject();
							// informació de l'autor del missatge
							User author = msgHeader.getFrom();
							String authorDisplayId = author.getDisplayId();
							M_log.debug ("EnviaNotifAppMobil: authorDisplayId " + authorDisplayId);
							String messageAuthor = author.getFirstName()+" "+author.getLastName();
							M_log.debug ("EnviaNotifAppMobil: author "+messageAuthor);
							// recuperem a qui s'ha d'enviar el missatge
							Site site = instanciaSiteService.getSite(msgContext);
							M_log.debug ("EnviaNotifAppMobil: context "+msgContext);
							ToolConfiguration toolConfig = site.getToolForCommonId("sakai.announcements");
							String pageId = toolConfig.getPageId();
							//notificationUrl = site.getUrl()+"/page/"+pageId;
							notificationUrl = "https://cv.udl.cat/portal/site/"+ site.getId()+ "/page/"+pageId;
							M_log.debug ("EnviaNotifAppMobil: NotificationUrl " + notificationUrl);
							String access = msgHeader.getAccess().toString();
							M_log.debug ("EnviaNotifAppMobil: access " +access);
							ResourceProperties siteProperties = site.getProperties();
							String categoryId = siteProperties.getProperty("categoryId");
							// comprovem si l'espai té definida una categoryId
							if (categoryId != null){
								// si està definit el categoryId s'envia com a recipientIds
								categoryId = "[\""+categoryId+"\"]";
								if (send(httpclient,subject,messageAuthor,body,msgContext,site.getTitle(),notificationUrl,categoryId)) {
				                	M_log.debug("EnviaNotifAppMobil: Missatge enviat correctament a la categoria "+categoryId);
				                	M_log.debug("EnviaNotifAppMobil: Procedim a eliminar el registre "+entityReference);
				                	deleteStmt = connection.prepareStatement(MESSAGES_TO_DELETE);
				                	deleteStmt.setString(1, entityReference);
				                	deleteStmt.executeUpdate();
				                	connection.commit();
				                	deleteStmt.close();
				                	M_log.debug("EnviaNotifAppMobil: Hem eliminat el registre "+entityReference);
				                }
				                else {
				                	M_log.warn("EnviaNotifAppMobil: Server returned error");
				                }
							}
							else {
								// si no està definit el categoryId cal determinar la llista d'usuaris als que s'ha d'enviar el missatge
								if (access.equals("channel")) {
									// s'ha d'enviar a tots els membres del site
									Set members = site.getMembers();
									String siteMembers = "[";
					                for(Iterator memberIter = members.iterator(); memberIter.hasNext();) {
					                    Member member = (Member)memberIter.next();
					                    String userEid = (String) member.getUserEid();
					                	M_log.debug ("EnviaNotifAppMobil: "+userEid+" is site member");
					                	siteMembers = siteMembers + "\""+userEid+"\"";
					                	if (memberIter.hasNext()){
					                		siteMembers = siteMembers + ",";
					                	}else{
					                		siteMembers = siteMembers + "]";
					                	}
					                }
					                if (send(httpclient,subject,messageAuthor,body,msgContext,site.getTitle(),notificationUrl,siteMembers)) {
					                	M_log.debug("EnviaNotifAppMobil: Missatge enviat correctament als membres del site");
					                	M_log.debug("EnviaNotifAppMobil: Procedim a eliminar el registre "+entityReference);
					                	deleteStmt = connection.prepareStatement(MESSAGES_TO_DELETE);
					                	deleteStmt.setString(1, entityReference);
					                	deleteStmt.executeUpdate();
					                	connection.commit();
					                	deleteStmt.close();
					                	M_log.debug("EnviaNotifAppMobil: Hem eliminat el registre "+entityReference);
					                }
					                else {
					                	M_log.warn("EnviaNotifAppMobil: Server returned error");
					                }
								}
								else if (access.equals("grouped")) {
									// només als membres dels grups autoritzats
									Collection <String> groupIds = msgHeader.getGroups();
									String groupMembers = "[";
									for(Iterator groupsIter = groupIds.iterator(); groupsIter.hasNext();) {
											String groupId = (String) groupsIter.next();
											M_log.debug ("EnviaNotifAppMobil: GroupId " +groupId);
											AuthzGroup authzGroup = instanciaAuthzGroupService.getAuthzGroup(groupId);
											Set membersGroup = authzGroup.getMembers();
								                for(Iterator membersGroupIter = membersGroup.iterator(); membersGroupIter.hasNext();) {
								                	 Member memberGroup = (Member) membersGroupIter.next();
								                	 String userEidGroup = (String) memberGroup.getUserEid();
								                	 M_log.debug ("EnviaNotifAppMobil: "+userEidGroup+" is member of this group");
								                	 groupMembers = groupMembers + "\""+userEidGroup+"\"";
									                	if (membersGroupIter.hasNext()){
									                		groupMembers = groupMembers + ",";
									                	}
								                }
								            if (groupsIter.hasNext()){
								            	groupMembers = groupMembers + ",";
								            } else {
								            	groupMembers = groupMembers + "]";
								            }
									}
									if (send(httpclient,subject,messageAuthor,body,msgContext,site.getTitle(),notificationUrl,groupMembers)) {
					                	M_log.debug("EnviaNotifAppMobil: Missatge enviat correctament als membres dels grups autoritzats");
					                	M_log.debug("EnviaNotifAppMobil: Procedim a eliminar el registre "+entityReference);
					                	deleteStmt = connection.prepareStatement(MESSAGES_TO_DELETE);
					                	deleteStmt.setString(1, entityReference);
					                	deleteStmt.executeUpdate();
					                	connection.commit();
					                	deleteStmt.close();
					                	M_log.debug("EnviaNotifAppMobil: Hem eliminat el registre "+entityReference);
					                }
					                else {
					                	M_log.warn("EnviaNotifAppMobil: Server returned error");
					                }
								}
								else {
									M_log.warn ("EnviaNotifAppMobil: access no és ni channel ni grouped ");
								}
							}		
						}
						else {
							M_log.debug ("EnviaNotifAppMobil: És un esborrany o encara no hem arribat a la releaseDate");
						}
					}
				} catch (Exception ex) {
					M_log.error("EnviaNotifAppMobil: Error processing");
					ex.printStackTrace ();
				}

			}
			
			//Tanquem el cursor
			rst.close();
			
		} catch (SQLException e) {
			M_log.error("EnviaNotifAppMobil: EXCEPCIO SQL(EnviaNotifAppMobil) ");
			M_log.error("EnviaNotifAppMobil: EXCEPCIO SQL " + e);
		} catch (Exception ex) {
			M_log.error("EnviaNotifAppMobil: EXCEPCIO general ");
			M_log.error(ex.getClass().getName() + " :: " + ex.getMessage());
		} finally {

			disableSecurityAdvisor();
			
			try {
					//Tanquem el statement principal
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				M_log.error("EnviaNotifAppMobil: EXCEPCIO SQL al tancar statement");
				M_log.error("EnviaNotifAppMobil: SQLException: " + e);
			}

			//Tanquem la connexió
			if (connection != null){
				sqlService.returnConnection(connection);
			}
				
		}
	}

	private HttpClient authenticate() throws Exception {
		HttpClient httpclient = new HttpClient();
		
		String urlAuthAppMobilServer = instanciaServerConfigurationService.getString("appMobil.urlServerAuth"); 
		String userAppMobilServer = instanciaServerConfigurationService.getString("appMobil.user"); 
		String pwdAppMobilServer = instanciaServerConfigurationService.getString("appMobil.password"); 

		M_log.debug ("EnviaNotifAppMobil: Url a la que autentiquem "+urlAuthAppMobilServer);
        
        httpclient.getState().setCredentials(
         	AuthScope.ANY,
         	new UsernamePasswordCredentials(userAppMobilServer, pwdAppMobilServer)
        );
	       
		   PostMethod post = new PostMethod(urlAuthAppMobilServer);
		   post.setDoAuthentication( true );
		   
           try {
        	   int response = httpclient.executeMethod(post);
        	   		M_log.debug("EnviaNotifAppMobil: Server response is "+response);
        	   		//System.out.println("EnviaNotiAppMobil -- Response post "+post.getResponseBodyAsString());	
	        }catch (Exception ex){
	        	ex.printStackTrace();
	        	httpclient = null;
	        }
           finally {
	        	 post.releaseConnection();
           }
           return httpclient;
	}
	
	private boolean send(HttpClient httpclient, String subject, String author, String content, String siteId, String siteTitle, String notificationUrl, String receptientsIds) throws Exception {
			
			boolean retorn = false;
			String subjectEscapat = StringEscapeUtils.escapeJson(subject);
			String authorEscapat = StringEscapeUtils.escapeJson(author);
			String bodyEscapat = StringEscapeUtils.escapeJson(content);
			String urlMessagesAppMobilServer = instanciaServerConfigurationService.getString("appMobil.urlMessages"); 
			//System.out.println(" Subject escapat "+subjectEscapat);
			//System.out.println(" Body escapat " +bodyEscapat);
			
			
			String JSON_STRING = "{"
						+ "\"subject\":\""+ subjectEscapat+ "\"," 
						+ "\"author\":\""+ authorEscapat+ "\"," 
						+ "\"content\":\""+ bodyEscapat+ "\","
						+ "\"siteId\":\""+ siteId+ "\","
						+ "\"siteTitle\":\""+ siteTitle+ "\","
						+ "\"notiURL\":\""+notificationUrl+ "\","
						+ "\"receptientsIds\":"+ receptientsIds+ "}";
			
			// System.out.println(" La string que enviem " +JSON_STRING);
			//System.out.println(" Url a la que enviem " +urlMessagesAppMobilServer);		
			
			
			StringRequestEntity requestEntity = new StringRequestEntity(
				    JSON_STRING,
				    "application/json",
				    "UTF-8");
			PostMethod postMessage = new PostMethod(urlMessagesAppMobilServer);
			postMessage.setRequestEntity(requestEntity);
			try {
				if (httpclient != null) {
					int postResponseCode = httpclient.executeMethod(postMessage);
					M_log.debug("EnviaNotifAppMobil: Server response is "+postResponseCode);
					//System.out.println("EnviaNotifAppMobil Server response post: "+postMessage.getResponseBodyAsString());	
					 if (postResponseCode != 200) {
						   M_log.debug("EnviaNotifAppMobil: Server response is not OK");
					   }
					   else {
						   retorn = true;
					   }
				}
				else {
					M_log.debug("EnviaNotifAppMobil: httpclient is null");
				}
				
	        }catch (Exception ex){
	        	ex.printStackTrace();
	        }
           finally {
        	   postMessage.releaseConnection();
	        } 
		   return retorn;
	    }
}
