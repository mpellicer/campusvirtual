package cat.udl.asic.app.einaenqvirtual;

import java.util.HashMap;

public class GuardarBean {
	HashMap respostes = new HashMap();
	HashMap enq_codnum = new HashMap();
	HashMap prg_codnum = new HashMap();
	HashMap hmTipus_prg = new HashMap();		
	
	public void setRespostes(HashMap mp)
	{
		respostes = mp;
	}
	
	public HashMap getRespostes()
	{
		return respostes;
	}
	public void setEnq_codnum(HashMap mp)
	{
		enq_codnum = mp;
	}
	
	public HashMap getEnq_codnum()
	{
		return enq_codnum;
	}
	public void setPrg_codnum(HashMap mp)
	{
		prg_codnum = mp;
	}
	
	public HashMap getPrg_codnum()
	{
		return prg_codnum;
	}
	public void setTipus_prg(HashMap mp)
	{
		hmTipus_prg = mp;
	}
	
	public HashMap getTipus_prg()
	{
		return hmTipus_prg;
	}
	
}