package org.amc.servlet.model;

public class MouldingProcess 
{
	//Top Header
	private float partId;
	private float machineSize;
	private float machineNo;
	private float material;
	private float masterbatchNo;
	private float dateOfIssue;
	private float signOffBy;
	private float ProcessNotes;
	
	//Injection section
	private float injectionSpeed_1;
	private float injectionSpeed_2;
	private float injectionSpeed_3;
	private float injectionSpeed_4;
	private float injectionSpeed_5;
	private float injectionSpeed_6;
	
	private float injSpeedPosition_1;
	private float injSpeedPosition_2;
	private float injSpeedPosition_3;
	private float injSpeedPosition_4;
	private float injSpeedPosition_5;
	private float injSpeedPosition_6;
	
	//Holding pressure
	
	private float holdingPressure_1;
	private float holdingPressure_2;
	private float holdingPressure_3;
	private float holdingPressure_4;
	private float holdingPressure_5;
	private float holdingPressure_6;
	
	//Holding Time
	private float holdingTime_1;
	private float holdingTime_2;
	private float holdingTime_3;
	private float holdingTime_4;
	private float holdingTime_5;
	private float holdingTime_6;
	
	//Injection and Holding variables
	private float maxPackVel;
	private float posTran;
	private float maxInjPre;
	private float maxInjTime;
	private float shotSize;
	private float DecompressionVel;
	private float coolTime;
	
	//Extruding
	
	private float backPressure_1;
	private float backPressure_2;
	private float backPressure_3;
	private float backPressure_4;
	private float backPressure_5;
	private float backPressure_6;
	
	private float screwExtSpeed_1;
	private float screwExtSpeed_2;
	private float screwExtSpeed_3;
	private float screwExtSpeed_4;
	private float screwExtSpeed_5;
	private float screwExtSpeed_6;
	
	private float extProfilePos_1;
	private float extProfilePos_2;
	private float extProfilePos_3;
	private float extProfilePos_4;
	private float extProfilePos_5;
	private float extProfilePos_6;
	
	
	//Barrel Temperature
	
	private float nozzelTemperature;
	private float barrelTemperature_1;
	private float barrelTemperature_2;
	private float barrelTemperature_3;
	private float barrelTemperature_4;
	private float throatTemperature;
	
	//mouldOpening
	private float mouldClosingOpenLimitPos;
	private float mouldClosingOpenLimitSpeed;
	
	private float mouldClosedLimitPos;
	private float mouldClosedLimitSpeed;
	
	private float CLSSlowPos;
	private float CLSSlowSpeed;
	
	//mould closing
	
	private float mouldOpenBreakAwaySpeed;
	private float mouldOpenStepPos_1;
	private float mouldOpenStepSpeed_1;
	private float mouldOpenStepPos_2;
	private float mouldOpenStepSpeed_2;
	private float mouldOpenStepPos_3;
	private float mouldOpenStepSpeed_3;
	
	private float mouldOpenTime;
	private String ejectStart;
	
	
	//Ejectors
	private String ejectMode;
	private float ejectPulse;
	private float ejectDelay;
	
	private float ejectorsFwdPos;
	private float ejectorsFwdSpeed;
	private float ejectorsFwdTime;
	
	private float ejectorsStopPos;
	private float ejectorsStopSpeed;
	private float ejectorsStopTime;
	
	private float ejectorsRevPos;
	private float ejectorsRevSpeed;
	private float ejectorsRevTime;
	
	// DMEs
	
	private float dme_1;
	private float dme_2;
	private float dme_3;
	private float dme_4;
	private float dme_5;
	private float dme_6;
	private float dme_7;
	private float dme_8;
	
	//Water
	private float waterTempFixedHalf;
	private float waterTempMovingHalf;
	private String waterTempNotes;
	
	
	public MouldingProcess()
	{
		
	}
	
	//Getters/Setters
	
