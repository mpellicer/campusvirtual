package cat.udl.asic.einaenqvirtual.api;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public interface EinaEnqVirtualService{

		
	public ModelEnqAssPrf getModel(int codiMod, final String dni, final String anyaca, final EinaEnqVirtualService s);
	public List<Assignatura> getAssignatura (String dni, String anyAca, int model, final EinaEnqVirtualService s);	
	public List<GrupEnqAssPrf> getGrupEnqAssPrf(String dni, String anyAca, int cod_assig, int model, final EinaEnqVirtualService s);
	public List<Integer> getGrupAssignatura(String dni, String anyAca, int cod_assig, int model, final EinaEnqVirtualService s);
	public List<Alumne> getAlumnes(String anyAca, final EinaEnqVirtualService s);
	public List<Integer> getGrupAssignatura1(String dni, String anyAca, int cod_assig,int model, final EinaEnqVirtualService s);	
	public Questionari getQuestionari(int codiQuesti);
	public List<Bloc> getBloc(int que_codnum, EinaEnqVirtualService s);
	public List<Bloc> getBlocTipus(String tipbloc, final EinaEnqVirtualService s);
//	public List<Bloc> getBlocDigitTipus(int que_codnum, String digit, final EinaEnqVirtualService s);
	public List<Bloc> getBlocDigitTipus(int que_codnum, String digit,String comentari, final EinaEnqVirtualService s);	
//	public List<Bloc> getBloc(int que_codnum, final int questionari, final EinaEnqVirtualService s);
	public List<Pregunta> getPregunta(int que_codnum, int codnum, EinaEnqVirtualService s);
//	public List<Pregunta> getPregunta(int que_codnum, int codnum, int questionari, final EinaEnqVirtualService s);
	public List<Opcio> getOpcio(int tipprg_codnum);	
	public String getContestat(List<Integer> enq_codnum, String dni);
	public void setResposta(Integer enq_codnum,Integer prg_codnum,Integer res_codnum,String text_resp);
	public void setTQue_contestat(Integer key_contestat,String dni);		
	public void EnviarMail(String email, String comentaris, String emailRemitent);
	//public void setNContestats(Integer key_contestat,int codiMod);
	public void setNContestats(Integer key_contestat,String tipParam);	
	
	public ModelEnqGrp getModelEnqGrp(int codiMod, final String dni, final String anyaca, final EinaEnqVirtualService s);	
	public List<GrupEnqGrp> getGrupEnqGrp(String dni, String anyAca,int model, final EinaEnqVirtualService s);	
	public QuestionariEnqGrp getQuestionariEnqGrp(int codiQuesti);	
	public List<BlocEnqGrp> getBlocEnqGrp(int que_codnum, final EinaEnqVirtualService s);
	public List<PreguntaEnqGrp> getPreguntaEnqGrp(int que_codnum, int codnum, final EinaEnqVirtualService s);
	public List<OpcioEnqGrp> getOpcioEnqGrp(int tipprg_codnum);	
	public List<Convocatoria> getConvocatoria (String dni,int mod_codnum);
	
	public String GetTipParam (int mod_codnum);
	
}	
