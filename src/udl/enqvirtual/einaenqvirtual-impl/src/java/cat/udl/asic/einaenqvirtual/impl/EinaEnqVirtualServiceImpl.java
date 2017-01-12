package cat.udl.asic.einaenqvirtual.impl;

import java.util.*;
import java.io.*;
import java.lang.Integer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cat.udl.asic.einaenqvirtual.api.*;
import org.sakaiproject.db.api.SqlReader;
import org.sakaiproject.db.api.SqlService;
import org.sakaiproject.site.api.Site;
import org.sakaiproject.site.cover.SiteService;
import cat.udl.asic.einaenqvirtual.api.EinaEnqVirtualService;

import org.sakaiproject.email.cover.EmailService;
import org.sakaiproject.tool.cover.ToolManager;
import org.sakaiproject.tool.api.Placement;
import org.sakaiproject.site.api.ToolConfiguration;


//import javax.servlet.*;



public  class EinaEnqVirtualServiceImpl implements EinaEnqVirtualService{

	//*************************************** CONSULTES SQL ***************************************//
	//**************************** s'agafen de system.properties de app *************************//	

//	static String sqlModel = "select codnum,desid1,tparam,ordre,visible from TQUE_MODEL where CODNUM = ?";
//	static String sqlAssignatures = "select distinct(ass_codnum) as ass_codnum,assignatura from VQUE_GRUPSENQASSPRF where ALU_DNIALU = ? AND ANYACA=? AND MOD_CODNUM=? ORDER BY ass_codnum";	
//	static String sqlGrupEnqAssPrf = "select ALU_DNIALU,NUMORD,PLA_CODALF,MOD_CODNUM,ANYACA,CEN_CODNUM,DUR_CODALF,ASS_CODNUM,GAS_CODNUM,PRF_CODALF,GRP_DESID1,DNI,DATA,HORA,AULA,FLG_RECOLLIT,FLG_PASSAT,FLG_ACTIVAT,NMAT,NCONTESTATS,FLG_RESPONSABLE,ENVIAR_A,QUE_CODNUM,ENQ_CODNUM,INCIDENCIES,BECARI,NUM_FULLS,FULL_INICI,FULL_FI,PLA,CREDITS_PRF,CENTRE,DURADA,PROFESSOR,DEPARTAMENT,ASSIGNATURA from VQUE_GRUPSENQASSPRF where ALU_DNIALU = ? AND ANYACA=? AND MOD_CODNUM=? AND ASS_CODNUM=?";
	
//	static String sqlGrupsAssignatura = "select ENQ_CODNUM from VQUE_GRUPSENQASSPRF where ALU_DNIALU = ? AND ANYACA=? AND MOD_CODNUM=? AND ASS_CODNUM=?";			
//	static String sqlGrupsAssignatura1 = "select DISTINCT(QUE_CODNUM) from VQUE_GRUPSENQASSPRF where ALU_DNIALU = ? AND ANYACA=? AND MOD_CODNUM=? AND ASS_CODNUM=?";
	
//	static String sqlQuestionari = "select codnum,desid1,desid2,desid3,ordre,visible from TQUE_QUESTIONARI where CODNUM = ?";
//	static String sqlBloc = "select codnum,que_codnum,desid1,desid2,desid3,tipbloc from TQUE_BLOC where QUE_CODNUM = ? ORDER BY CODNUM";
//	static String sqlBlocTipus = "select codnum,que_codnum,desid1,desid2,desid3,tipbloc from TQUE_BLOC where TIPBLOC = ? ORDER BY CODNUM";
//	static String sqlBlocDigitTipus = "select codnum,que_codnum,desid1,desid2,desid3,tipbloc from TQUE_BLOC where QUE_CODNUM = ? AND (substr(TIPBLOC, 1 ,1) = ? OR TIPBLOC = ?) ORDER BY CODNUM";

//	static String sqlPregunta = "select p.codnum,p.text,p.tipprg_codnum,b.que_codnum,p.prg_vell,b.ordre from TQUE_PREGUNTA p, TQUE_BLOCPREGUNTA b where b.prg_codnum = p.codnum AND b.QUE_CODNUM = ? and b.blo_codnum= ? order by b.ordre";
		
//	static String sqlOpcio = "select codnum,tipprg_codnum,val_codnum,val_codalf from TQUE_TIPRESPOSTA where TIPPRG_CODNUM = ?";
//	static String sqlContestat = "select * from TQUE_CONTESTAT where ENQ_CODNUM = ? AND DNI=?";

//	static String sqlInsertResposta = "insert into TQUE_RESPOSTA(ENQ_CODNUM,PRG_CODNUM,RES_CODNUM,USUARI,TEXT_RESP) values (?,?,?,?,?)";
//	static String sqlInsertContestat = "insert into TQUE_CONTESTAT(ENQ_CODNUM,DNI) values (?,?)";
	