	public float getPartId() {
		return partId;
	}
	public float getMachineSize() {
		return machineSize;
	}
	public float getMachineNo() {
		return machineNo;
	}
	public float getMaterial() {
		return material;
	}
	public float getMasterbatchNo() {
		return masterbatchNo;
	}
	public float getDateOfIssue() {
		return dateOfIssue;
	}
	public float getSignOffBy() {
		return signOffBy;
	}
	public float getProcessNotes() {
		return ProcessNotes;
	}
	public float getInjectionSpeed_1() {
		return injectionSpeed_1;
	}
	public float getInjectionSpeed_2() {
		return injectionSpeed_2;
	}
	public float getInjectionSpeed_3() {
		return injectionSpeed_3;
	}
	public float getInjectionSpeed_4() {
		return injectionSpeed_4;
	}
	public float getInjectionSpeed_5() {
		return injectionSpeed_5;
	}
	public float getInjectionSpeed_6() {
		return injectionSpeed_6;
	}
	public float getInjSpeedPosition_1() {
		return injSpeedPosition_1;
	}
	public float getInjSpeedPosition_2() {
		return injSpeedPosition_2;
	}
	public float getInjSpeedPosition_3() {
		return injSpeedPosition_3;
	}
	public float getInjSpeedPosition_4() {
		return injSpeedPosition_4;
	}
	public float getInjSpeedPosition_5() {
		return injSpeedPosition_5;
	}
	public float getInjSpeedPosition_6() {
		return injSpeedPosition_6;
	}
	public float getHoldingPressure_1() {
		return holdingPressure_1;
	}
	public float getHoldingPressure_2() {
		return holdingPressure_2;
	}
	public float getHoldingPressure_3() {
		return holdingPressure_3;
	}
	public float getHoldingPressure_4() {
		return holdingPressure_4;
	}
	public float getHoldingPressure_5() {
		return holdingPressure_5;
	}
	public float getHoldingPressure_6() {
		return holdingPressure_6;
	}
	public float getHoldingTime_1() {
		return holdingTime_1;
	}
	public float getHoldingTime_2() {
		return holdingTime_2;
	}
	public float getHoldingTime_3() {
		return holdingTime_3;
	}
	public float getHoldingTime_4() {
		return holdingTime_4;
	}
	public float getHoldingTime_5() {
		return holdingTime_5;
	}
	public float getHoldingTime_6() {
		return holdingTime_6;
	}
	public float getMaxPackVel() {
		return maxPackVel;
	}
	public float getPosTran() {
		return posTran;
	}
	public float getMaxInjPre() {
		return maxInjPre;
	}
	public float getMaxInjTime() {
		return maxInjTime;
	}
	public float getShotSize() {
		return shotSize;
	}
	public float getDecompressionVel() {
		return DecompressionVel;
	}
	public float getCoolTime() {
		return coolTime;
	}
	public float getBackPressure_1() {
		return backPressure_1;
	}
	public float getBackPressure_2() {
		return backPressure_2;
	}
	public float getBackPressure_3() {
		return backPressure_3;
	}
	public float getBackPressure_4() {
		return backPressure_4;
	}
	public float getBackPressure_5() {
		return backPressure_5;
	}
	public float getBackPressure_6() {
		return backPressure_6;
	}
	public float getScrewExtSpeed_1() {
		return screwExtSpeed_1;
	}
	public float getScrewExtSpeed_2() {
		return screwExtSpeed_2;
	}
	public float getScrewExtSpeed_3() {
		return screwExtSpeed_3;
	}
	public float getScrewExtSpeed_4() {
		return screwExtSpeed_4;
	}
	public float getScrewExtSpeed_5() {
		return screwExtSpeed_5;
	}
	public float getScrewExtSpeed_6() {
		return screwExtSpeed_6;
	}
	public float getExtProfilePos_1() {
		return extProfilePos_1;
	}
	public float getExtProfilePos_2() {
		return extProfilePos_2;
	}
	public float getExtProfilePos_3() {
		return extProfilePos_3;
	}
	public float getExtProfilePos_4() {
		return extProfilePos_4;
	}
	public float getExtProfilePos_5() {
		return extProfilePos_5;
	}
	public float getExtProfilePos_6() {
		return extProfilePos_6;
	}
	public float getNozzelTemperature() {
		return nozzelTemperature;
	}
	public float getBarrelTemperature_1() {
		return barrelTemperature_1;
	}
	public float getBarrelTemperature_2() {
		return barrelTemperature_2;
	}
	public float getBarrelTemperature_3() {
		return barrelTemperature_3;
	}
	public float getBarrelTemperature_4() {
		return barrelTemperature_4;
	}
	public float getThroatTemperature() {
		return throatTemperature;
	}
	public float getMouldClosingOpenLimitPos() {
		return mouldClosingOpenLimitPos;
	}
	public float getMouldClosingOpenLimitSpeed() {
		return mouldClosingOpenLimitSpeed;
	}
	public float getMouldClosedLimitPos() {
		return mouldClosedLimitPos;
	}
	public float getMouldClosedLimitSpeed() {
		return mouldClosedLimitSpeed;
	}
	public float getCLSSlowPos() {
		return CLSSlowPos;
	}
	public float getCLSSlowSpeed() {
		return CLSSlowSpeed;
	}
	public float getMouldOpenBreakAwaySpeed() {
		return mouldOpenBreakAwaySpeed;
	}
	public float getMouldOpenStepPos_1() {
		return mouldOpenStepPos_1;
	}
	public float getMouldOpenStepSpeed_1() {
		return mouldOpenStepSpeed_1;
	}
	public float getMouldOpenStepPos_2() {
		return mouldOpenStepPos_2;
	}
	public float getMouldOpenStepSpeed_2() {
		return mouldOpenStepSpeed_2;
	}
	public float getMouldOpenStepPos_3() {
		return mouldOpenStepPos_3;
	}
	public float getMouldOpenStepSpeed_3() {
		return mouldOpenStepSpeed_3;
	}
	public float getMouldOpenTime() {
		return mouldOpenTime;
	}
	public String getEjectStart() {
		return ejectStart;
	}
	public String getEjectMode() {
		return ejectMode;
	}
	public float getEjectPulse() {
		return ejectPulse;
	}
	public float getEjectDelay() {
		return ejectDelay;
	}
	public float getEjectorsFwdPos() {
		return ejectorsFwdPos;
	}
	public float getEjectorsFwdSpeed() {
		return ejectorsFwdSpeed;
	}
	public float getEjectorsFwdTime() {
		return ejectorsFwdTime;
	}
	public float getEjectorsStopPos() {
		return ejectorsStopPos;
	}
	public float getEjectorsStopSpeed() {
		return ejectorsStopSpeed;
	}
	public float getEjectorsStopTime() {
		return ejectorsStopTime;
	}
	public float getEjectorsRevPos() {
		return ejectorsRevPos;
	}
	public float getEjectorsRevSpeed() {
		return ejectorsRevSpeed;
	}
	public float getEjectorsRevTime() {
		return ejectorsRevTime;
	}
	public float getDme_1() {
		return dme_1;
	}
	public float getDme_2() {
		return dme_2;
	}
	public float getDme_3() {
		return dme_3;
	}
	public float getDme_4() {
		return dme_4;
	}
	public float getDme_5() {
		return dme_5;
	}
	public float getDme_6() {
		return dme_6;
	}
	public float getDme_7() {
		return dme_7;
	}
	public float getDme_8() {
		return dme_8;
	}
	public float getWaterTempFixedHalf() {
		return waterTempFixedHalf;
	}
	public float getWaterTempMovingHalf() {
		return waterTempMovingHalf;
	}
	public String getWaterTempNotes() {
		return waterTempNotes;
	}
	public void setPartId(float partId) {
		this.partId = partId;
	}
	public void setMachineSize(float machineSize) {
		this.machineSize = machineSize;
	}
	public void setMachineNo(float machineNo) {
		this.machineNo = machineNo;
	}
	public void setMaterial(float material) {
		this.material = material;
	}
	public void setMasterbatchNo(float masterbatchNo) {
		this.masterbatchNo = masterbatchNo;
	}
	public void setDateOfIssue(float dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public void setSignOffBy(float signOffBy) {
		this.signOffBy = signOffBy;
	}
	public void setProcessNotes(float processNotes) {
		ProcessNotes = processNotes;
	}
	public void setInjectionSpeed_1(float injectionSpeed_1) {
		this.injectionSpeed_1 = injectionSpeed_1;
	}
	public void setInjectionSpeed_2(float injectionSpeed_2) {
		this.injectionSpeed_2 = injectionSpeed_2;
	}
	public void setInjectionSpeed_3(float injectionSpeed_3) {
		this.injectionSpeed_3 = injectionSpeed_3;
	}
	public void setInjectionSpeed_4(float injectionSpeed_4) {
		this.injectionSpeed_4 = injectionSpeed_4;
	}
	public void setInjectionSpeed_5(float injectionSpeed_5) {
		this.injectionSpeed_5 = injectionSpeed_5;
	}
	public void setInjectionSpeed_6(float injectionSpeed_6) {
		this.injectionSpeed_6 = injectionSpeed_6;
	}
	public void setInjSpeedPosition_1(float injSpeedPosition_1) {
		this.injSpeedPosition_1 = injSpeedPosition_1;
	}
	public void setInjSpeedPosition_2(float injSpeedPosition_2) {
		this.injSpeedPosition_2 = injSpeedPosition_2;
	}
	public void setInjSpeedPosition_3(float injSpeedPosition_3) {
		this.injSpeedPosition_3 = injSpeedPosition_3;
	}
	public void setInjSpeedPosition_4(float injSpeedPosition_4) {
		this.injSpeedPosition_4 = injSpeedPosition_4;
	}
	public void setInjSpeedPosition_5(float injSpeedPosition_5) {
		this.injSpeedPosition_5 = injSpeedPosition_5;
	}
	public void setInjSpeedPosition_6(float injSpeedPosition_6) {
		this.injSpeedPosition_6 = injSpeedPosition_6;
	}
	public void setHoldingPressure_1(float holdingPressure_1) {
		this.holdingPressure_1 = holdingPressure_1;
	}
	public void setHoldingPressure_2(float holdingPressure_2) {
		this.holdingPressure_2 = holdingPressure_2;
	}
	public void setHoldingPressure_3(float holdingPressure_3) {
		this.holdingPressure_3 = holdingPressure_3;
	}
	public void setHoldingPressure_4(float holdingPressure_4) {
		this.holdingPressure_4 = holdingPressure_4;
	}
	public void setHoldingPressure_5(float holdingPressure_5) {
		this.holdingPressure_5 = holdingPressure_5;
	}
	public void setHoldingPressure_6(float holdingPressure_6) {
		this.holdingPressure_6 = holdingPressure_6;
	}
	public void setHoldingTime_1(float holdingTime_1) {
		this.holdingTime_1 = holdingTime_1;
	}
	public void setHoldingTime_2(float holdingTime_2) {
		this.holdingTime_2 = holdingTime_2;
	}
	public void setHoldingTime_3(float holdingTime_3) {
		this.holdingTime_3 = holdingTime_3;
	}
	public void setHoldingTime_4(float holdingTime_4) {
		this.holdingTime_4 = holdingTime_4;
	}
	public void setHoldingTime_5(float holdingTime_5) {
		this.holdingTime_5 = holdingTime_5;
	}
	public void setHoldingTime_6(float holdingTime_6) {
		this.holdingTime_6 = holdingTime_6;
	}
	public void setMaxPackVel(float maxPackVel) {
		this.maxPackVel = maxPackVel;
	}
	public void setPosTran(float posTran) {
		this.posTran = posTran;
	}
	public void setMaxInjPre(float maxInjPre) {
		this.maxInjPre = maxInjPre;
	}
	public void setMaxInjTime(float maxInjTime) {
		this.maxInjTime = maxInjTime;
	}
	public void setShotSize(float shotSize) {
		this.shotSize = shotSize;
	}
	public void setDecompressionVel(float decompressionVel) {
		DecompressionVel = decompressionVel;
	}
	public void setCoolTime(float coolTime) {
		this.coolTime = coolTime;
	}
	public void setBackPressure_1(float backPressure_1) {
		this.backPressure_1 = backPressure_1;
	}
	public void setBackPressure_2(float backPressure_2) {
		this.backPressure_2 = backPressure_2;
	}
	public void setBackPressure_3(float backPressure_3) {
		this.backPressure_3 = backPressure_3;
	}
	public void setBackPressure_4(float backPressure_4) {
		this.backPressure_4 = backPressure_4;
	}
	public void setBackPressure_5(float backPressure_5) {
		this.backPressure_5 = backPressure_5;
	}
	public void setBackPressure_6(float backPressure_6) {
		this.backPressure_6 = backPressure_6;
	}
	public void setScrewExtSpeed_1(float screwExtSpeed_1) {
		this.screwExtSpeed_1 = screwExtSpeed_1;
	}
	public void setScrewExtSpeed_2(float screwExtSpeed_2) {
		this.screwExtSpeed_2 = screwExtSpeed_2;
	}
	public void setScrewExtSpeed_3(float screwExtSpeed_3) {
		this.screwExtSpeed_3 = screwExtSpeed_3;
	}
	public void setScrewExtSpeed_4(float screwExtSpeed_4) {
		this.screwExtSpeed_4 = screwExtSpeed_4;
	}
	public void setScrewExtSpeed_5(float screwExtSpeed_5) {
		this.screwExtSpeed_5 = screwExtSpeed_5;
	}
	public void setScrewExtSpeed_6(float screwExtSpeed_6) {
		this.screwExtSpeed_6 = screwExtSpeed_6;
	}
	public void setExtProfilePos_1(float extProfilePos_1) {
		this.extProfilePos_1 = extProfilePos_1;
	}
	public void setExtProfilePos_2(float extProfilePos_2) {
		this.extProfilePos_2 = extProfilePos_2;
	}
	public void setExtProfilePos_3(float extProfilePos_3) {
		this.extProfilePos_3 = extProfilePos_3;
	}
	public void setExtProfilePos_4(float extProfilePos_4) {
		this.extProfilePos_4 = extProfilePos_4;
	}
	public void setExtProfilePos_5(float extProfilePos_5) {
		this.extProfilePos_5 = extProfilePos_5;
	}
	public void setExtProfilePos_6(float extProfilePos_6) {
		this.extProfilePos_6 = extProfilePos_6;
	}
	public void setNozzelTemperature(float nozzelTemperature) {
		this.nozzelTemperature = nozzelTemperature;
	}
	public void setBarrelTemperature_1(float barrelTemperature_1) {
		this.barrelTemperature_1 = barrelTemperature_1;
	}
	public void setBarrelTemperature_2(float barrelTemperature_2) {
		this.barrelTemperature_2 = barrelTemperature_2;
	}
	public void setBarrelTemperature_3(float barrelTemperature_3) {
		this.barrelTemperature_3 = barrelTemperature_3;
	}
	public void setBarrelTemperature_4(float barrelTemperature_4) {
		this.barrelTemperature_4 = barrelTemperature_4;
	}
	public void setThroatTemperature(float throatTemperature) {
		this.throatTemperature = throatTemperature;
	}
	public void setMouldClosingOpenLimitPos(float mouldClosingOpenLimitPos) {
		this.mouldClosingOpenLimitPos = mouldClosingOpenLimitPos;
	}
	public void setMouldClosingOpenLimitSpeed(float mouldClosingOpenLimitSpeed) {
		this.mouldClosingOpenLimitSpeed = mouldClosingOpenLimitSpeed;
	}
	public void setMouldClosedLimitPos(float mouldClosedLimitPos) {
		this.mouldClosedLimitPos = mouldClosedLimitPos;
	}
	public void setMouldClosedLimitSpeed(float mouldClosedLimitSpeed) {
		this.mouldClosedLimitSpeed = mouldClosedLimitSpeed;
	}
	public void setCLSSlowPos(float cLSSlowPos) {
		CLSSlowPos = cLSSlowPos;
	}
	public void setCLSSlowSpeed(float cLSSlowSpeed) {
		CLSSlowSpeed = cLSSlowSpeed;
	}
	public void setMouldOpenBreakAwaySpeed(float mouldOpenBreakAwaySpeed) {
		this.mouldOpenBreakAwaySpeed = mouldOpenBreakAwaySpeed;
	}
	public void setMouldOpenStepPos_1(float mouldOpenStepPos_1) {
		this.mouldOpenStepPos_1 = mouldOpenStepPos_1;
	}
	public void setMouldOpenStepSpeed_1(float mouldOpenStepSpeed_1) {
		this.mouldOpenStepSpeed_1 = mouldOpenStepSpeed_1;
	}
	public void setMouldOpenStepPos_2(float mouldOpenStepPos_2) {
		this.mouldOpenStepPos_2 = mouldOpenStepPos_2;
	}
	public void setMouldOpenStepSpeed_2(float mouldOpenStepSpeed_2) {
		this.mouldOpenStepSpeed_2 = mouldOpenStepSpeed_2;
	}
	public void setMouldOpenStepPos_3(float mouldOpenStepPos_3) {
		this.mouldOpenStepPos_3 = mouldOpenStepPos_3;
	}
	public void setMouldOpenStepSpeed_3(float mouldOpenStepSpeed_3) {
		this.mouldOpenStepSpeed_3 = mouldOpenStepSpeed_3;
	}
	public void setMouldOpenTime(float mouldOpenTime) {
		this.mouldOpenTime = mouldOpenTime;
	}
	public void setEjectStart(String ejectStart) {
		this.ejectStart = ejectStart;
	}
	public void setEjectMode(String ejectMode) {
		this.ejectMode = ejectMode;
	}
	public void setEjectPulse(float ejectPulse) {
		this.ejectPulse = ejectPulse;
	}
	public void setEjectDelay(float ejectDelay) {
		this.ejectDelay = ejectDelay;
	}
	public void setEjectorsFwdPos(float ejectorsFwdPos) {
		this.ejectorsFwdPos = ejectorsFwdPos;
	}
	public void setEjectorsFwdSpeed(float ejectorsFwdSpeed) {
		this.ejectorsFwdSpeed = ejectorsFwdSpeed;
	}
	public void setEjectorsFwdTime(float ejectorsFwdTime) {
		this.ejectorsFwdTime = ejectorsFwdTime;
	}
	public void setEjectorsStopPos(float ejectorsStopPos) {
		this.ejectorsStopPos = ejectorsStopPos;
	}
	public void setEjectorsStopSpeed(float ejectorsStopSpeed) {
		this.ejectorsStopSpeed = ejectorsStopSpeed;
	}
	public void setEjectorsStopTime(float ejectorsStopTime) {
		this.ejectorsStopTime = ejectorsStopTime;
	}
	public void setEjectorsRevPos(float ejectorsRevPos) {
		this.ejectorsRevPos = ejectorsRevPos;
	}
	public void setEjectorsRevSpeed(float ejectorsRevSpeed) {
		this.ejectorsRevSpeed = ejectorsRevSpeed;
	}
	public void setEjectorsRevTime(float ejectorsRevTime) {
		this.ejectorsRevTime = ejectorsRevTime;
	}
	public void setDme_1(float dme_1) {
		this.dme_1 = dme_1;
	}
	public void setDme_2(float dme_2) {
		this.dme_2 = dme_2;
	}
	public void setDme_3(float dme_3) {
		this.dme_3 = dme_3;
	}
	public void setDme_4(float dme_4) {
		this.dme_4 = dme_4;
	}
	public void setDme_5(float dme_5) {
		this.dme_5 = dme_5;
	}
	public void setDme_6(float dme_6) {
		this.dme_6 = dme_6;
	}
	public void setDme_7(float dme_7) {
		this.dme_7 = dme_7;
	}
	public void setDme_8(float dme_8) {
		this.dme_8 = dme_8;
	}
	public void setWaterTempFixedHalf(float waterTempFixedHalf) {
		this.waterTempFixedHalf = waterTempFixedHalf;
	}
	public void setWaterTempMovingHalf(float waterTempMovingHalf) {
		this.waterTempMovingHalf = waterTempMovingHalf;
	}
	public void setWaterTempNotes(String waterTempNotes) {
		this.waterTempNotes = waterTempNotes;
	}
	
	
}
