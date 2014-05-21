package org.amc.model;

public interface MouldingProcess
{
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
		 "decompressionDist",
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
	public void setField(String field, Float f);

	public void setField(String field, java.sql.Date s);

	public void setField(String field, String s);

	public void setField(String field, Integer s);

	public void setField(String field, Object obj);

	public Object getField(String field);

	public String getPartId();

	public int getMachineSize();

	public String getMachineNo();

	public int getMaterial();

	public String getMasterbatchNo();

	public java.sql.Date getDateOfIssue();

	public String getSignOffBy();

	public String getProcessNotes();

	public float getInjectionSpeed_1();

	public float getInjectionSpeed_2();

	public float getInjectionSpeed_3();

	public float getInjectionSpeed_4();

	public float getInjectionSpeed_5();

	public float getInjectionSpeed_6();

	public float getInjSpeedPosition_1();

	public float getInjSpeedPosition_2();

	public float getInjSpeedPosition_3();

	public float getInjSpeedPosition_4();

	public float getInjSpeedPosition_5();

	public float getInjSpeedPosition_6();

	public float getHoldingPressure_1();

	public float getHoldingPressure_2();

	public float getHoldingPressure_3();

	public float getHoldingPressure_4();

	public float getHoldingPressure_5();

	public float getHoldingPressure_6();

	public float getHoldingTime_1();

	public float getHoldingTime_2();

	public float getHoldingTime_3();

	public float getHoldingTime_4();

	public float getHoldingTime_5();

	public float getHoldingTime_6();

	public float getMaxPackVel();

	public float getPosTran();

	public float getMaxInjPre();

	public float getMaxInjTime();

	public float getShotSize();

	public float getDecompressionVel();

	public float getCoolTime();

	public float getBackPressure_1();

	public float getBackPressure_2();

	public float getBackPressure_3();

	public float getBackPressure_4();

	public float getBackPressure_5();

	public float getBackPressure_6();

	public float getScrewExtSpeed_1();

	public float getScrewExtSpeed_2();

	public float getScrewExtSpeed_3();

	public float getScrewExtSpeed_4();

	public float getScrewExtSpeed_5();

	public float getScrewExtSpeed_6();

	public float getExtProfilePos_1();

	public float getExtProfilePos_2();

	public float getExtProfilePos_3();

	public float getExtProfilePos_4();

	public float getExtProfilePos_5();

	public float getExtProfilePos_6();

	public float getNozzelTemperature();

	public float getBarrelTemperature_1();

	public float getBarrelTemperature_2();

	public float getBarrelTemperature_3();

	public float getBarrelTemperature_4();

	public float getThroatTemperature();

	public float getMouldClosingOpenLimitPos();

	public float getMouldClosingOpenLimitSpeed();

	public float getMouldClosedLimitPos();

	public float getMouldClosedLimitSpeed();

	public float getClsSlowPos();

	public float getClsSlowSpeed();

	public float getMouldOpenBreakAwaySpeed();

	public float getMouldOpenStepPos_1();

	public float getMouldOpenStepSpeed_1();

	public float getMouldOpenStepPos_2();

	public float getMouldOpenStepSpeed_2();

	public float getMouldOpenStepPos_3();

	public float getMouldOpenStepSpeed_3();

	public float getMouldOpenTime();

	public String getEjectStart();

	public String getEjectMode();

	public float getEjectPulse();

	public float getEjectDelay();

	public float getEjectorsFwdPos();

	public float getEjectorsFwdSpeed();

	public float getEjectorsFwdTime();

	public float getEjectorsStopPos();

	public float getEjectorsStopSpeed();

	public float getEjectorsStopTime();

	public float getEjectorsRevPos();

	public float getEjectorsRevSpeed();

	public float getEjectorsRevTime();

	public float getDme_1();

	public float getDme_2();

	public float getDme_3();

	public float getDme_4();

	public float getDme_5();

	public float getDme_6();

	public float getDme_7();

	public float getDme_8();

	public float getWaterTempFixedHalf();

	public float getWaterTempMovingHalf();

	public String getWaterTempNotes();

	public void setPartId(String partId);

	public void setMachineSize(int machineSize);

	public void setMachineNo(String machineNo);

	public void setMaterial(int material);

	public void setMasterbatchNo(String masterbatchNo);

	public void setDateOfIssue(java.sql.Date dateOfIssue);

	public void setSignOffBy(String signOffBy);

	public void setProcessNotes(String processNotes);

	public void setInjectionSpeed_1(float injectionSpeed_1);

	public void setInjectionSpeed_2(float injectionSpeed_2);

	public void setInjectionSpeed_3(float injectionSpeed_3);

	public void setInjectionSpeed_4(float injectionSpeed_4);

	public void setInjectionSpeed_5(float injectionSpeed_5);

	public void setInjectionSpeed_6(float injectionSpeed_6);

	public void setInjSpeedPosition_1(float injSpeedPosition_1);

	public void setInjSpeedPosition_2(float injSpeedPosition_2);

	public void setInjSpeedPosition_3(float injSpeedPosition_3);

	public void setInjSpeedPosition_4(float injSpeedPosition_4);

	public void setInjSpeedPosition_5(float injSpeedPosition_5);

	public void setInjSpeedPosition_6(float injSpeedPosition_6);

	public void setHoldingPressure_1(float holdingPressure_1);

