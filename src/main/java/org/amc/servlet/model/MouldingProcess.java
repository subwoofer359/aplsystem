package org.amc.servlet.model;

import java.util.HashMap;
import java.util.Map;

public class MouldingProcess 
{
	private Map<String,Object> store;
	public static String[] fields={
			 "id",	
			 "partId",
			 "machineSize",
			 "machineNo",
			 "material",
			 "masterbatchNo",
			 "dateOfIssue",
			 "signOffBy",
			 "processNotes",	
			 "injectionSpeed_1",
			 "injectionSpeed_2",
			 "injectionSpeed_3",
			 "injectionSpeed_4",
			 "injectionSpeed_5",
			 "injectionSpeed_6",
			 "injSpeedPosition_1",
			 "injSpeedPosition_2",
			 "injSpeedPosition_3",
			 "injSpeedPosition_4",
			 "injSpeedPosition_5",
			 "injSpeedPosition_6",
			 "holdingPressure_1",
			 "holdingPressure_2",
			 "holdingPressure_3",
			 "holdingPressure_4",
			 "holdingPressure_5",
			 "holdingPressure_6",
			 "holdingTime_1",
			 "holdingTime_2",
			 "holdingTime_3",
			 "holdingTime_4",
			 "holdingTime_5",
			 "holdingTime_6",
			 "maxPackVel",
			 "posTran",
			 "maxInjPre",
			 "maxInjTime",
			 "shotSize",
			 "decompressionVel",
			 "coolTime",
			 "backPressure_1",
			 "backPressure_2",
			 "backPressure_3",
			 "backPressure_4",
			 "backPressure_5",
			 "backPressure_6",
			 "screwExtSpeed_1",
			 "screwExtSpeed_2",
			 "screwExtSpeed_3",
			 "screwExtSpeed_4",
			 "screwExtSpeed_5",
			 "screwExtSpeed_6",	
			 "extProfilePos_1",
			 "extProfilePos_2",
			 "extProfilePos_3",
			 "extProfilePos_4",
			 "extProfilePos_5",
			 "extProfilePos_6",
			 "nozzelTemperature",
			 "barrelTemperature_1",
			 "barrelTemperature_2",
			 "barrelTemperature_3",
			 "barrelTemperature_4",
			 "throatTemperature",
			 "mouldClosingOpenLimitPos",
			 "mouldClosingOpenLimitSpeed",
			 "mouldClosedLimitPos",
			 "mouldClosedLimitSpeed",
			 "clsSlowPos",
			 "clsSlowSpeed",
			 "clsSPPos",
			 "clsSPSpeed",
			 "mouldOpenBreakAwaySpeed",
			 "mouldOpenStepPos_1",
			 "mouldOpenStepSpeed_1",
			 "mouldOpenStepPos_2",
			 "mouldOpenStepSpeed_2",
			 "mouldOpenStepPos_3",
			 "mouldOpenStepSpeed_3",
			 "mouldOpenTime",
			 "ejectStart",
			 "ejectMode",
			 "ejectPulse",
			 "ejectDelay",
			 "ejectorsFwdPos",
			 "ejectorsFwdSpeed",
			 "ejectorsFwdTime",
			 "ejectorsStopPos",
			 "ejectorsStopSpeed",
			 "ejectorsStopTime",
			 "ejectorsRevPos",
			 "ejectorsRevSpeed",
			 "ejectorsRevTime",	
			 "dme_1",
			 "dme_2",
			 "dme_3",
			 "dme_4",
			 "dme_5",
			 "dme_6",
			 "dme_7",
			 "dme_8",
			 "waterTempFixedHalf",
			 "waterTempMovingHalf",
			 "waterTempNotes",
	};
//	private int id;//Database ID
//	//Top Header
//	private String partId;
//	private int machineSize;
//	private String machineNo;
//	private String material;
//	private String masterbatchNo;
//	private Date dateOfIssue;
//	private String signOffBy;
//	private String processNotes;
//	
//	//Injection section
//	private float injectionSpeed_1;
//	private float injectionSpeed_2;
//	private float injectionSpeed_3;
//	private float injectionSpeed_4;
//	private float injectionSpeed_5;
//	private float injectionSpeed_6;
//	
//	private float injSpeedPosition_1;
//	private float injSpeedPosition_2;
//	private float injSpeedPosition_3;
//	private float injSpeedPosition_4;
//	private float injSpeedPosition_5;
//	private float injSpeedPosition_6;
//	
//	//Holding pressure
//	
//	private float holdingPressure_1;
//	private float holdingPressure_2;
//	private float holdingPressure_3;
//	private float holdingPressure_4;
//	private float holdingPressure_5;
//	private float holdingPressure_6;
//	
//	//Holding Time
//	private float holdingTime_1;
//	private float holdingTime_2;
//	private float holdingTime_3;
//	private float holdingTime_4;
//	private float holdingTime_5;
//	private float holdingTime_6;
//	
//	//Injection and Holding variables
//	private float maxPackVel;
//	private float posTran;
//	private float maxInjPre;
//	private float maxInjTime;
//	private float shotSize;
//	private float decompressionVel;
//	private float coolTime;
//	
//	//Extruding
//	
//	private float backPressure_1;
//	private float backPressure_2;
//	private float backPressure_3;
//	private float backPressure_4;
//	private float backPressure_5;
//	private float backPressure_6;
//	
//	private float screwExtSpeed_1;
//	private float screwExtSpeed_2;
//	private float screwExtSpeed_3;
//	private float screwExtSpeed_4;
//	private float screwExtSpeed_5;
//	private float screwExtSpeed_6;
//	
//	private float extProfilePos_1;
//	private float extProfilePos_2;
//	private float extProfilePos_3;
//	private float extProfilePos_4;
//	private float extProfilePos_5;
//	private float extProfilePos_6;
//	
//	
//	//Barrel Temperature
//	
//	private float nozzelTemperature;
//	private float barrelTemperature_1;
//	private float barrelTemperature_2;
//	private float barrelTemperature_3;
//	private float barrelTemperature_4;
//	private float throatTemperature;
//	
//	//mouldClosing
//	private float mouldClosingOpenLimitPos;
//	private float mouldClosingOpenLimitSpeed;
//	
//	private float mouldClosedLimitPos;
//	private float mouldClosedLimitSpeed;
//	
//	private float clsSlowPos;
//	private float clsSlowSpeed;
//	
//	private float clsSPPos;
//	private float clsSPSpeed;
//	//mould opening
//	
//	private float mouldOpenBreakAwaySpeed;
//	private float mouldOpenStepPos_1;
//	private float mouldOpenStepSpeed_1;
//	private float mouldOpenStepPos_2;
//	private float mouldOpenStepSpeed_2;
//	private float mouldOpenStepPos_3;
//	private float mouldOpenStepSpeed_3;
//	
//	private float mouldOpenTime;
//	private String ejectStart;
//	
//	
//	//Ejectors
//	private String ejectMode;
//	private float ejectPulse;
//	private float ejectDelay;
//	
//	private float ejectorsFwdPos;
//	private float ejectorsFwdSpeed;
//	private float ejectorsFwdTime;
//	
//	private float ejectorsStopPos;
//	private float ejectorsStopSpeed;
//	private float ejectorsStopTime;
//	
//	private float ejectorsRevPos;
//	private float ejectorsRevSpeed;
//	private float ejectorsRevTime;
//	
//	// DMEs
//	
//	private float dme_1;
//	private float dme_2;
//	private float dme_3;
//	private float dme_4;
//	private float dme_5;
//	private float dme_6;
//	private float dme_7;
//	private float dme_8;
//	
//	//Water
//	private float waterTempFixedHalf;
//	private float waterTempMovingHalf;
//	private String waterTempNotes;
	
	
	public MouldingProcess()
	{
		store=new HashMap<String,Object>();
		for(int i=9;i<MouldingProcess.fields.length;i++)
		{
			setField(fields[i], new Float(0f));
		}
	}
	