	// M2
//	static String sqlGrupEnqGrp = "select ALU_DNIALU,PLA_CODALF,MOD_CODNUM,ANYACA,CEN_CODNUM,QUE_CODNUM,ENQ_CODNUM,INCIDENCIES,CENTRE,NOMPLA from VQUE_GRUPSM2 where ALU_DNIALU = ? AND ANYACA=? AND MOD_CODNUM=?";
//	static String sqlBlocEnqGrp = "select codnum,que_codnum,desid1,desid2,desid3,tipbloc from TQUE_BLOC where QUE_CODNUM = ? ORDER BY CODNUM";
	
	
	
	// Actualitzar el valor de NCONTESTATS	
//	static String sqlUpdateNContestats1 = "UPDATE tque_tipparam1 set NCONTESTATS=NVL(NCONTESTATS,0)+1 WHERE ENQ_CODNUM = ?";
//	static String sqlUpdateNContestats2 = "UPDATE tque_tipparam2 set NCONTESTATS=NVL(NCONTESTATS,0)+1 WHERE ENQ_CODNUM = ?";
//	static String sqlUpdateNContestats3 = "UPDATE tque_tipparam3 set NCONTESTATS=NVL(NCONTESTATS,0)+1 WHERE ENQ_CODNUM = ?";
//	static String sqlUpdateNContestats4 = "UPDATE tque_tipparam4 set NCONTESTATS=NVL(NCONTESTATS,0)+1 WHERE ENQ_CODNUM = ?";
//	static String sqlUpdateNContestats5 = "UPDATE tque_tipparam5 set NCONTESTATS=NVL(NCONTESTATS,0)+1 WHERE ENQ_CODNUM = ?";
//	static String sqlUpdateNContestats6 = "UPDATE tque_tipparam6 set NCONTESTATS=NVL(NCONTESTATS,0)+1 WHERE ENQ_CODNUM = ?";	
	
	//************************************* FI CONSULTES SQL ***************************************//
	

	public static final String QUOTE_PATTERN = "'";
	public static final String QUOTE_REPLACE = "\'\'";	

	private SqlService sqlService = null;
	private SqlReader  sqlReader = null;	
	private Properties ldapPropietats=null;

	public void init(){
		
	}
	
	public void  setSqlService (SqlService s){
		sqlService = s;
	}

	public SqlService getSqlService(){
		return sqlService;
	}

	
	
	
	public ModelEnqAssPrf getModel(int codiMod, final String dni, final String anyaca, final EinaEnqVirtualService s){
		String sqlModel = Property.sysProperties.getProperty("sqlModel");		
		
		final int pcodiMod = codiMod;
		ModelEnqAssPrf model = new ModelEnqAssPrf();
		Object[] fields = new Object[1];
		fields[0] = pcodiMod;		

		List list = sqlService.dbRead(sqlModel, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{				
				try
				{					
					int codnum = result.getInt(1);										
					String desid1 = result.getString(2);
					if(desid1 == null) {desid1 = "";}
					String tparam = result.getString(3);
					if(tparam == null) {tparam = "";}
					int ordre = result.getInt(4);
					String visible = result.getString(5);
					if(visible == null) {visible = "";}					
					ModelEnqAssPrf mod = new ModelEnqAssPrf(codnum, desid1, tparam, ordre, visible,anyaca,dni,s);
					
					return mod;
				}
				catch (SQLException e)
				{					
					e.printStackTrace();
					return null;
				}
			}
		});
		if (list != null)
		{			
			Iterator iter = list.iterator();
			if (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					model = (ModelEnqAssPrf) val;					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}							
		return model;		
	}
	public ModelEnqGrp getModelEnqGrp(int codiMod, final String dni, final String anyaca, final EinaEnqVirtualService s){
		
		String sqlModel = Property.sysProperties.getProperty("sqlModel");	

		final int pcodiMod = codiMod;
		ModelEnqGrp model = new ModelEnqGrp();
		Object[] fields = new Object[1];
		fields[0] = pcodiMod;		

		List list = sqlService.dbRead(sqlModel, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{				
				try
				{					
					int codnum = result.getInt(1);										
					String desid1 = result.getString(2);
					if(desid1 == null) {desid1 = "";}
					String tparam = result.getString(3);
					if(tparam == null) {tparam = "";}
					int ordre = result.getInt(4);
					String visible = result.getString(5);
					if(visible == null) {visible = "";}					
					ModelEnqGrp mod = new ModelEnqGrp(codnum, desid1, tparam, ordre, visible,anyaca,dni,s,pcodiMod);
					
					return mod;
				}
				catch (SQLException e)
				{					
					e.printStackTrace();
					return null;
				}
			}
		});
		if (list != null)
		{			
			Iterator iter = list.iterator();
			if (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					model = (ModelEnqGrp) val;					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}							
		return model;		
	}

	
	