	public void setHoldingPressure_2(float holdingPressure_2);

	public void setHoldingPressure_3(float holdingPressure_3);

	public void setHoldingPressure_4(float holdingPressure_4);

	public void setHoldingPressure_5(float holdingPressure_5);

	public void setHoldingPressure_6(float holdingPressure_6);

	public void setHoldingTime_1(float holdingTime_1);

	public void setHoldingTime_2(float holdingTime_2);

	public void setHoldingTime_3(float holdingTime_3);

	public void setHoldingTime_4(float holdingTime_4);

	public void setHoldingTime_5(float holdingTime_5);

	public void setHoldingTime_6(float holdingTime_6);

	public void setMaxPackVel(float maxPackVel);

	public void setPosTran(float posTran);

	public void setMaxInjPre(float maxInjPre);

	public void setMaxInjTime(float maxInjTime);

	public void setShotSize(float shotSize);

	public void setDecompressionVel(float decompressionVel);

	public void setCoolTime(float coolTime);

	public void setBackPressure_1(float backPressure_1);

	public void setBackPressure_2(float backPressure_2);

	public void setBackPressure_3(float backPressure_3);

	public void setBackPressure_4(float backPressure_4);

	public void setBackPressure_5(float backPressure_5);

	public void setBackPressure_6(float backPressure_6);

	public void setScrewExtSpeed_1(float screwExtSpeed_1);

	public void setScrewExtSpeed_2(float screwExtSpeed_2);

	public void setScrewExtSpeed_3(float screwExtSpeed_3);

	public void setScrewExtSpeed_4(float screwExtSpeed_4);

	public void setScrewExtSpeed_5(float screwExtSpeed_5);

	public void setScrewExtSpeed_6(float screwExtSpeed_6);

	public void setExtProfilePos_1(float extProfilePos_1);

	public void setExtProfilePos_2(float extProfilePos_2);

	public void setExtProfilePos_3(float extProfilePos_3);

	public void setExtProfilePos_4(float extProfilePos_4);

	public void setExtProfilePos_5(float extProfilePos_5);

	public void setExtProfilePos_6(float extProfilePos_6);

	public void setNozzelTemperature(float nozzelTemperature);

	public void setBarrelTemperature_1(float barrelTemperature_1);

	public void setBarrelTemperature_2(float barrelTemperature_2);

	public void setBarrelTemperature_3(float barrelTemperature_3);

	public void setBarrelTemperature_4(float barrelTemperature_4);

	public void setThroatTemperature(float throatTemperature);

	public void setMouldClosingOpenLimitPos(float mouldClosingOpenLimitPos);

	public void setMouldClosingOpenLimitSpeed(float mouldClosingOpenLimitSpeed);

	public void setMouldClosedLimitPos(float mouldClosedLimitPos);

	public void setMouldClosedLimitSpeed(float mouldClosedLimitSpeed);

	public void setClsSlowPos(float clsSlowPos);

	public void setClsSlowSpeed(float clsSlowSpeed);

	public void setMouldOpenBreakAwaySpeed(float mouldOpenBreakAwaySpeed);

	public void setMouldOpenStepPos_1(float mouldOpenStepPos_1);

	public void setMouldOpenStepSpeed_1(float mouldOpenStepSpeed_1);

	public void setMouldOpenStepPos_2(float mouldOpenStepPos_2);

	public void setMouldOpenStepSpeed_2(float mouldOpenStepSpeed_2);

	public void setMouldOpenStepPos_3(float mouldOpenStepPos_3);

	public void setMouldOpenStepSpeed_3(float mouldOpenStepSpeed_3);

	public void setMouldOpenTime(float mouldOpenTime);

	public void setEjectStart(String ejectStart);

	public void setEjectMode(String ejectMode);

	public void setEjectPulse(float ejectPulse);

	public void setEjectDelay(float ejectDelay);

	public void setEjectorsFwdPos(float ejectorsFwdPos);

	public void setEjectorsFwdSpeed(float ejectorsFwdSpeed);

	public void setEjectorsFwdTime(float ejectorsFwdTime);

	public void setEjectorsStopPos(float ejectorsStopPos);

	public void setEjectorsStopSpeed(float ejectorsStopSpeed);

	public void setEjectorsStopTime(float ejectorsStopTime);

	public void setEjectorsRevPos(float ejectorsRevPos);

	public void setEjectorsRevSpeed(float ejectorsRevSpeed);

	public void setEjectorsRevTime(float ejectorsRevTime);

	public void setDme_1(float dme_1);

	public void setDme_2(float dme_2);

	public void setDme_3(float dme_3);

	public void setDme_4(float dme_4);

	public void setDme_5(float dme_5);

	public void setDme_6(float dme_6);

	public void setDme_7(float dme_7);

	public void setDme_8(float dme_8);

	public void setWaterTempFixedHalf(float waterTempFixedHalf);

	public void setWaterTempMovingHalf(float waterTempMovingHalf);

	public void setWaterTempNotes(String waterTempNotes);

	public float getClsSPPos();

	public float getClsSPSpeed();

	public void setClsSPPos(float clsSPPos);

	public void setClsSPSpeed(float clsSPSpeed);

	public int getId();

	public void setId(int id);

	public float getDecompressionDist();

	public void setDecompressionDist(float decompressionDist);
	
	

}