	//Getters/Setters
	
	public void setField(String field,Object value)
	{
		store.put(field, value);
	}
	
	public Object getField(String field)
	{
		return store.get(field);
	}
	
	public String getPartId() {
		return (String)store.get("partId");
	}
	public int getMachineSize() {
		return (Integer)store.get("machineSize");
	}
	public String getMachineNo() {
		return (String)store.get("machineNo");
	}
	public String getMaterial() {
		return (String)store.get("material");
	}
	public String getMasterbatchNo() {
		return (String)store.get("masterbatchNo");
	}
	public java.sql.Date getDateOfIssue() {
		return (java.sql.Date)store.get("dateOfIssue");
	}
	public String getSignOffBy() {
		return (String)store.get("signOffBy");
	}
	public String getProcessNotes() {
		return (String)store.get("processNotes");
	}
	public float getInjectionSpeed_1() {
		return (Float)store.get("injectionSpeed_1");
	}
	public float getInjectionSpeed_2() {
		return (Float)store.get("injectionSpeed_2");
	}
	public float getInjectionSpeed_3() {
		return (Float)store.get("injectionSpeed_3");
	}
	public float getInjectionSpeed_4() {
		return (Float)store.get("injectionSpeed_4");
	}
	public float getInjectionSpeed_5() {
		return (Float)store.get("injectionSpeed_5");
	}
	public float getInjectionSpeed_6() {
		return (Float)store.get("injectionSpeed_6");
	}
	public float getInjSpeedPosition_1() {
		return (Float)store.get("injSpeedPosition_1");
	}
	public float getInjSpeedPosition_2() {
		return (Float)store.get("injSpeedPosition_2");
	}
	public float getInjSpeedPosition_3() {
		return (Float)store.get("injSpeedPosition_3");
	}
	public float getInjSpeedPosition_4() {
		return (Float)store.get("injSpeedPosition_4");
	}
	public float getInjSpeedPosition_5() {
		return (Float)store.get("injSpeedPosition_5");
	}
	public float getInjSpeedPosition_6() {
		return (Float)store.get("injSpeedPosition_6");
	}
	public float getHoldingPressure_1() {
		return (Float)store.get("holdingPressure_1");
	}
	public float getHoldingPressure_2() {
		return (Float)store.get("holdingPressure_2");
	}
	public float getHoldingPressure_3() {
		return (Float)store.get("holdingPressure_3");
	}
	public float getHoldingPressure_4() {
		return (Float)store.get("holdingPressure_4");
	}
	public float getHoldingPressure_5() {
		return (Float)store.get("holdingPressure_5");
	}
	public float getHoldingPressure_6() {
		return (Float)store.get("holdingPressure_6");
	}
	public float getHoldingTime_1() {
		return (Float)store.get("holdingTime_1");
	}
	public float getHoldingTime_2() {
		return (Float)store.get("holdingTime_2");
	}
	public float getHoldingTime_3() {
		return (Float)store.get("holdingTime_3");
	}
	public float getHoldingTime_4() {
		return (Float)store.get("holdingTime_4");
	}
	public float getHoldingTime_5() {
		return (Float)store.get("holdingTime_5");
	}
	public float getHoldingTime_6() {
		return (Float)store.get("holdingTime_6");
	}
	public float getMaxPackVel() {
		return (Float)store.get("maxPackVel");
	}
	public float getPosTran() {
		return (Float)store.get("posTran");
	}
	public float getMaxInjPre() {
		return (Float)store.get("maxInjPre");
	}
	public float getMaxInjTime() {
		return (Float)store.get("maxInjTime");
	}
	public float getShotSize() {
		return (Float)store.get("shotSize");
	}
	public float getDecompressionVel() {
		return (Float)store.get("decompressionVel");
	}
	public float getCoolTime() {
		return (Float)store.get("coolTime");
	}
	public float getBackPressure_1() {
		return (Float)store.get("backPressure_1");
	}
	public float getBackPressure_2() {
		return (Float)store.get("backPressure_2");
	}
	public float getBackPressure_3() {
		return (Float)store.get("backPressure_3");
	}
	public float getBackPressure_4() {
		return (Float)store.get("backPressure_4");
	}
	public float getBackPressure_5() {
		return (Float)store.get("backPressure_5");
	}
	public float getBackPressure_6() {
		return (Float)store.get("backPressure_6");
	}
	public float getScrewExtSpeed_1() {
		return (Float)store.get("screwExtSpeed_1");
	}
	public float getScrewExtSpeed_2() {
		return (Float)store.get("screwExtSpeed_2");
	}
	public float getScrewExtSpeed_3() {
		return (Float)store.get("screwExtSpeed_3");
	}
	public float getScrewExtSpeed_4() {
		return (Float)store.get("screwExtSpeed_4");
	}
	public float getScrewExtSpeed_5() {
		return (Float)store.get("screwExtSpeed_5");
	}
	public float getScrewExtSpeed_6() {
		return (Float)store.get("screwExtSpeed_6");
	}
	public float getExtProfilePos_1() {
		return (Float)store.get("extProfilePos_1");
	}
	public float getExtProfilePos_2() {
		return (Float)store.get("extProfilePos_2");
	}
	public float getExtProfilePos_3() {
		return (Float)store.get("extProfilePos_3");
	}
	public float getExtProfilePos_4() {
		return (Float)store.get("extProfilePos_4");
	}
	public float getExtProfilePos_5() {
		return (Float)store.get("extProfilePos_5");
	}
	public float getExtProfilePos_6() {
		return (Float)store.get("extProfilePos_6");
	}
	public float getNozzelTemperature() {
		return (Float)store.get("nozzelTemperature");
	}
	public float getBarrelTemperature_1() {
		return (Float)store.get("barrelTemperature_1");
	}
	public float getBarrelTemperature_2() {
		return (Float)store.get("barrelTemperature_2");
	}
	public float getBarrelTemperature_3() {
		return (Float)store.get("barrelTemperature_3");
	}
	public float getBarrelTemperature_4() {
		return (Float)store.get("barrelTemperature_4");
	}
	public float getThroatTemperature() {
		return (Float)store.get("throatTemperature");
	}
	public float getMouldClosingOpenLimitPos() {
		return (Float)store.get("mouldClosingOpenLimitPos");
	}
	public float getMouldClosingOpenLimitSpeed() {
		return (Float)store.get("mouldClosingOpenLimitSpeed");
	}
	public float getMouldClosedLimitPos() {
		return (Float)store.get("mouldClosedLimitPos");
	}
	public float getMouldClosedLimitSpeed() {
		return (Float)store.get("mouldClosedLimitSpeed");
	}
	public float getClsSlowPos() {
		return (Float)store.get("clsSlowPos");
	}
	public float getClsSlowSpeed() {
		return (Float)store.get("clsSlowSpeed");
	}
	public float getMouldOpenBreakAwaySpeed() {
		return (Float)store.get("mouldOpenBreakAwaySpeed");
	}
	public float getMouldOpenStepPos_1() {
		return (Float)store.get("mouldOpenStepPos_1");
	}
	public float getMouldOpenStepSpeed_1() {
		return (Float)store.get("mouldOpenStepSpeed_1");
	}
	public float getMouldOpenStepPos_2() {
		return (Float)store.get("mouldOpenStepPos_2");
	}
	public float getMouldOpenStepSpeed_2() {
		return (Float)store.get("mouldOpenStepSpeed_2");
	}
	public float getMouldOpenStepPos_3() {
		return (Float)store.get("mouldOpenStepPos_3");
	}
	public float getMouldOpenStepSpeed_3() {
		return (Float)store.get("mouldOpenStepSpeed_3");
	}
	public float getMouldOpenTime() {
		return (Float)store.get("mouldOpenTime");
	}
	public String getEjectStart() {
		return (String)store.get("ejectStart");
	}
	public String getEjectMode() {
		return (String)store.get("ejectMode");
	}
	public float getEjectPulse() {
		return (Float)store.get("ejectPulse");
	}
	public float getEjectDelay() {
		return (Float)store.get("ejectDelay");
	}
	public float getEjectorsFwdPos() {
		return (Float)store.get("ejectorsFwdPos");
	}
	public float getEjectorsFwdSpeed() {
		return (Float)store.get("ejectorsFwdSpeed");
	}
	public float getEjectorsFwdTime() {
		return (Float)store.get("ejectorsFwdTime");
	}
	public float getEjectorsStopPos() {
		return (Float)store.get("ejectorsStopPos");
	}
	public float getEjectorsStopSpeed() {
		return (Float)store.get("ejectorsStopSpeed");
	}
	public float getEjectorsStopTime() {
		return (Float)store.get("ejectorsStopTime");
	}
	public float getEjectorsRevPos() {
		return (Float)store.get("ejectorsRevPos");
	}
	public float getEjectorsRevSpeed() {
		return (Float)store.get("ejectorsRevSpeed");
	}
	public float getEjectorsRevTime() {
		return (Float)store.get("ejectorsRevTime");
	}
	public float getDme_1() {
		return (Float)store.get("dme_1");
	}
	public float getDme_2() {
		return (Float)store.get("dme_2");
	}
	public float getDme_3() {
		return (Float)store.get("dme_3");
	}
	public float getDme_4() {
		return (Float)store.get("dme_4");
	}
	public float getDme_5() {
		return (Float)store.get("dme_5");
	}
	public float getDme_6() {
		return (Float)store.get("dme_6");
	}
	public float getDme_7() {
		return (Float)store.get("dme_7");
	}
	public float getDme_8() {
		return (Float)store.get("dme_8");
	}
	public float getWaterTempFixedHalf() {
		return (Float)store.get("waterTempFixedHalf");
	}
	public float getWaterTempMovingHalf() {
		return (Float)store.get("waterTempMovingHalf");
	}
	public String getWaterTempNotes() {
		return (String)store.get("waterTempNotes");
	}
	public void setPartId(String partId) {
		setField("partId",partId);
	}
	public void setMachineSize(int machineSize) {
		setField("machineSize",machineSize);
	}
	public void setMachineNo(String machineNo) {
		setField("machineNo",machineNo);
	}
	public void setMaterial(String material) {
		setField("material",material);
	}
	public void setMasterbatch(String masterbatchNo) {
		setField("masterbatchNo",masterbatchNo);
	}
	public void setDateOfIssue(java.util.Date dateOfIssue) {
		setField("dateOfIssue",dateOfIssue);
	}
	public void setSignOffBy(String signOffBy) {
		setField("signOffBy",signOffBy);
	}
	public void setProcessNotes(String processNotes) {
		setField("processNotes",processNotes);
	}
	public void setInjectionSpeed_1(float injectionSpeed_1) {
		setField("injectionSpeed_1",injectionSpeed_1);;
	}
	public void setInjectionSpeed_2(float injectionSpeed_2) {
		setField("injectionSpeed_2",injectionSpeed_2);
	}
	public void setInjectionSpeed_3(float injectionSpeed_3) {
		setField("injectionSpeed_3",injectionSpeed_3);
	}
	public void setInjectionSpeed_4(float injectionSpeed_4) {
		setField("injectionSpeed_4",injectionSpeed_4);
	}
	public void setInjectionSpeed_5(float injectionSpeed_5) {
		setField("injectionSpeed_5",injectionSpeed_5);
	}
	public void setInjectionSpeed_6(float injectionSpeed_6) {
		setField("injectionSpeed_6",injectionSpeed_6);
	}
	public void setInjSpeedPosition_1(float injSpeedPosition_1) {
		setField("injSpeedPosition_1",injSpeedPosition_1);
	}
	public void setInjSpeedPosition_2(float injSpeedPosition_2) {
		setField("injSpeedPosition_2",injSpeedPosition_2);
	}
	public void setInjSpeedPosition_3(float injSpeedPosition_3) {
		setField("injSpeedPosition_3",injSpeedPosition_3);
	}
	public void setInjSpeedPosition_4(float injSpeedPosition_4) {
		setField("injSpeedPosition_4",injSpeedPosition_4);
	}
	public void setInjSpeedPosition_5(float injSpeedPosition_5) {
		setField("injSpeedPosition_5",injSpeedPosition_5);
	}
	public void setInjSpeedPosition_6(float injSpeedPosition_6) {
		setField("injSpeedPosition_6",injSpeedPosition_6);
	}
	public void setHoldingPressure_1(float holdingPressure_1) {
		setField("holdingPressure_1",holdingPressure_1);
	}
	public void setHoldingPressure_2(float holdingPressure_2) {
		setField("holdingPressure_2",holdingPressure_2);
	}
	public void setHoldingPressure_3(float holdingPressure_3) {
		setField("holdingPressure_3",holdingPressure_3);
	}
	public void setHoldingPressure_4(float holdingPressure_4) {
		setField("holdingPressure_4",holdingPressure_4);
	}
	public void setHoldingPressure_5(float holdingPressure_5) {
		setField("holdingPressure_5",holdingPressure_5);
	}
	public void setHoldingPressure_6(float holdingPressure_6) {
		setField("holdingPressure_6",holdingPressure_6);
	}
	public void setHoldingTime_1(float holdingTime_1) {
		setField("holdingTime_1",holdingTime_1);
	}
	public void setHoldingTime_2(float holdingTime_2) {
		setField("holdingTime_2",holdingTime_2);
	}
	public void setHoldingTime_3(float holdingTime_3) {
		setField("holdingTime_3",holdingTime_3);
	}
	public void setHoldingTime_4(float holdingTime_4) {
		setField("holdingTime_4",holdingTime_4);
	}
	public void setHoldingTime_5(float holdingTime_5) {
		setField("holdingTime_5",holdingTime_5);
	}
	public void setHoldingTime_6(float holdingTime_6) {
		setField("holdingTime_6",holdingTime_6);
	}
	public void setMaxPackVel(float maxPackVel) {
		setField("maxPackVel",maxPackVel);
	}
	public void setPosTran(float posTran) {
		setField("posTran",posTran);
	}
	public void setMaxInjPre(float maxInjPre) {
		setField("maxInjPre",maxInjPre);
	}
	public void setMaxInjTime(float maxInjTime) {
		setField("maxInjTime",maxInjTime);
	}
	public void setShotSize(float shotSize) {
		setField("shotSize",shotSize);
	}
	public void setDecompressionVel(float decompressionVel) {
		setField("decompressionVel",decompressionVel);
	}
	public void setCoolTime(float coolTime) {
		setField("coolTime",coolTime);
	}
	public void setBackPressure_1(float backPressure_1) {
		setField("backPressure_1",backPressure_1);
	}
	public void setBackPressure_2(float backPressure_2) {
		setField("backPressure_2",backPressure_2);
	}
	public void setBackPressure_3(float backPressure_3) {
		setField("backPressure_3",backPressure_3);
	}
	public void setBackPressure_4(float backPressure_4) {
		setField("backPressure_4",backPressure_4);
	}
	public void setBackPressure_5(float backPressure_5) {
		setField("backPressure_5",backPressure_5);;
	}
	public void setBackPressure_6(float backPressure_6) {
		setField("backPressure_6",backPressure_6);;
	}
	public void setScrewExtSpeed_1(float screwExtSpeed_1) {
		setField("screwExtSpeed_1",screwExtSpeed_1);;
	}
	public void setScrewExtSpeed_2(float screwExtSpeed_2) {
		setField("screwExtSpeed_2",screwExtSpeed_2);;
	}
	public void setScrewExtSpeed_3(float screwExtSpeed_3) {
		setField("screwExtSpeed_3",screwExtSpeed_3);;
	}
	public void setScrewExtSpeed_4(float screwExtSpeed_4) {
		setField("screwExtSpeed_4",screwExtSpeed_4);;
	}
	public void setScrewExtSpeed_5(float screwExtSpeed_5) {
		setField("screwExtSpeed_5",screwExtSpeed_5);;
	}
	public void setScrewExtSpeed_6(float screwExtSpeed_6) {
		setField("screwExtSpeed_6",screwExtSpeed_6);;
	}
	public void setExtProfilePos_1(float extProfilePos_1) {
		setField("extProfilePos_1",extProfilePos_1);;
	}
	public void setExtProfilePos_2(float extProfilePos_2) {
		setField("extProfilePos_2",extProfilePos_2);;
	}
	public void setExtProfilePos_3(float extProfilePos_3) {
		setField("extProfilePos_3",extProfilePos_3);;
	}
	public void setExtProfilePos_4(float extProfilePos_4) {
		setField("extProfilePos_4",extProfilePos_4);;
	}
	public void setExtProfilePos_5(float extProfilePos_5) {
		setField("extProfilePos_5",extProfilePos_5);;
	}
	public void setExtProfilePos_6(float extProfilePos_6) {
		setField("extProfilePos_6",extProfilePos_6);;
	}
	public void setNozzelTemperature(float nozzelTemperature) {
		setField("nozzelTemperature",nozzelTemperature);;
	}
	public void setBarrelTemperature_1(float barrelTemperature_1) {
		setField("barrelTemperature_1",barrelTemperature_1);;
	}
	public void setBarrelTemperature_2(float barrelTemperature_2) {
		setField("barrelTemperature_2",barrelTemperature_2);;
	}
	public void setBarrelTemperature_3(float barrelTemperature_3) {
		setField("barrelTemperature_3",barrelTemperature_3);;
	}
	public void setBarrelTemperature_4(float barrelTemperature_4) {
		setField("barrelTemperature_4",barrelTemperature_4);;
	}
	public void setThroatTemperature(float throatTemperature) {
		setField("throatTemperature",throatTemperature);;
	}
	public void setMouldClosingOpenLimitPos(float mouldClosingOpenLimitPos) {
		setField("mouldClosingOpenLimitPos",mouldClosingOpenLimitPos);;
	}
	public void setMouldClosingOpenLimitSpeed(float mouldClosingOpenLimitSpeed) {
		setField("mouldClosingOpenLimitSpeed",mouldClosingOpenLimitSpeed);;
	}
	public void setMouldClosedLimitPos(float mouldClosedLimitPos) {
		setField("mouldClosedLimitPos",mouldClosedLimitPos);;
	}
	public void setMouldClosedLimitSpeed(float mouldClosedLimitSpeed) {
		setField("mouldClosedLimitSpeed",mouldClosedLimitSpeed);;
	}
	public void setClsSlowPos(float cLSSlowPos) {
		setField("clsSlowPos",cLSSlowPos);;
	}
	public void setClsSlowSpeed(float cLSSlowSpeed) {
		setField("clsSlowSpeed",cLSSlowSpeed);;
	}
	public void setMouldOpenBreakAwaySpeed(float mouldOpenBreakAwaySpeed) {
		setField("mouldOpenBreakAwaySpeed",mouldOpenBreakAwaySpeed);;
	}
	public void setMouldOpenStepPos_1(float mouldOpenStepPos_1) {
		setField("mouldOpenStepPos_1",mouldOpenStepPos_1);;
	}
	public void setMouldOpenStepSpeed_1(float mouldOpenStepSpeed_1) {
		setField("mouldOpenStepSpeed_1",mouldOpenStepSpeed_1);;
	}
	public void setMouldOpenStepPos_2(float mouldOpenStepPos_2) {
		setField("mouldOpenStepPos_2",mouldOpenStepPos_2);;
	}
	public void setMouldOpenStepSpeed_2(float mouldOpenStepSpeed_2) {
		setField("mouldOpenStepSpeed_2",mouldOpenStepSpeed_2);;
	}
	public void setMouldOpenStepPos_3(float mouldOpenStepPos_3) {
		setField("mouldOpenStepPos_3",mouldOpenStepPos_3);;
	}
	public void setMouldOpenStepSpeed_3(float mouldOpenStepSpeed_3) {
		setField("mouldOpenStepSpeed_3",mouldOpenStepSpeed_3);;
	}
	public void setMouldOpenTime(float mouldOpenTime) {
		setField("mouldOpenTime",mouldOpenTime);;
	}
	public void setEjectStart(String ejectStart) {
		setField("ejectStart",ejectStart);;
	}
	public void setEjectMode(String ejectMode) {
		setField("ejectMode",ejectMode);;
	}
	public void setEjectPulse(float ejectPulse) {
		setField("ejectPulse",ejectPulse);;
	}
	public void setEjectDelay(float ejectDelay) {
		setField("ejectDelay",ejectDelay);;
	}
	public void setEjectorsFwdPos(float ejectorsFwdPos) {
		setField("ejectorsFwdPos",ejectorsFwdPos);;
	}
	public void setEjectorsFwdSpeed(float ejectorsFwdSpeed) {
		setField("ejectorsFwdSpeed",ejectorsFwdSpeed);;
	}
	public void setEjectorsFwdTime(float ejectorsFwdTime) {
		setField("ejectorsFwdTime",ejectorsFwdTime);;
	}
	public void setEjectorsStopPos(float ejectorsStopPos) {
		setField("ejectorsStopPos",ejectorsStopPos);;
	}
	public void setEjectorsStopSpeed(float ejectorsStopSpeed) {
		setField("ejectorsStopSpeed",ejectorsStopSpeed);;
	}
	public void setEjectorsStopTime(float ejectorsStopTime) {
		setField("ejectorsStopTime",ejectorsStopTime);;
	}
	public void setEjectorsRevPos(float ejectorsRevPos) {
		setField("ejectorsRevPos",ejectorsRevPos);;
	}
	public void setEjectorsRevSpeed(float ejectorsRevSpeed) {
		setField("ejectorsRevSpeed",ejectorsRevSpeed);;
	}
	public void setEjectorsRevTime(float ejectorsRevTime) {
		setField("ejectorsRevTime",ejectorsRevTime);;
	}
	public void setDme_1(float dme_1) {
		setField("dme_1",dme_1);;
	}
	public void setDme_2(float dme_2) {
		setField("dme_2",dme_2);;
	}
	public void setDme_3(float dme_3) {
		setField("dme_3",dme_3);;
	}
	public void setDme_4(float dme_4) {
		setField("dme_4",dme_4);;
	}
	public void setDme_5(float dme_5) {
		setField("dme_5",dme_5);;
	}
	public void setDme_6(float dme_6) {
		setField("dme_6",dme_6);;
	}
	public void setDme_7(float dme_7) {
		setField("dme_7",dme_7);;
	}
	public void setDme_8(float dme_8) {
		setField("dme_8",dme_8);;
	}
	public void setWaterTempFixedHalf(float waterTempFixedHalf) {
		setField("waterTempFixedHalf",waterTempFixedHalf);;
	}
	public void setWaterTempMovingHalf(float waterTempMovingHalf) {
		setField("waterTempMovingHalf",waterTempMovingHalf);;
	}
	public void setWaterTempNotes(String waterTempNotes) {
		setField("waterTempNotes",waterTempNotes);;
	}

	public float getClsSPPos() {
		return (Float)store.get("clsSPPos");
	}

	public float getClsSPSpeed() {
		return (Float)store.get("clsSPSpeed");
	}

	public void setClsSPPos(float cLSSPPos) {
		setField("clsSPPos",cLSSPPos);;
	}

	public void setClsSPSpeed(float cLSSPSpeed) {
		setField("clsSPSpeed",cLSSPSpeed);;
	}
	public int getId() {
		return (Integer)store.get("id");
	}

	public void setId(int id) {
		setField("id",id);
	}
	
	@Override
	public String toString()
	{
		String result="ProcessSheet:(";
		for(String field:fields)
		{
			result+=","+field+":"+getField(field);
		}
		result+=")";
		return result;
	}
	
	public static void main(String[] args)
	{
		MouldingProcess m=new MouldingProcess();
		System.out.println(m.getWaterTempNotes());
		System.out.println(m);
	}
}