	public List<GrupEnqAssPrf> getGrupEnqAssPrf(String dni, String anyAca, int cod_assig,int model, final EinaEnqVirtualService s){

		String sqlGrupEnqAssPrf = Property.sysProperties.getProperty("sqlGrupEnqAssPrf");
		
		final String pdni = dni;
		final String panyAca = anyAca;
		final int pmodel = model;
		final int pcod_assig = cod_assig;

		List<GrupEnqAssPrf> lgrups = new ArrayList<GrupEnqAssPrf>();

		Object[] fields = new Object[4];
				 fields[0] = pdni;
				 fields[1] = panyAca;
				 fields[2] = pmodel;
				 fields[3] = pcod_assig;
			
		List list = sqlService.dbRead(sqlGrupEnqAssPrf, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
					int que_codnum = result.getInt(23);					
					int e_cod = result.getInt(24);
					Integer enq = new Integer(e_cod);
					List<Integer> enq_codnum = new ArrayList<Integer>();
					enq_codnum.add(enq);					
					
					String anyaca = result.getString(5);
					if (anyaca == null) {anyaca = "";}
					int cen_codnum = result.getInt(6);					
					int dur_codalf = result.getInt(7);					
					int ass_codnum = result.getInt(8);					
					int gas_codnum = result.getInt(9);					
					String prf_codalf = result.getString(10);
					if (prf_codalf == null) {prf_codalf = "";}
					String grp_desid1 = result.getString(11);
					if (grp_desid1 == null) {grp_desid1 = "";}
					String dni = result.getString(12);
					if (dni == null) {dni = "";}
					String data = result.getString(13);
					if (data == null) {data = "";}
					String hora = result.getString(14);
					if (hora == null) {hora = "";}
					String aula = result.getString(15);
					if (aula == null) {aula = "";}
					String flg_recollit = result.getString(16);
					if (flg_recollit == null) {flg_recollit = "";}
					String flg_passat = result.getString(17);
					if (flg_passat == null) {flg_passat = "";}
					String flg_activat = result.getString(18);
					if (flg_activat == null) {flg_activat = "";}
					int nmat = result.getInt(19);					
					int ncontestats = result.getInt(20);					
					String flg_responsable = result.getString(21);
					if (flg_responsable == null) {flg_responsable = "";}
					String enviar_a = result.getString(22);
					if (enviar_a == null) {enviar_a = "";}
					String incidencies = result.getString(25);
					if (incidencies == null) {incidencies = "";}
					String becari = result.getString(26);
					if (becari == null) {becari = "";}
					int num_fulls = result.getInt(27);					
					String full_inici = result.getString(28);
					if (full_inici == null) {full_inici = "";}
					String full_fi = result.getString(29);
					if (full_fi == null) {full_fi = "";}
					String pla = result.getString(30);
					if (pla == null) {pla = "";}
					int credits_prf = result.getInt(31);									
					String alu_dnialu = result.getString(1);
					if (alu_dnialu == null) {alu_dnialu = "";}
					int numord = result.getInt(2);					
					String pla_codalf = result.getString(3);
					if (pla_codalf == null) {pla_codalf = "";}
					int mod_codnum = result.getInt(4);
					
					String centre = result.getString(32);
					if (centre == null) {centre = "";}
					String durada = result.getString(33);
					if (durada == null) {durada = "";}
					String professor = result.getString(34);
					if (professor == null) {professor = "";}
					String departament = result.getString(35);
					if (departament == null) {departament = "";}
					String assignatura = result.getString(36);
					if (assignatura == null) {assignatura = "";}

					
					GrupEnqAssPrf gm1 = new GrupEnqAssPrf(que_codnum,enq_codnum,anyaca,cen_codnum,dur_codalf,ass_codnum,gas_codnum,prf_codalf,grp_desid1,dni,data,hora,aula,flg_recollit,flg_passat,flg_activat,nmat,ncontestats,flg_responsable,enviar_a,incidencies,becari,num_fulls,full_inici,full_fi,pla,credits_prf,alu_dnialu,numord,pla_codalf,mod_codnum,centre,durada,professor,departament,assignatura, s);					
										
					return gm1;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					GrupEnqAssPrf aux =(GrupEnqAssPrf) val;
					
					lgrups.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lgrups;
	}

	public List<GrupEnqGrp> getGrupEnqGrp(String dni, String anyAca,int model, final EinaEnqVirtualService s){
				
		final String pdni = dni;
		final String panyAca = anyAca;
		final int pmodel = model;		

		
		// Recuperem per parametre la consulta sql 	
		Placement p = ToolManager.getCurrentPlacement();
		ToolConfiguration toolConfig = SiteService.findTool(p.getId());	
		String sql = toolConfig.getPlacementConfig().getProperty("sql");

		List<GrupEnqGrp> lgrups = new ArrayList<GrupEnqGrp>();

		Object[] fields = new Object[3];
				 fields[0] = pdni;
				 fields[1] = panyAca;
				 fields[2] = pmodel;			
			
		List list = sqlService.dbRead(sql, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{		
										
					String ALU_DNIALU = result.getString(1);
					if (ALU_DNIALU == null) {ALU_DNIALU = "";}
					String PLA_CODALF = result.getString(2);
					if (PLA_CODALF == null) {PLA_CODALF = "";}
					int MOD_CODNUM = result.getInt(3);
					String ANYACA = result.getString(4);
					if (ANYACA == null) {ANYACA = "";}
					int CEN_CODNUM = result.getInt(5);
					int QUE_CODNUM = result.getInt(6);
					int ENQ_CODNUM = result.getInt(7);
					String INCIDENCIES = result.getString(8);
					if (INCIDENCIES == null) {INCIDENCIES = "";}
					String CENTRE = result.getString(9);
					if (CENTRE == null) {CENTRE = "";}					
					String NOMPLA = result.getString(10);
					if (NOMPLA == null) {NOMPLA = "";}
										
					GrupEnqGrp gm1 = new GrupEnqGrp (ALU_DNIALU,PLA_CODALF,MOD_CODNUM,ANYACA,CEN_CODNUM,QUE_CODNUM,ENQ_CODNUM,INCIDENCIES,CENTRE,NOMPLA,s);					
										
					return gm1;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					GrupEnqGrp aux =(GrupEnqGrp) val;
					
					lgrups.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lgrups;
	}
	
	public List<Integer> getGrupAssignatura(String dni, String anyAca, int cod_assig, int model, final EinaEnqVirtualService s){

		String sqlGrupsAssignatura = Property.sysProperties.getProperty("sqlGrupsAssignatura");
		
		final String pdni = dni;
		final String panyAca = anyAca;
		final int pmodel = model;
		final int pcod_assig = cod_assig;

		List<Integer> lgrups = new ArrayList<Integer>();

		Object[] fields = new Object[4];
				 fields[0] = pdni;
				 fields[1] = panyAca;
				 fields[2] = pmodel;
				 fields[3] = pcod_assig;
			

		List list = sqlService.dbRead(sqlGrupsAssignatura, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
														
					int enq_codnum = result.getInt(1);					
					Integer i = new Integer(enq_codnum);
					return i;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Integer aux = new Integer((Integer)val);
					
					lgrups.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	
		return lgrups;
	}

	
	
	public List<Alumne> getAlumnes(String anyAca, final EinaEnqVirtualService s){

		/******** Accedim a les propietats de configuració de l'eina ***/		
		Placement p = ToolManager.getCurrentPlacement();
		ToolConfiguration toolConfig = SiteService.findTool(p.getId());		
		/******** Recuperem el codi del model d'enquesta ****************/
		String model = toolConfig.getPlacementConfig().getProperty("codimodel");
		int codiMod = Integer.parseInt(model);
		
		String sql = "select ALU_DNIALU, CONV_CODNUM, ASS_CODNUM, PLA_CODALF, PRF_CODALF from VQUE_GRUPSM1M3M5M7 where ANYACA=? and MOD_CODNUM = ?";
		
		//String sql = Property.sysProperties.getProperty("sqlAlumnes");

		final String panyAca = anyAca;
		
		List<Alumne> lalumnes = new ArrayList<Alumne>();

		Object[] fields = new Object[2];		
				 fields[0] = panyAca;
				 fields[1] = codiMod;
		
				 System.out.println("SQL: "+sql + fields[0] + fields[1]); 
				
		List list = sqlService.dbRead(sql, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
					Alumne a = new Alumne();									
					a.setDni(result.getString(1));					
					a.setConvCodnum(result.getInt(2));
					a.setAssCodnum(result.getInt(3));
					a.setPlaCodalf(result.getString(4));
					a.setPrfCodalf(result.getString(5));					
														
					return a;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Alumne aux = (Alumne) val;
					
					
					lalumnes.add(aux);	
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	
		return lalumnes;
	}
	
	
	
	
	
	
	public List<Integer> getGrupAssignatura1(String dni, String anyAca, int cod_assig, int model, final EinaEnqVirtualService s){

		String sqlGrupsAssignatura1 = Property.sysProperties.getProperty("sqlGrupsAssignatura1");
		
		final String pdni = dni;
		final String panyAca = anyAca;
		final int pmodel = model;
		final int pcod_assig = cod_assig;

		List<Integer> lgrups = new ArrayList<Integer>();

		Object[] fields = new Object[4];
				 fields[0] = pdni;
				 fields[1] = panyAca;
				 fields[2] = pmodel;
				 fields[3] = pcod_assig;
			
		List list = sqlService.dbRead(sqlGrupsAssignatura1, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
														
					int que_codnum = result.getInt(1);					
					Integer i = new Integer(que_codnum);					
														
					return i;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Integer aux = new Integer((Integer)val);
					
					lgrups.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	
		return lgrups;
	}

	public Questionari getQuestionari(int codiQuesti){
		
		String sqlQuestionari = Property.sysProperties.getProperty("sqlQuestionari");
		
		final int pcodi = codiQuesti;
		Questionari questionari = new Questionari();
		Object[] fields = new Object[1];
		fields[0] = pcodi;		

		List list = sqlService.dbRead(sqlQuestionari, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{				
				try
				{					
					int codnum = result.getInt(1);										
					String desid1 = result.getString(2);
					if(desid1 == null) {desid1 = "";}
					String desid2 = result.getString(3);
					if(desid2 == null) {desid2 = "";}
					String desid3 = result.getString(4);
					if(desid3 == null) {desid3 = "";}
					int ordre = result.getInt(5);
					String visible = result.getString(6);
					if(visible == null) {visible = "";}					
					Questionari quest = new Questionari(codnum, desid1, desid2, desid3, ordre, visible);
					
					return quest;
				}
				catch (SQLException e)
				{					
					e.printStackTrace();
					return null;
				}
			}
		});
		if (list != null)
		{			
			Iterator iter = list.iterator();
			if (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					questionari = (Questionari) val;					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}							
		return questionari;		
	}
	
	
	public QuestionariEnqGrp getQuestionariEnqGrp(int codiQuesti){
		
		String sqlQuestionari = Property.sysProperties.getProperty("sqlQuestionari");
		
		final int pcodi = codiQuesti;
		QuestionariEnqGrp questionari = new QuestionariEnqGrp();
		Object[] fields = new Object[1];
		fields[0] = pcodi;		

		List list = sqlService.dbRead(sqlQuestionari, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{				
				try
				{					
					int codnum = result.getInt(1);										
					String desid1 = result.getString(2);
					if(desid1 == null) {desid1 = "";}
					String desid2 = result.getString(3);
					if(desid2 == null) {desid2 = "";}
					String desid3 = result.getString(4);
					if(desid3 == null) {desid3 = "";}
					int ordre = result.getInt(5);
					String visible = result.getString(6);
					if(visible == null) {visible = "";}					
					QuestionariEnqGrp quest = new QuestionariEnqGrp(codnum, desid1, desid2, desid3, ordre, visible);
					
					return quest;
				}
				catch (SQLException e)
				{					
					e.printStackTrace();
					return null;
				}
			}
		});
		if (list != null)
		{			
			Iterator iter = list.iterator();
			if (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					questionari = (QuestionariEnqGrp) val;					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}							
		return questionari;		
	}	

	
	
	
	
	public List<Bloc> getBloc(int que_codnum, final EinaEnqVirtualService s){

		String sqlBloc = Property.sysProperties.getProperty("sqlBloc");
				
		final int pque_codnum = que_codnum;		

		List<Bloc> lblocs = new ArrayList<Bloc>();

		Object[] fields = new Object[1];
				 fields[0] = pque_codnum;			

		List list = sqlService.dbRead(sqlBloc, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
					int que_codnum = result.getInt(2);									
					int codnum = result.getInt(1);
														
					String desid1 = result.getString(3);
					if (desid1 == null) {desid1 = "";}
					String desid2 = result.getString(4);
					if (desid2 == null) {desid2 = "";}
					String desid3 = result.getString(5);
					if (desid3 == null) {desid3 = "";}					
					String tipbloc = result.getString(6);
					if (tipbloc == null) {tipbloc = "";}
										
					Bloc blc = new Bloc(codnum,que_codnum,desid1,desid2,desid3,tipbloc,s);					

					return blc;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Bloc aux =(Bloc) val;
					
					lblocs.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lblocs;
	}
	
	
		
	
	public List<Bloc> getBlocTipus(String tipbloc, final EinaEnqVirtualService s){

		String sqlBlocTipus = Property.sysProperties.getProperty("sqlBlocTipus");
				
		final String ptipbloc = tipbloc;		

		List<Bloc> lblocs = new ArrayList<Bloc>();

		Object[] fields = new Object[1];
				 fields[0] = ptipbloc;						
			
		List list = sqlService.dbRead(sqlBlocTipus, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
					int que_codnum = result.getInt(2);									
					int codnum = result.getInt(1);
														
					String desid1 = result.getString(3);
					if (desid1 == null) {desid1 = "";}
					String desid2 = result.getString(4);
					if (desid2 == null) {desid2 = "";}
					String desid3 = result.getString(5);
					if (desid3 == null) {desid3 = "";}					
					String tipbloc = result.getString(6);
					if (tipbloc == null) {tipbloc = "";}
										
					Bloc blc = new Bloc(codnum,que_codnum,desid1,desid2,desid3,tipbloc,s);					

					return blc;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Bloc aux =(Bloc) val;
					
					lblocs.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lblocs;
	}
	
	public List<Bloc> getBlocDigitTipus(int que_codnum, String digit,String comentari, final EinaEnqVirtualService s){

		String sqlBlocDigitTipus = Property.sysProperties.getProperty("sqlBlocDigitTipus");
						
		final int pque_codnum = que_codnum;
		final String pdigit = digit;
		final String pcomentari = comentari;

		List<Bloc> lblocs = new ArrayList<Bloc>();

		Object[] fields = new Object[3];
				 fields[0] = pque_codnum;			
				 fields[1] = pdigit;
				 fields[2] = pcomentari;
			
		List list = sqlService.dbRead(sqlBlocDigitTipus, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
					int que_codnum = result.getInt(2);									
					int codnum = result.getInt(1);
														
					String desid1 = result.getString(3);
					if (desid1 == null) {desid1 = "";}
					String desid2 = result.getString(4);
					if (desid2 == null) {desid2 = "";}
					String desid3 = result.getString(5);
					if (desid3 == null) {desid3 = "";}					
					String tipbloc = result.getString(6);
					if (tipbloc == null) {tipbloc = "";}
										
					Bloc blc = new Bloc(codnum,que_codnum,desid1,desid2,desid3,tipbloc,s);					

					return blc;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Bloc aux =(Bloc) val;
					
					lblocs.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lblocs;
	}
	
	
	public List<BlocEnqGrp> getBlocEnqGrp(int que_codnum, final EinaEnqVirtualService s){

		String sqlBlocEnqGrp = Property.sysProperties.getProperty("sqlBlocEnqGrp");
						
		final int pque_codnum = que_codnum;		

		List<BlocEnqGrp> lblocs = new ArrayList<BlocEnqGrp>();

		Object[] fields = new Object[1];
				 fields[0] = pque_codnum;						

		List list = sqlService.dbRead(sqlBlocEnqGrp, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{									
					int que_codnum = result.getInt(2);									
					int codnum = result.getInt(1);
														
					String desid1 = result.getString(3);
					if (desid1 == null) {desid1 = "";}
					String desid2 = result.getString(4);
					if (desid2 == null) {desid2 = "";}
					String desid3 = result.getString(5);
					if (desid3 == null) {desid3 = "";}					
					String tipbloc = result.getString(6);
					if (tipbloc == null) {tipbloc = "";}
										
					BlocEnqGrp blc = new BlocEnqGrp(codnum,que_codnum,desid1,desid2,desid3,tipbloc,s);					

					return blc;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					BlocEnqGrp aux =(BlocEnqGrp) val;
					
					lblocs.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lblocs;
	}	


	public List<Pregunta> getPregunta(int que_codnum, int codnum, final EinaEnqVirtualService s){

		String sqlPregunta = Property.sysProperties.getProperty("sqlPregunta");				
	
		final int pbloc = codnum;
		final int pquestionari = que_codnum;
		

		List<Pregunta> lpreguntes = new ArrayList<Pregunta>();

		Object[] fields = new Object[2];
				 fields[0] = pquestionari;						
				 fields[1] = pbloc;

		List list = sqlService.dbRead(sqlPregunta, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{				
					int que_codnum = result.getInt(4);									
					int codnum = result.getInt(1);
					int tipprg_codnum = result.getInt(3);
					int ordre = result.getInt(6);
														
					String text = result.getString(2);
					if (text == null) {text = "";}
					String prg_vell = result.getString(5);
					if (prg_vell == null) {prg_vell = "";}									
					String text1 = new String("");					
					String resposta = new String();
					Pregunta prg;
										
					if (tipprg_codnum!=7)	//Si no es un comentari, es una opcio					
						prg = new PreguntaOp(que_codnum,codnum,text,ordre,tipprg_codnum,prg_vell,text1,s);
					else	// Es un comentari
						prg = new PreguntaTxt(que_codnum,codnum,text,0,tipprg_codnum,prg_vell,text1,resposta);											  
					
					return prg;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Pregunta aux =(Pregunta) val;
					
					lpreguntes.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lpreguntes;
	}
	
	
	public List<PreguntaEnqGrp> getPreguntaEnqGrp(int que_codnum, int codnum, final EinaEnqVirtualService s){

		String sqlPregunta = Property.sysProperties.getProperty("sqlPregunta");				
	
		final int pbloc = codnum;
		final int pquestionari = que_codnum;
		

		List<PreguntaEnqGrp> lpreguntes = new ArrayList<PreguntaEnqGrp>();

		Object[] fields = new Object[2];
		  		 fields[0] = pquestionari;						
		  		 fields[1] = pbloc;

		List list = sqlService.dbRead(sqlPregunta, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{				
					int que_codnum = result.getInt(4);									
					int codnum = result.getInt(1);
					int tipprg_codnum = result.getInt(3);
														
					String text = result.getString(2);
					if (text == null) {text = "";}
					String prg_vell = result.getString(5);
					if (prg_vell == null) {prg_vell = "";}									
					String text1 = new String("");					
					String resposta = new String();
					PreguntaEnqGrp prg;
										
					if (tipprg_codnum!=7)	//Si no es un comentari, es una opcio					
						prg = new PreguntaEnqGrpOp(que_codnum,codnum,text,tipprg_codnum,prg_vell,text1,s);
					else	// Es un comentari
						prg = new PreguntaEnqGrpTxt(que_codnum,codnum,text,tipprg_codnum,prg_vell,text1,resposta);											  
					
					return prg;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					PreguntaEnqGrp aux =(PreguntaEnqGrp) val;
					
					lpreguntes.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lpreguntes;
	}
	
	public List<Opcio> getOpcio(int tipprg_codnum){

		String sqlOpcio = Property.sysProperties.getProperty("sqlOpcio");
				
		final int ptipprg_codnum = tipprg_codnum;		

		List<Opcio> lopcions = new ArrayList<Opcio>();

		Object[] fields = new Object[1];
		 		 fields[0] = ptipprg_codnum;			

		List list = sqlService.dbRead(sqlOpcio, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{																						
					int codnum = result.getInt(1);
					int tipprg_codnum = result.getInt(2);
					int val_codnum = result.getInt(3);													
					String val_codalf = result.getString(4);
					if (val_codalf == null) {val_codalf = "";}					
										
					Opcio op = new Opcio(codnum,tipprg_codnum,val_codnum,val_codalf);					
					
					return op;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Opcio aux =(Opcio) val;
					
					lopcions.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lopcions;
	}
	
	
	public List<OpcioEnqGrp> getOpcioEnqGrp(int tipprg_codnum){

		String sqlOpcio = Property.sysProperties.getProperty("sqlOpcio");
				
		final int ptipprg_codnum = tipprg_codnum;		

		List<OpcioEnqGrp> lopcions = new ArrayList<OpcioEnqGrp>();

		Object[] fields = new Object[1];
				 fields[0] = ptipprg_codnum;			

		List list = sqlService.dbRead(sqlOpcio, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{																						
					int codnum = result.getInt(1);
					int tipprg_codnum = result.getInt(2);
					int val_codnum = result.getInt(3);													
					String val_codalf = result.getString(4);
					if (val_codalf == null) {val_codalf = "";}					
										
					OpcioEnqGrp op = new OpcioEnqGrp(codnum,tipprg_codnum,val_codnum,val_codalf);					
					
					return op;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					OpcioEnqGrp aux =(OpcioEnqGrp) val;
					
					lopcions.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lopcions;
	}	


	public List<Assignatura> getAssignatura (String dni, String anyAca, int model, final EinaEnqVirtualService s){

		String sqlAssignatures = Property.sysProperties.getProperty("sqlAssignatures");
		
		final String pdni = dni;
		final String panyAca = anyAca;
		final int pmodel = model;
		
		List<Assignatura> lassignatures = new ArrayList<Assignatura>();

		Object[] fields = new Object[3];
				 fields[0] = pdni;
				 fields[1] = panyAca;
				 fields[2] = pmodel;			
			
		List list = sqlService.dbRead(sqlAssignatures, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{
				try
				{																						
					int codnum = result.getInt(1);																	
					String assig = result.getString(2);
					if (assig == null) {assig = "";}					
										
					Assignatura ass = new Assignatura(codnum,assig,s,pdni,panyAca,pmodel);					
					
					return ass;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});

		if (list != null)
		{			
			Iterator iter = list.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Object val =  iter.next();
					Assignatura aux =(Assignatura) val;
					
					lassignatures.add(aux);					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	

		return lassignatures;
	}

	public String getContestat(List<Integer> enq_codnum, String dni){
		
		String sqlContestat = Property.sysProperties.getProperty("sqlContestat");
		
		final List<Integer> penq_codnum = enq_codnum;
		final String pdni = dni;
		
		String retorn = new String("N");		
				
		
		Object[] fields = new Object[2];
		fields[0] = penq_codnum.get(0);		
		fields[1] = pdni;

		List list = sqlService.dbRead(sqlContestat, fields, new SqlReader()
		{
			public Object readSqlResultRecord(ResultSet result)
			{				
					
					String re = new String();
					if (result!=null)
						re = "S";
					else
					{
						re = "N";
					}										
					return re;																							

			}
		});
		if (list.size() != 0)							
				retorn = "S";
		return retorn;
	}
	
public void setResposta(Integer enq_codnum,Integer prg_codnum,Integer res_codnum,String text_resp)
	{ 	
		String sqlInsertResposta = Property.sysProperties.getProperty("sqlInsertResposta");
	
		final Integer penq_codnum = new Integer(enq_codnum);
		final Integer pprg_codnum = new Integer(prg_codnum);
		final Integer pres_codnum = new Integer(res_codnum);
		final String pusuari = new String("");
		final String ptext_resp = text_resp;				
			
		Object[] fields = new Object[5];
		fields[0] = penq_codnum;
		fields[1] = pprg_codnum;
		fields[2] = pres_codnum;
		fields[3] = pusuari;
		fields[4] = ptext_resp;
	
		boolean ok = sqlService.dbWrite(sqlInsertResposta, fields);
	}


public void setTQue_contestat(Integer key_contestat,String dni)
{
	String sqlInsertContestat = Property.sysProperties.getProperty("sqlInsertContestat");
	
	final Integer pkey_contestat = new Integer(key_contestat);	
	final String pdni = dni;
	
	Object[] fields = new Object[2];
	fields[0] = pkey_contestat;
	fields[1] = pdni;
	
	boolean ok = sqlService.dbWrite(sqlInsertContestat, fields);
}

public void setNContestats(Integer key_contestat,String tipParam)
{
	String sqlUpdateNContestats1 = Property.sysProperties.getProperty("sqlUpdateNContestatsTipPa1");
	String sqlUpdateNContestats2 = Property.sysProperties.getProperty("sqlUpdateNContestatsTipPa1");
	String sqlUpdateNContestatsX = Property.sysProperties.getProperty("sqlUpdateNContestatsTipPaX");	
	
	final Integer pkey_contestat = new Integer(key_contestat);	
	
	Object[] fields = new Object[1];
			 fields[0] = pkey_contestat;
	boolean ok;
	
	if (tipParam.equals("TQUE_TIPPARAM1"))			
		ok = sqlService.dbWrite(sqlUpdateNContestats1, fields);	
	else if (tipParam.equals("TQUE_TIPPARAM2"))
		ok = sqlService.dbWrite(sqlUpdateNContestats2, fields);	
	else if (tipParam.equals("TQUE_TIPPARAMX"))
		ok = sqlService.dbWrite(sqlUpdateNContestatsX, fields);		
	}


public void EnviarMail(String email, String comentaris, String emailRemitent)
{
	// Recuperem per parametre el correu de desti 	
	Placement p = ToolManager.getCurrentPlacement();
	ToolConfiguration toolConfig = SiteService.findTool(p.getId());	
	String to = toolConfig.getPlacementConfig().getProperty("emaildesti");
	
	String from = emailRemitent;	//Remitent	- Correu Udl		
			
	String message_subject = "Avís: rebuda una incidència d'Enquestes Virtuals";
	String content = "Correu alternatiu: "+email+" \n\n" +
	"Comentaris:\n\n"+comentaris+"\n";
					
	if ( (email==null || email.equals("")) && (comentaris==null || comentaris.equals("")) )
	{
		//No emviem ja que no hi ha continguts
	}
	else
	{
		EmailService.send(from, to, message_subject, content, null, null, null);
	}
}	
	
public List<Convocatoria> getConvocatoria (String dni,int mod_codnum)
{
	String sqlConvocatories = Property.sysProperties.getProperty("sqlConvocatories");
	
	// Recuperem parametres 	
	Placement p = ToolManager.getCurrentPlacement();
	ToolConfiguration toolConfig = SiteService.findTool(p.getId());	
	String anyAca = toolConfig.getPlacementConfig().getProperty("any");
		
	final String pdni = dni;
	final String panyAca = anyAca;
	final int pmodel = mod_codnum;
	
	List<Convocatoria> lconvocatories = new ArrayList<Convocatoria>();

	Object[] fields = new Object[3];
			 fields[0] = pdni;
			 fields[1] = panyAca;
			 fields[2] = pmodel;			
		
	List list = sqlService.dbRead(sqlConvocatories, fields, new SqlReader()
	{
		public Object readSqlResultRecord(ResultSet result)
		{
			try
			{																						
				int codnum = result.getInt(1);																	
				String desid1 = result.getString(2);
				if (desid1 == null) {desid1 = "";}					
				String periode2_ini = result.getString(3);
				if (periode2_ini == null) {periode2_ini = "";}
				String periode2_fi = result.getString(4);
				if (periode2_fi == null) {periode2_fi = "";}
									
				Convocatoria conv = new Convocatoria(codnum,desid1,periode2_ini,periode2_fi);					
				
				return conv;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
		}
	});

	if (list != null)
	{			
		Iterator iter = list.iterator();
		while (iter.hasNext())
		{
			try
			{					
				Object val =  iter.next();
				Convocatoria aux =(Convocatoria) val;
				
				lconvocatories.add(aux);					
			}
			catch (Exception e)			
			{
				e.printStackTrace();
			}
		}
	}	

	return lconvocatories;
}


public String GetTipParam (int mod_codnum){
	
	String sqlTipParam = Property.sysProperties.getProperty("sqlTipParam");

	final int pcodnum = mod_codnum;			
			
	String tipP = new String("");	
	
	Object[] fields = new Object[1];
			 fields[0] = pcodnum;	

	List list = sqlService.dbRead(sqlTipParam, fields, new SqlReader()
	{
		public Object readSqlResultRecord(ResultSet result)
		{
			try
			{																						
																				
				String tip = result.getString(1);
				if (tip == null) {tip = "";}					
																	
				return tip;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
		}
	});
	if (list != null)
	{			
		Iterator iter = list.iterator();
		while (iter.hasNext())
		{
			try
			{					
				Object val =  iter.next();
				String aux =(String) val;
				
				tipP = aux;					
			}
			catch (Exception e)			
			{
				e.printStackTrace();
			}
		}
	}	
	

	return tipP;
	
}




}