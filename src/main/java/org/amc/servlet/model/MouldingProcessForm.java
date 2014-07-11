package org.amc.servlet.model;

import javax.validation.ValidationException;

import org.amc.model.MouldingProcess;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class MouldingProcessForm
{
	public String id;// Database ID
	// Top Header
	public String partId;
	public String machineSize;
	public String machineNo;
	public int material;
	public String masterbatchNo;
	public String dateOfIssue;
	public String signOffBy;
	public String processNotes;

	// Injection section
	public String injectionSpeed_1;
	public String injectionSpeed_2;
	public String injectionSpeed_3;
	public String injectionSpeed_4;
	public String injectionSpeed_5;
	public String injectionSpeed_6;

	public String injSpeedPosition_1;
	public String injSpeedPosition_2;
	public String injSpeedPosition_3;
	public String injSpeedPosition_4;
	public String injSpeedPosition_5;
	public String injSpeedPosition_6;

	// Holding pressure

	public String holdingPressure_1;
	public String holdingPressure_2;
	public String holdingPressure_3;
	public String holdingPressure_4;
	public String holdingPressure_5;
	public String holdingPressure_6;

	// Holding Time
	public String holdingTime_1;
	public String holdingTime_2;
	public String holdingTime_3;
	public String holdingTime_4;
	public String holdingTime_5;
	public String holdingTime_6;

	// Injection and Holding variables
	public String maxPackVel;
	public String posTran;
	public String maxInjPre;
	public String maxInjTime;
	public String shotSize;
	public String decompressionDist;
	public String decompressionVel;
	public String coolTime;

	// Extruding

	public String backPressure_1;
	public String backPressure_2;
	public String backPressure_3;
	public String backPressure_4;
	public String backPressure_5;
	public String backPressure_6;

	public String screwExtSpeed_1;
	public String screwExtSpeed_2;
	public String screwExtSpeed_3;
	public String screwExtSpeed_4;
	public String screwExtSpeed_5;
	public String screwExtSpeed_6;

	public String extProfilePos_1;
	public String extProfilePos_2;
	public String extProfilePos_3;
	public String extProfilePos_4;
	public String extProfilePos_5;
	public String extProfilePos_6;

	// Barrel Temperature

	public String nozzelTemperature;
	public String barrelTemperature_1;
	public String barrelTemperature_2;
	public String barrelTemperature_3;
	public String barrelTemperature_4;
	public String throatTemperature;

	// mouldClosing
	public String mouldClosingOpenLimitPos;
	public String mouldClosingOpenLimitSpeed;

	public String mouldClosedLimitPos;
	public String mouldClosedLimitSpeed;

	public String clsSlowPos;
	public String clsSlowSpeed;

	public String clsSPPos;
	public String clsSPSpeed;
	// mould opening

	public String mouldOpenBreakAwaySpeed;
	public String mouldOpenStepPos_1;
	public String mouldOpenStepSpeed_1;
	public String mouldOpenStepPos_2;
	public String mouldOpenStepSpeed_2;
	public String mouldOpenStepPos_3;
	public String mouldOpenStepSpeed_3;

	public String mouldOpenTime;
	public String ejectStart;

	// Ejectors
	public String ejectMode;
	public String ejectPulse;
	public String ejectDelay;

	public String ejectorsFwdPos;
	public String ejectorsFwdSpeed;
	public String ejectorsFwdTime;

	public String ejectorsStopPos;
	public String ejectorsStopSpeed;
	public String ejectorsStopTime;

	public String ejectorsRevPos;
	public String ejectorsRevSpeed;
	public String ejectorsRevTime;

	// DMEs

	public String dme_1;
	public String dme_2;
	public String dme_3;
	public String dme_4;
	public String dme_5;
	public String dme_6;
	public String dme_7;
	public String dme_8;

	// Water
	public String waterTempFixedHalf;
	public String waterTempMovingHalf;
	public String waterTempNotes;

	// Getters/Setters (JavaBean)

	public String getPartId()
	{
		return this.partId;
	}

	public String getMachineSize()
	{
		return this.machineSize;
	}

	public String getMachineNo()
	{
		return this.machineNo;
	}

	public int getMaterial()
	{
		return this.material;
	}

	public String getMasterbatchNo()
	{
		return this.masterbatchNo;
	}

	public String getDateOfIssue()
	{
		return this.dateOfIssue;
	}

	public String getSignOffBy()
	{
		return this.signOffBy;
	}

	public String getProcessNotes()
	{
		return this.processNotes;
	}

	public String getInjectionSpeed_1()
	{
		return this.injectionSpeed_1;
	}

	public String getInjectionSpeed_2()
	{
		return this.injectionSpeed_2;
	}

	public String getInjectionSpeed_3()
	{
		return this.injectionSpeed_3;
	}

	public String getInjectionSpeed_4()
	{
		return this.injectionSpeed_4;
	}

	public String getInjectionSpeed_5()
	{
		return this.injectionSpeed_5;
	}

	public String getInjectionSpeed_6()
	{
		return this.injectionSpeed_6;
	}

	public String getInjSpeedPosition_1()
	{
		return this.injSpeedPosition_1;
	}

	public String getInjSpeedPosition_2()
	{
		return this.injSpeedPosition_2;
	}

	public String getInjSpeedPosition_3()
	{
		return this.injSpeedPosition_3;
	}

	public String getInjSpeedPosition_4()
	{
		return this.injSpeedPosition_4;
	}

	public String getInjSpeedPosition_5()
	{
		return this.injSpeedPosition_5;
	}

	public String getInjSpeedPosition_6()
	{
		return this.injSpeedPosition_6;
	}

	public String getHoldingPressure_1()
	{
		return this.holdingPressure_1;
	}

	public String getHoldingPressure_2()
	{
		return this.holdingPressure_2;
	}

	public String getHoldingPressure_3()
	{
		return this.holdingPressure_3;
	}

	public String getHoldingPressure_4()
	{
		return this.holdingPressure_4;
	}

	public String getHoldingPressure_5()
	{
		return this.holdingPressure_5;
	}

	public String getHoldingPressure_6()
	{
		return this.holdingPressure_6;
	}

	public String getHoldingTime_1()
	{
		return this.holdingTime_1;
	}

	public String getHoldingTime_2()
	{
		return this.holdingTime_2;
	}

	public String getHoldingTime_3()
	{
		return this.holdingTime_3;
	}

	public String getHoldingTime_4()
	{
		return this.holdingTime_4;
	}

	public String getHoldingTime_5()
	{
		return this.holdingTime_5;
	}

	public String getHoldingTime_6()
	{
		return this.holdingTime_6;
	}

	public String getMaxPackVel()
	{
		return this.maxPackVel;
	}

	public String getPosTran()
	{
		return this.posTran;
	}

	public String getMaxInjPre()
	{
		return this.maxInjPre;
	}

	public String getMaxInjTime()
	{
		return this.maxInjTime;
	}

	public String getShotSize()
	{
		return this.shotSize;
	}

	public String getDecompressionVel()
	{
		return this.decompressionVel;
	}

	public String getCoolTime()
	{
		return this.coolTime;
	}

	public String getBackPressure_1()
	{
		return this.backPressure_1;
	}

	public String getBackPressure_2()
	{
		return this.backPressure_2;
	}

	public String getBackPressure_3()
	{
		return this.backPressure_3;
	}

	public String getBackPressure_4()
	{
		return this.backPressure_4;
	}

	public String getBackPressure_5()
	{
		return this.backPressure_5;
	}

	public String getBackPressure_6()
	{
		return this.backPressure_6;
	}

	public String getScrewExtSpeed_1()
	{
		return this.screwExtSpeed_1;
	}

	public String getScrewExtSpeed_2()
	{
		return this.screwExtSpeed_2;
	}

	public String getScrewExtSpeed_3()
	{
		return this.screwExtSpeed_3;
	}

	public String getScrewExtSpeed_4()
	{
		return this.screwExtSpeed_4;
	}

	public String getScrewExtSpeed_5()
	{
		return this.screwExtSpeed_5;
	}

	public String getScrewExtSpeed_6()
	{
		return this.screwExtSpeed_6;
	}

	public String getExtProfilePos_1()
	{
		return this.extProfilePos_1;
	}

	public String getExtProfilePos_2()
	{
		return this.extProfilePos_2;
	}

	public String getExtProfilePos_3()
	{
		return this.extProfilePos_3;
	}

	public String getExtProfilePos_4()
	{
		return this.extProfilePos_4;
	}

	public String getExtProfilePos_5()
	{
		return this.extProfilePos_5;
	}

	public String getExtProfilePos_6()
	{
		return this.extProfilePos_6;
	}

	public String getNozzelTemperature()
	{
		return this.nozzelTemperature;
	}

	public String getBarrelTemperature_1()
	{
		return this.barrelTemperature_1;
	}

	public String getBarrelTemperature_2()
	{
		return this.barrelTemperature_2;
	}

	public String getBarrelTemperature_3()
	{
		return this.barrelTemperature_3;
	}

	public String getBarrelTemperature_4()
	{
		return this.barrelTemperature_4;
	}

	public String getThroatTemperature()
	{
		return this.throatTemperature;
	}

	public String getMouldClosingOpenLimitPos()
	{
		return this.mouldClosingOpenLimitPos;
	}

	public String getMouldClosingOpenLimitSpeed()
	{
		return this.mouldClosingOpenLimitSpeed;
	}

	public String getMouldClosedLimitPos()
	{
		return this.mouldClosedLimitPos;
	}

	public String getMouldClosedLimitSpeed()
	{
		return this.mouldClosedLimitSpeed;
	}

	public String getClsSlowPos()
	{
		return this.clsSlowPos;
	}

	public String getClsSlowSpeed()
	{
		return this.clsSlowSpeed;
	}

	public String getMouldOpenBreakAwaySpeed()
	{
		return this.mouldOpenBreakAwaySpeed;
	}

	public String getMouldOpenStepPos_1()
	{
		return this.mouldOpenStepPos_1;
	}

	public String getMouldOpenStepSpeed_1()
	{
		return this.mouldOpenStepSpeed_1;
	}

	public String getMouldOpenStepPos_2()
	{
		return this.mouldOpenStepPos_2;
	}

	public String getMouldOpenStepSpeed_2()
	{
		return this.mouldOpenStepSpeed_2;
	}

	public String getMouldOpenStepPos_3()
	{
		return this.mouldOpenStepPos_3;
	}

	public String getMouldOpenStepSpeed_3()
	{
		return this.mouldOpenStepSpeed_3;
	}

	public String getMouldOpenTime()
	{
		return this.mouldOpenTime;
	}

	public String getEjectStart()
	{
		return this.ejectStart;
	}

	public String getEjectMode()
	{
		return this.ejectMode;
	}

	public String getEjectPulse()
	{
		return this.ejectPulse;
	}

	public String getEjectDelay()
	{
		return this.ejectDelay;
	}

	public String getEjectorsFwdPos()
	{
		return this.ejectorsFwdPos;
	}

	public String getEjectorsFwdSpeed()
	{
		return this.ejectorsFwdSpeed;
	}

	public String getEjectorsFwdTime()
	{
		return this.ejectorsFwdTime;
	}

	public String getEjectorsStopPos()
	{
		return this.ejectorsStopPos;
	}

	public String getEjectorsStopSpeed()
	{
		return this.ejectorsStopSpeed;
	}

	public String getEjectorsStopTime()
	{
		return this.ejectorsStopTime;
	}

	public String getEjectorsRevPos()
	{
		return this.ejectorsRevPos;
	}

	public String getEjectorsRevSpeed()
	{
		return this.ejectorsRevSpeed;
	}

	public String getEjectorsRevTime()
	{
		return this.ejectorsRevTime;
	}

	public String getDme_1()
	{
		return this.dme_1;
	}

	public String getDme_2()
	{
		return this.dme_2;
	}

	public String getDme_3()
	{
		return this.dme_3;
	}

	public String getDme_4()
	{
		return this.dme_4;
	}

	public String getDme_5()
	{
		return this.dme_5;
	}

	public String getDme_6()
	{
		return this.dme_6;
	}

	public String getDme_7()
	{
		return this.dme_7;
	}

	public String getDme_8()
	{
		return this.dme_8;
	}

	public String getWaterTempFixedHalf()
	{
		return this.waterTempFixedHalf;
	}

	public String getWaterTempMovingHalf()
	{
		return this.waterTempMovingHalf;
	}

	public String getWaterTempNotes()
	{
		return this.waterTempNotes;
	}

	public void setPartId(String partId)
	{
		this.partId = partId;
	}

	public void setMachineSize(String machineSize)
	{
		this.machineSize = machineSize;
	}

	public void setMachineNo(String machineNo)
	{
		this.machineNo = machineNo;
	}

	public void setMaterial(int material)
	{
		this.material = material;
	}

	public void setMasterbatchNo(String masterbatchNo)
	{
		this.masterbatchNo = masterbatchNo;
	}

	public void setDateOfIssue(String dateOfIssue)
	{

		this.dateOfIssue = dateOfIssue;

	}

	public void setSignOffBy(String signOffBy)
	{
		this.signOffBy = signOffBy;
	}

	public void setProcessNotes(String processNotes)
	{
		this.processNotes = processNotes;
	}

	public void setInjectionSpeed_1(String injectionSpeed_1)
	{
		this.injectionSpeed_1 = injectionSpeed_1;
	}

	public void setInjectionSpeed_2(String injectionSpeed_2)
	{
		this.injectionSpeed_2 = injectionSpeed_2;
	}

	public void setInjectionSpeed_3(String injectionSpeed_3)
	{
		this.injectionSpeed_3 = injectionSpeed_3;
	}

	public void setInjectionSpeed_4(String injectionSpeed_4)
	{
		this.injectionSpeed_4 = injectionSpeed_4;
	}

	public void setInjectionSpeed_5(String injectionSpeed_5)
	{
		this.injectionSpeed_5 = injectionSpeed_5;
	}

	public void setInjectionSpeed_6(String injectionSpeed_6)
	{
		this.injectionSpeed_6 = injectionSpeed_6;
	}

	public void setInjSpeedPosition_1(String injSpeedPosition_1)
	{
		this.injSpeedPosition_1 = injSpeedPosition_1;
	}

	public void setInjSpeedPosition_2(String injSpeedPosition_2)
	{
		this.injSpeedPosition_2 = injSpeedPosition_2;
	}

	public void setInjSpeedPosition_3(String injSpeedPosition_3)
	{
		this.injSpeedPosition_3 = injSpeedPosition_3;
	}

	public void setInjSpeedPosition_4(String injSpeedPosition_4)
	{
		this.injSpeedPosition_4 = injSpeedPosition_4;
	}

	public void setInjSpeedPosition_5(String injSpeedPosition_5)
	{
		this.injSpeedPosition_5 = injSpeedPosition_5;
	}

	public void setInjSpeedPosition_6(String injSpeedPosition_6)
	{
		this.injSpeedPosition_6 = injSpeedPosition_6;
	}

	public void setHoldingPressure_1(String holdingPressure_1)
	{
		this.holdingPressure_1 = holdingPressure_1;
	}

	public void setHoldingPressure_2(String holdingPressure_2)
	{
		this.holdingPressure_2 = holdingPressure_2;
	}

	public void setHoldingPressure_3(String holdingPressure_3)
	{
		this.holdingPressure_3 = holdingPressure_3;
	}

	public void setHoldingPressure_4(String holdingPressure_4)
	{
		this.holdingPressure_4 = holdingPressure_4;
	}

	public void setHoldingPressure_5(String holdingPressure_5)
	{
		this.holdingPressure_5 = holdingPressure_5;
	}

	public void setHoldingPressure_6(String holdingPressure_6)
	{
		this.holdingPressure_6 = holdingPressure_6;
	}

	public void setHoldingTime_1(String holdingTime_1)
	{
		this.holdingTime_1 = holdingTime_1;
	}

	public void setHoldingTime_2(String holdingTime_2)
	{
		this.holdingTime_2 = holdingTime_2;
	}

	public void setHoldingTime_3(String holdingTime_3)
	{
		this.holdingTime_3 = holdingTime_3;
	}

	public void setHoldingTime_4(String holdingTime_4)
	{
		this.holdingTime_4 = holdingTime_4;
	}

	public void setHoldingTime_5(String holdingTime_5)
	{
		this.holdingTime_5 = holdingTime_5;
	}

	public void setHoldingTime_6(String holdingTime_6)
	{
		this.holdingTime_6 = holdingTime_6;
	}

	public void setMaxPackVel(String maxPackVel)
	{
		this.maxPackVel = maxPackVel;
	}

	public void setPosTran(String posTran)
	{
		this.posTran = posTran;
	}

	public void setMaxInjPre(String maxInjPre)
	{
		this.maxInjPre = maxInjPre;
	}

	public void setMaxInjTime(String maxInjTime)
	{
		this.maxInjTime = maxInjTime;
	}

	public void setShotSize(String shotSize)
	{
		this.shotSize = shotSize;
	}

	public void setDecompressionVel(String decompressionVel)
	{
		this.decompressionVel = decompressionVel;
	}

	public void setCoolTime(String coolTime)
	{
		this.coolTime = coolTime;
	}

	public void setBackPressure_1(String backPressure_1)
	{
		this.backPressure_1 = backPressure_1;
	}

	public void setBackPressure_2(String backPressure_2)
	{
		this.backPressure_2 = backPressure_2;
	}

	public void setBackPressure_3(String backPressure_3)
	{
		this.backPressure_3 = backPressure_3;
	}

	public void setBackPressure_4(String backPressure_4)
	{
		this.backPressure_4 = backPressure_4;
	}

	public void setBackPressure_5(String backPressure_5)
	{
		this.backPressure_5 = backPressure_5;
	}

	public void setBackPressure_6(String backPressure_6)
	{
		this.backPressure_6 = backPressure_6;
	}

	public void setScrewExtSpeed_1(String screwExtSpeed_1)
	{
		this.screwExtSpeed_1 = screwExtSpeed_1;
	}

	public void setScrewExtSpeed_2(String screwExtSpeed_2)
	{
		this.screwExtSpeed_2 = screwExtSpeed_2;
	}

	public void setScrewExtSpeed_3(String screwExtSpeed_3)
	{
		this.screwExtSpeed_3 = screwExtSpeed_3;
	}

	public void setScrewExtSpeed_4(String screwExtSpeed_4)
	{
		this.screwExtSpeed_4 = screwExtSpeed_4;
	}

	public void setScrewExtSpeed_5(String screwExtSpeed_5)
	{
		this.screwExtSpeed_5 = screwExtSpeed_5;
	}

	public void setScrewExtSpeed_6(String screwExtSpeed_6)
	{
		this.screwExtSpeed_6 = screwExtSpeed_6;
	}

	public void setExtProfilePos_1(String extProfilePos_1)
	{
		this.extProfilePos_1 = extProfilePos_1;
	}

	public void setExtProfilePos_2(String extProfilePos_2)
	{
		this.extProfilePos_2 = extProfilePos_2;
	}

	public void setExtProfilePos_3(String extProfilePos_3)
	{
		this.extProfilePos_3 = extProfilePos_3;
	}

	public void setExtProfilePos_4(String extProfilePos_4)
	{
		this.extProfilePos_4 = extProfilePos_4;
	}

	public void setExtProfilePos_5(String extProfilePos_5)
	{
		this.extProfilePos_5 = extProfilePos_5;
	}

	public void setExtProfilePos_6(String extProfilePos_6)
	{
		this.extProfilePos_6 = extProfilePos_6;
	}

	public void setNozzelTemperature(String nozzelTemperature)
	{
		this.nozzelTemperature = nozzelTemperature;
	}

	public void setBarrelTemperature_1(String barrelTemperature_1)
	{
		this.barrelTemperature_1 = barrelTemperature_1;
	}

	public void setBarrelTemperature_2(String barrelTemperature_2)
	{
		this.barrelTemperature_2 = barrelTemperature_2;
	}

	public void setBarrelTemperature_3(String barrelTemperature_3)
	{
		this.barrelTemperature_3 = barrelTemperature_3;
	}

	public void setBarrelTemperature_4(String barrelTemperature_4)
	{
		this.barrelTemperature_4 = barrelTemperature_4;
	}

	public void setThroatTemperature(String throatTemperature)
	{
		this.throatTemperature = throatTemperature;
	}

	public void setMouldClosingOpenLimitPos(String mouldClosingOpenLimitPos)
	{
		this.mouldClosingOpenLimitPos = mouldClosingOpenLimitPos;
	}

	public void setMouldClosingOpenLimitSpeed(String mouldClosingOpenLimitSpeed)
	{
		this.mouldClosingOpenLimitSpeed = mouldClosingOpenLimitSpeed;
	}

	public void setMouldClosedLimitPos(String mouldClosedLimitPos)
	{
		this.mouldClosedLimitPos = mouldClosedLimitPos;
	}

	public void setMouldClosedLimitSpeed(String mouldClosedLimitSpeed)
	{
		this.mouldClosedLimitSpeed = mouldClosedLimitSpeed;
	}

	public void setClsSlowPos(String clsSlowPos)
	{
		this.clsSlowPos = clsSlowPos;
	}

	public void setClsSlowSpeed(String clsSlowSpeed)
	{
		this.clsSlowSpeed = clsSlowSpeed;
	}

	public void setMouldOpenBreakAwaySpeed(String mouldOpenBreakAwaySpeed)
	{
		this.mouldOpenBreakAwaySpeed = mouldOpenBreakAwaySpeed;
	}

	public void setMouldOpenStepPos_1(String mouldOpenStepPos_1)
	{
		this.mouldOpenStepPos_1 = mouldOpenStepPos_1;
	}

	public void setMouldOpenStepSpeed_1(String mouldOpenStepSpeed_1)
	{
		this.mouldOpenStepSpeed_1 = mouldOpenStepSpeed_1;
	}

	public void setMouldOpenStepPos_2(String mouldOpenStepPos_2)
	{
		this.mouldOpenStepPos_2 = mouldOpenStepPos_2;
	}

	public void setMouldOpenStepSpeed_2(String mouldOpenStepSpeed_2)
	{
		this.mouldOpenStepSpeed_2 = mouldOpenStepSpeed_2;
	}

	public void setMouldOpenStepPos_3(String mouldOpenStepPos_3)
	{
		this.mouldOpenStepPos_3 = mouldOpenStepPos_3;
	}

	public void setMouldOpenStepSpeed_3(String mouldOpenStepSpeed_3)
	{
		this.mouldOpenStepSpeed_3 = mouldOpenStepSpeed_3;
	}

	public void setMouldOpenTime(String mouldOpenTime)
	{
		this.mouldOpenTime = mouldOpenTime;
	}

	public void setEjectStart(String ejectStart)
	{
		this.ejectStart = ejectStart;
	}

	public void setEjectMode(String ejectMode)
	{
		this.ejectMode = ejectMode;
	}

	public void setEjectPulse(String ejectPulse)
	{
		this.ejectPulse = ejectPulse;
	}

	public void setEjectDelay(String ejectDelay)
	{
		this.ejectDelay = ejectDelay;
	}

	public void setEjectorsFwdPos(String ejectorsFwdPos)
	{
		this.ejectorsFwdPos = ejectorsFwdPos;
	}

	public void setEjectorsFwdSpeed(String ejectorsFwdSpeed)
	{
		this.ejectorsFwdSpeed = ejectorsFwdSpeed;
	}

	public void setEjectorsFwdTime(String ejectorsFwdTime)
	{
		this.ejectorsFwdTime = ejectorsFwdTime;
	}

	public void setEjectorsStopPos(String ejectorsStopPos)
	{
		this.ejectorsStopPos = ejectorsStopPos;
	}

	public void setEjectorsStopSpeed(String ejectorsStopSpeed)
	{
		this.ejectorsStopSpeed = ejectorsStopSpeed;
	}

	public void setEjectorsStopTime(String ejectorsStopTime)
	{
		this.ejectorsStopTime = ejectorsStopTime;
	}

	public void setEjectorsRevPos(String ejectorsRevPos)
	{
		this.ejectorsRevPos = ejectorsRevPos;
	}

	public void setEjectorsRevSpeed(String ejectorsRevSpeed)
	{
		this.ejectorsRevSpeed = ejectorsRevSpeed;
	}

	public void setEjectorsRevTime(String ejectorsRevTime)
	{
		this.ejectorsRevTime = ejectorsRevTime;
	}

	public void setDme_1(String dme_1)
	{
		this.dme_1 = dme_1;
	}

	public void setDme_2(String dme_2)
	{
		this.dme_2 = dme_2;
	}

	public void setDme_3(String dme_3)
	{
		this.dme_3 = dme_3;
	}

	public void setDme_4(String dme_4)
	{
		this.dme_4 = dme_4;
	}

	public void setDme_5(String dme_5)
	{
		this.dme_5 = dme_5;
	}

	public void setDme_6(String dme_6)
	{
		this.dme_6 = dme_6;
	}

	public void setDme_7(String dme_7)
	{
		this.dme_7 = dme_7;
	}

	public void setDme_8(String dme_8)
	{
		this.dme_8 = dme_8;
	}

	public void setWaterTempFixedHalf(String waterTempFixedHalf)
	{
		this.waterTempFixedHalf = waterTempFixedHalf;
	}

	public void setWaterTempMovingHalf(String waterTempMovingHalf)
	{
		this.waterTempMovingHalf = waterTempMovingHalf;
	}

	public void setWaterTempNotes(String waterTempNotes)
	{
		this.waterTempNotes = waterTempNotes;
	}

	public String getClsSPPos()
	{
		return this.clsSPPos;
	}

	public String getClsSPSpeed()
	{
		return this.clsSPSpeed;
	}

	public void setClsSPPos(String clsSPPos)
	{
		this.clsSPPos = clsSPPos;
	}

	public void setClsSPSpeed(String clsSPSpeed)
	{
		this.clsSPSpeed = clsSPSpeed;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDecompressionDist()
	{
		return decompressionDist;
	}

	public void setDecompressionDist(String decompressionDist)
	{
		this.decompressionDist = decompressionDist;
	}

	@Override
	public String toString()
	{
		String result = "ProcessSheet:()"; // TODO implement a better string
											// representation of a MouldingForm

		return result;
	}

	/**
	 * 
	 * @param jForm
	 * @return a MouldingProces object containing the values in jForm but cast
	 *         to the correct object
	 * @throws Exception
	 *             if parsing/casting of the jForm variables fails
	 */
	public static MouldingProcess getMouldingProcess(MouldingProcessForm jForm) throws ValidationException
	{
		MouldingProcess processSheet = null;
		try
		{

			processSheet = new MouldingProcess();

			processSheet.setId(Integer.parseInt(jForm.getId()));

			processSheet.setPartId(jForm.getPartId());
			processSheet.setMachineSize(Integer.parseInt(jForm.getMachineSize()));
			processSheet.setMachineNo(jForm.getMachineNo());
			processSheet.setMaterial(jForm.getMaterial());
			processSheet.setMasterbatchNo(jForm.getMasterbatchNo());
			processSheet.setDateOfIssue(java.sql.Date.valueOf(jForm.getDateOfIssue()));
			processSheet.setSignOffBy(jForm.getSignOffBy());
			processSheet.setProcessNotes(jForm.getProcessNotes());

			processSheet.setInjectionSpeed_1(Float.parseFloat(jForm.getInjectionSpeed_1()));

			processSheet.setInjectionSpeed_2(Float.parseFloat(jForm.getInjectionSpeed_2()));
			processSheet.setInjectionSpeed_3(Float.parseFloat(jForm.getInjectionSpeed_3()));
			processSheet.setInjectionSpeed_4(Float.parseFloat(jForm.getInjectionSpeed_4()));
			processSheet.setInjectionSpeed_5(Float.parseFloat(jForm.getInjectionSpeed_5()));
			processSheet.setInjectionSpeed_6(Float.parseFloat(jForm.getInjectionSpeed_6()));
			processSheet.setInjSpeedPosition_1(Float.parseFloat(jForm.getInjSpeedPosition_1()));
			processSheet.setInjSpeedPosition_2(Float.parseFloat(jForm.getInjSpeedPosition_2()));
			processSheet.setInjSpeedPosition_3(Float.parseFloat(jForm.getInjSpeedPosition_3()));
			processSheet.setInjSpeedPosition_4(Float.parseFloat(jForm.getInjSpeedPosition_4()));
			processSheet.setInjSpeedPosition_5(Float.parseFloat(jForm.getInjSpeedPosition_5()));
			processSheet.setInjSpeedPosition_6(Float.parseFloat(jForm.getInjSpeedPosition_6()));
			processSheet.setHoldingPressure_1(Float.parseFloat(jForm.getHoldingPressure_1()));
			processSheet.setHoldingPressure_2(Float.parseFloat(jForm.getHoldingPressure_2()));
			processSheet.setHoldingPressure_3(Float.parseFloat(jForm.getHoldingPressure_3()));
			processSheet.setHoldingPressure_4(Float.parseFloat(jForm.getHoldingPressure_4()));
			processSheet.setHoldingPressure_5(Float.parseFloat(jForm.getHoldingPressure_5()));
			processSheet.setHoldingPressure_6(Float.parseFloat(jForm.getHoldingPressure_6()));
			processSheet.setHoldingTime_1(Float.parseFloat(jForm.getHoldingTime_1()));
			processSheet.setHoldingTime_2(Float.parseFloat(jForm.getHoldingTime_2()));
			processSheet.setHoldingTime_3(Float.parseFloat(jForm.getHoldingTime_3()));
			processSheet.setHoldingTime_4(Float.parseFloat(jForm.getHoldingTime_4()));
			processSheet.setHoldingTime_5(Float.parseFloat(jForm.getHoldingTime_5()));
			processSheet.setHoldingTime_6(Float.parseFloat(jForm.getHoldingTime_6()));
			processSheet.setMaxPackVel(Float.parseFloat(jForm.getMaxPackVel()));
			processSheet.setPosTran(Float.parseFloat(jForm.getPosTran()));
			processSheet.setMaxInjPre(Float.parseFloat(jForm.getMaxInjPre()));
			processSheet.setMaxInjTime(Float.parseFloat(jForm.getMaxInjTime()));
			processSheet.setShotSize(Float.parseFloat(jForm.getShotSize()));
			processSheet.setDecompressionVel(Float.parseFloat(jForm.getDecompressionVel()));
			processSheet.setCoolTime(Float.parseFloat(jForm.getCoolTime()));
			processSheet.setBackPressure_1(Float.parseFloat(jForm.getBackPressure_1()));
			processSheet.setBackPressure_2(Float.parseFloat(jForm.getBackPressure_2()));
			processSheet.setBackPressure_3(Float.parseFloat(jForm.getBackPressure_3()));
			processSheet.setBackPressure_4(Float.parseFloat(jForm.getBackPressure_4()));
			processSheet.setBackPressure_5(Float.parseFloat(jForm.getBackPressure_5()));
			processSheet.setBackPressure_6(Float.parseFloat(jForm.getBackPressure_6()));
			processSheet.setScrewExtSpeed_1(Float.parseFloat(jForm.getScrewExtSpeed_1()));
			processSheet.setScrewExtSpeed_2(Float.parseFloat(jForm.getScrewExtSpeed_2()));
			processSheet.setScrewExtSpeed_3(Float.parseFloat(jForm.getScrewExtSpeed_3()));
			processSheet.setScrewExtSpeed_4(Float.parseFloat(jForm.getScrewExtSpeed_4()));
			processSheet.setScrewExtSpeed_5(Float.parseFloat(jForm.getScrewExtSpeed_5()));
			processSheet.setScrewExtSpeed_6(Float.parseFloat(jForm.getScrewExtSpeed_6()));
			processSheet.setExtProfilePos_1(Float.parseFloat(jForm.getExtProfilePos_1()));
			processSheet.setExtProfilePos_2(Float.parseFloat(jForm.getExtProfilePos_2()));
			processSheet.setExtProfilePos_3(Float.parseFloat(jForm.getExtProfilePos_3()));
			processSheet.setExtProfilePos_4(Float.parseFloat(jForm.getExtProfilePos_4()));
			processSheet.setExtProfilePos_5(Float.parseFloat(jForm.getExtProfilePos_5()));
			processSheet.setExtProfilePos_6(Float.parseFloat(jForm.getExtProfilePos_6()));
			processSheet.setNozzelTemperature(Float.parseFloat(jForm.getNozzelTemperature()));
			processSheet.setBarrelTemperature_1(Float.parseFloat(jForm.getBarrelTemperature_1()));
			processSheet.setBarrelTemperature_2(Float.parseFloat(jForm.getBarrelTemperature_2()));
			processSheet.setBarrelTemperature_3(Float.parseFloat(jForm.getBarrelTemperature_3()));
			processSheet.setBarrelTemperature_4(Float.parseFloat(jForm.getBarrelTemperature_4()));
			processSheet.setThroatTemperature(Float.parseFloat(jForm.getThroatTemperature()));
			processSheet.setMouldClosingOpenLimitPos(Float.parseFloat(jForm.getMouldClosingOpenLimitPos()));
			processSheet.setMouldClosingOpenLimitSpeed(Float.parseFloat(jForm.getMouldClosingOpenLimitSpeed()));
			processSheet.setMouldClosedLimitPos(Float.parseFloat(jForm.getMouldClosedLimitPos()));
			processSheet.setMouldClosedLimitSpeed(Float.parseFloat(jForm.getMouldClosedLimitSpeed()));
			processSheet.setClsSlowPos(Float.parseFloat(jForm.getClsSlowPos()));
			processSheet.setClsSlowSpeed(Float.parseFloat(jForm.getClsSlowSpeed()));
			processSheet.setMouldOpenBreakAwaySpeed(Float.parseFloat(jForm.getMouldOpenBreakAwaySpeed()));
			processSheet.setMouldOpenStepPos_1(Float.parseFloat(jForm.getMouldOpenStepPos_1()));
			processSheet.setMouldOpenStepSpeed_1(Float.parseFloat(jForm.getMouldOpenStepSpeed_1()));
			processSheet.setMouldOpenStepPos_2(Float.parseFloat(jForm.getMouldOpenStepPos_2()));
			processSheet.setMouldOpenStepSpeed_2(Float.parseFloat(jForm.getMouldOpenStepSpeed_2()));
			processSheet.setMouldOpenStepPos_3(Float.parseFloat(jForm.getMouldOpenStepPos_3()));
			processSheet.setMouldOpenStepSpeed_3(Float.parseFloat(jForm.getMouldOpenStepSpeed_3()));
			processSheet.setMouldOpenTime(Float.parseFloat(jForm.getMouldOpenTime()));
			processSheet.setEjectStart(jForm.getEjectStart());
			processSheet.setEjectMode(jForm.getEjectMode());
			processSheet.setEjectPulse(Float.parseFloat(jForm.getEjectPulse()));
			processSheet.setEjectDelay(Float.parseFloat(jForm.getEjectDelay()));
			processSheet.setEjectorsFwdPos(Float.parseFloat(jForm.getEjectorsFwdPos()));
			processSheet.setEjectorsFwdSpeed(Float.parseFloat(jForm.getEjectorsFwdSpeed()));
			processSheet.setEjectorsFwdTime(Float.parseFloat(jForm.getEjectorsFwdTime()));
			processSheet.setEjectorsStopPos(Float.parseFloat(jForm.getEjectorsStopPos()));
			processSheet.setEjectorsStopSpeed(Float.parseFloat(jForm.getEjectorsStopSpeed()));
			processSheet.setEjectorsStopTime(Float.parseFloat(jForm.getEjectorsStopTime()));
			processSheet.setEjectorsRevPos(Float.parseFloat(jForm.getEjectorsRevPos()));
			processSheet.setEjectorsRevSpeed(Float.parseFloat(jForm.getEjectorsRevSpeed()));
			processSheet.setEjectorsRevTime(Float.parseFloat(jForm.getEjectorsRevTime()));
			processSheet.setDme_1(Float.parseFloat(jForm.getDme_1()));
			processSheet.setDme_2(Float.parseFloat(jForm.getDme_2()));
			processSheet.setDme_3(Float.parseFloat(jForm.getDme_3()));
			processSheet.setDme_4(Float.parseFloat(jForm.getDme_4()));
			processSheet.setDme_5(Float.parseFloat(jForm.getDme_5()));
			processSheet.setDme_6(Float.parseFloat(jForm.getDme_6()));
			processSheet.setDme_7(Float.parseFloat(jForm.getDme_7()));
			processSheet.setDme_8(Float.parseFloat(jForm.getDme_8()));
			processSheet.setWaterTempFixedHalf(Float.parseFloat(jForm.getWaterTempFixedHalf()));
			processSheet.setWaterTempMovingHalf(Float.parseFloat(jForm.getWaterTempMovingHalf()));
			processSheet.setWaterTempNotes(jForm.getWaterTempNotes());
			processSheet.setClsSPPos(Float.parseFloat(jForm.getClsSPPos()));
			processSheet.setClsSPSpeed(Float.parseFloat(jForm.getClsSPSpeed()));
			processSheet.setDecompressionDist(Float.parseFloat(jForm.getDecompressionDist()));
		} catch (NumberFormatException e)
		{
			ValidationException exception = new ValidationException("Couldn't parse MouldingProcessForm into MouldingProcess object");
			exception.initCause(e);
			throw exception;
		}
		return processSheet;
	}

}
