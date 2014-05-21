package org.amc.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "processSheets")
public class MouldingProcessImpl implements MouldingProcess,Serializable
{

	private static final long serialVersionUID = -2782845506401132766L;
	@Id
	private int id;// Database ID
	// Top Header
	@Column(nullable=false)
	private String partId;
	@Column(nullable=false)
	private int machineSize;
	@Column(nullable=false)
	private String machineNo;
	@Column(nullable=false)
	private int material;
	@Column
	private String masterbatchNo;
	@Column(nullable=false)
	private Date dateOfIssue;
	@Column(nullable=false)
	private String signOffBy;
	@Column
	private String processNotes;

	// Injection section
	@Column
	private float injectionSpeed_1;
	@Column
	private float injectionSpeed_2;
	@Column
	private float injectionSpeed_3;
	@Column
	private float injectionSpeed_4;
	@Column
	private float injectionSpeed_5;
	@Column
	private float injectionSpeed_6;

	@Column
	private float injSpeedPosition_1;
	@Column
	private float injSpeedPosition_2;
	@Column
	private float injSpeedPosition_3;
	@Column
	private float injSpeedPosition_4;
	@Column
	private float injSpeedPosition_5;
	@Column
	private float injSpeedPosition_6;

	// Holding pressure
	@Column
	private float holdingPressure_1;
	@Column
	private float holdingPressure_2;
	@Column
	private float holdingPressure_3;
	@Column
	private float holdingPressure_4;
	@Column
	private float holdingPressure_5;
	@Column
	private float holdingPressure_6;

	// Holding Time
	@Column
	private float holdingTime_1;
	@Column
	private float holdingTime_2;
	@Column
	private float holdingTime_3;
	@Column
	private float holdingTime_4;
	@Column
	private float holdingTime_5;
	@Column
	private float holdingTime_6;

	// Injection and Holding variables
	@Column
	private float maxPackVel;
	@Column
	private float posTran;
	@Column
	private float maxInjPre;
	@Column
	private float maxInjTime;
	@Column
	private float shotSize;
	@Column
	private float decompressionDist;
	@Column
	private float decompressionVel;
	@Column
	private float coolTime;

	// Extruding
	@Column
	private float backPressure_1;
	@Column
	private float backPressure_2;
	@Column
	private float backPressure_3;
	@Column
	private float backPressure_4;
	@Column
	private float backPressure_5;
	@Column
	private float backPressure_6;

	@Column
	private float screwExtSpeed_1;
	@Column
	private float screwExtSpeed_2;
	@Column
	private float screwExtSpeed_3;
	@Column
	private float screwExtSpeed_4;
	@Column
	private float screwExtSpeed_5;
	@Column
	private float screwExtSpeed_6;

	@Column
	private float extProfilePos_1;
	@Column
	private float extProfilePos_2;
	@Column
	private float extProfilePos_3;
	@Column
	private float extProfilePos_4;
	@Column
	private float extProfilePos_5;
	@Column
	private float extProfilePos_6;

	// Barrel Temperature
	@Column
	private float nozzelTemperature;
	@Column
	private float barrelTemperature_1;
	@Column
	private float barrelTemperature_2;
	@Column
	private float barrelTemperature_3;
	@Column
	private float barrelTemperature_4;
	@Column
	private float throatTemperature;

	// mouldClosing
	@Column
	private float mouldClosingOpenLimitPos;
	@Column
	private float mouldClosingOpenLimitSpeed;
	@Column
	private float mouldClosedLimitPos;
	@Column
	private float mouldClosedLimitSpeed;

	@Column
	private float clsSlowPos;
	@Column
	private float clsSlowSpeed;
	@Column
	private float clsSPPos;
	@Column
	private float clsSPSpeed;

	// mould opening
	@Column
	private float mouldOpenBreakAwaySpeed;
	@Column
	private float mouldOpenStepPos_1;
	@Column
	private float mouldOpenStepSpeed_1;
	@Column
	private float mouldOpenStepPos_2;
	@Column
	private float mouldOpenStepSpeed_2;
	@Column
	private float mouldOpenStepPos_3;
	@Column
	private float mouldOpenStepSpeed_3;

	@Column
	private float mouldOpenTime;
	@Column
	private String ejectStart;

	// Ejectors
	@Column
	private String ejectMode;
	@Column
	private float ejectPulse;
	@Column
	private float ejectDelay;
	@Column
	private float ejectorsFwdPos;
	@Column
	private float ejectorsFwdSpeed;
	@Column
	private float ejectorsFwdTime;
	@Column
	private float ejectorsStopPos;
	@Column
	private float ejectorsStopSpeed;
	@Column
	private float ejectorsStopTime;
	@Column
	private float ejectorsRevPos;
	@Column
	private float ejectorsRevSpeed;
	@Column
	private float ejectorsRevTime;

	// DMEs
	@Column
	private float dme_1;
	@Column
	private float dme_2;
	@Column
	private float dme_3;
	@Column
	private float dme_4;
	@Column
	private float dme_5;
	@Column
	private float dme_6;
	@Column
	private float dme_7;
	@Column
	private float dme_8;

	// Water
	@Column
	private float waterTempFixedHalf;
	@Column
	private float waterTempMovingHalf;
	@Column
	private String waterTempNotes;

	public MouldingProcessImpl()
	{

	}

	// Getters/Setters
	public void setField(String field, Float f)
	{
		try
		{
			Field refField = this.getClass().getDeclaredField(field);
			refField.setFloat(this, f);
		} catch (NoSuchFieldException e)
		{

			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{

			e.printStackTrace();
		} catch (IllegalAccessException e)
		{

			e.printStackTrace();
		}

	}

	public void setField(String field, java.sql.Date s)
	{
		try
		{
			Field refField = this.getClass().getDeclaredField(field);
			refField.set(this, s);
		} catch (NoSuchFieldException e)
		{

			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{

			e.printStackTrace();
		} catch (IllegalAccessException e)
		{

			e.printStackTrace();
		}

	}

	public void setField(String field, String s)
	{
		try
		{
			Field refField = this.getClass().getDeclaredField(field);
			refField.set(this, s);
		} 
		catch (NoSuchFieldException e)
		{

			e.printStackTrace();
		} 
		catch (IllegalArgumentException e)
		{

			e.printStackTrace();
		} 
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

	}

	public void setField(String field, Integer s)
	{
		try
		{
			Field refField = this.getClass().getDeclaredField(field);
			refField.setInt(this, s);
		} 
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

	}

	public void setField(String field, Object obj)
	{
		try
		{
			Field refField = this.getClass().getDeclaredField(field);
			refField.set(this, obj);
		} 
		catch (NoSuchFieldException e)
		{
			
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e)
		{
			
			e.printStackTrace();
		} 
		catch (IllegalAccessException e)
		{
			
			e.printStackTrace();
		}

	}

	public Object getField(String field)
	{
		try
		{
			Field refField = this.getClass().getDeclaredField(field);
			return refField.get(this);
		} catch (NoSuchFieldException e)
		{
			
			e.printStackTrace();
		} catch (SecurityException e)
		{
			
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			
			e.printStackTrace();
		}
		return null;
	}

	public String getPartId()
	{
		return this.partId;
	}

	public int getMachineSize()
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

	public java.sql.Date getDateOfIssue()
	{
		return (java.sql.Date) this.dateOfIssue;
	}

	public String getSignOffBy()
	{
		return this.signOffBy;
	}

	public String getProcessNotes()
	{
		return this.processNotes;
	}

	public float getInjectionSpeed_1()
	{
		return this.injectionSpeed_1;
	}

	public float getInjectionSpeed_2()
	{
		return this.injectionSpeed_2;
	}

	public float getInjectionSpeed_3()
	{
		return this.injectionSpeed_3;
	}

	public float getInjectionSpeed_4()
	{
		return this.injectionSpeed_4;
	}

	public float getInjectionSpeed_5()
	{
		return this.injectionSpeed_5;
	}

	public float getInjectionSpeed_6()
	{
		return this.injectionSpeed_6;
	}

	public float getInjSpeedPosition_1()
	{
		return this.injSpeedPosition_1;
	}

	public float getInjSpeedPosition_2()
	{
		return this.injSpeedPosition_2;
	}

	public float getInjSpeedPosition_3()
	{
		return this.injSpeedPosition_3;
	}

	public float getInjSpeedPosition_4()
	{
		return this.injSpeedPosition_4;
	}

	public float getInjSpeedPosition_5()
	{
		return this.injSpeedPosition_5;
	}

	public float getInjSpeedPosition_6()
	{
		return this.injSpeedPosition_6;
	}

	public float getHoldingPressure_1()
	{
		return this.holdingPressure_1;
	}

	public float getHoldingPressure_2()
	{
		return this.holdingPressure_2;
	}

	public float getHoldingPressure_3()
	{
		return this.holdingPressure_3;
	}

	public float getHoldingPressure_4()
	{
		return this.holdingPressure_4;
	}

	public float getHoldingPressure_5()
	{
		return this.holdingPressure_5;
	}

	public float getHoldingPressure_6()
	{
		return this.holdingPressure_6;
	}

	public float getHoldingTime_1()
	{
		return this.holdingTime_1;
	}

	public float getHoldingTime_2()
	{
		return this.holdingTime_2;
	}

	public float getHoldingTime_3()
	{
		return this.holdingTime_3;
	}

	public float getHoldingTime_4()
	{
		return this.holdingTime_4;
	}

	public float getHoldingTime_5()
	{
		return this.holdingTime_5;
	}

	public float getHoldingTime_6()
	{
		return this.holdingTime_6;
	}

	public float getMaxPackVel()
	{
		return this.maxPackVel;
	}

	public float getPosTran()
	{
		return this.posTran;
	}

	public float getMaxInjPre()
	{
		return this.maxInjPre;
	}

	public float getMaxInjTime()
	{
		return this.maxInjTime;
	}

	public float getShotSize()
	{
		return this.shotSize;
	}

	public float getDecompressionVel()
	{
		return this.decompressionVel;
	}

	public float getCoolTime()
	{
		return this.coolTime;
	}

	public float getBackPressure_1()
	{
		return this.backPressure_1;
	}

	public float getBackPressure_2()
	{
		return this.backPressure_2;
	}

	public float getBackPressure_3()
	{
		return this.backPressure_3;
	}

	public float getBackPressure_4()
	{
		return this.backPressure_4;
	}

	public float getBackPressure_5()
	{
		return this.backPressure_5;
	}

	public float getBackPressure_6()
	{
		return this.backPressure_6;
	}

	public float getScrewExtSpeed_1()
	{
		return this.screwExtSpeed_1;
	}

	public float getScrewExtSpeed_2()
	{
		return this.screwExtSpeed_2;
	}

	public float getScrewExtSpeed_3()
	{
		return this.screwExtSpeed_3;
	}

	public float getScrewExtSpeed_4()
	{
		return this.screwExtSpeed_4;
	}

	public float getScrewExtSpeed_5()
	{
		return this.screwExtSpeed_5;
	}

	public float getScrewExtSpeed_6()
	{
		return this.screwExtSpeed_6;
	}

	public float getExtProfilePos_1()
	{
		return this.extProfilePos_1;
	}

	public float getExtProfilePos_2()
	{
		return this.extProfilePos_2;
	}

	public float getExtProfilePos_3()
	{
		return this.extProfilePos_3;
	}

	public float getExtProfilePos_4()
	{
		return this.extProfilePos_4;
	}

	public float getExtProfilePos_5()
	{
		return this.extProfilePos_5;
	}

	public float getExtProfilePos_6()
	{
		return this.extProfilePos_6;
	}

	public float getNozzelTemperature()
	{
		return this.nozzelTemperature;
	}

	public float getBarrelTemperature_1()
	{
		return this.barrelTemperature_1;
	}

	public float getBarrelTemperature_2()
	{
		return this.barrelTemperature_2;
	}

	public float getBarrelTemperature_3()
	{
		return this.barrelTemperature_3;
	}

	public float getBarrelTemperature_4()
	{
		return this.barrelTemperature_4;
	}

	public float getThroatTemperature()
	{
		return this.throatTemperature;
	}

	public float getMouldClosingOpenLimitPos()
	{
		return this.mouldClosingOpenLimitPos;
	}

	public float getMouldClosingOpenLimitSpeed()
	{
		return this.mouldClosingOpenLimitSpeed;
	}

	public float getMouldClosedLimitPos()
	{
		return this.mouldClosedLimitPos;
	}

	public float getMouldClosedLimitSpeed()
	{
		return this.mouldClosedLimitSpeed;
	}

	public float getClsSlowPos()
	{
		return this.clsSlowPos;
	}

	public float getClsSlowSpeed()
	{
		return this.clsSlowSpeed;
	}

	public float getMouldOpenBreakAwaySpeed()
	{
		return this.mouldOpenBreakAwaySpeed;
	}

	public float getMouldOpenStepPos_1()
	{
		return this.mouldOpenStepPos_1;
	}

	public float getMouldOpenStepSpeed_1()
	{
		return this.mouldOpenStepSpeed_1;
	}

	public float getMouldOpenStepPos_2()
	{
		return this.mouldOpenStepPos_2;
	}

	public float getMouldOpenStepSpeed_2()
	{
		return this.mouldOpenStepSpeed_2;
	}

	public float getMouldOpenStepPos_3()
	{
		return this.mouldOpenStepPos_3;
	}

	public float getMouldOpenStepSpeed_3()
	{
		return this.mouldOpenStepSpeed_3;
	}

	public float getMouldOpenTime()
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

	public float getEjectPulse()
	{
		return this.ejectPulse;
	}

	public float getEjectDelay()
	{
		return this.ejectDelay;
	}

	public float getEjectorsFwdPos()
	{
		return this.ejectorsFwdPos;
	}

	public float getEjectorsFwdSpeed()
	{
		return this.ejectorsFwdSpeed;
	}

	public float getEjectorsFwdTime()
	{
		return this.ejectorsFwdTime;
	}

	public float getEjectorsStopPos()
	{
		return this.ejectorsStopPos;
	}

	public float getEjectorsStopSpeed()
	{
		return this.ejectorsStopSpeed;
	}

	public float getEjectorsStopTime()
	{
		return this.ejectorsStopTime;
	}

	public float getEjectorsRevPos()
	{
		return this.ejectorsRevPos;
	}

	public float getEjectorsRevSpeed()
	{
		return this.ejectorsRevSpeed;
	}

	public float getEjectorsRevTime()
	{
		return this.ejectorsRevTime;
	}

	public float getDme_1()
	{
		return this.dme_1;
	}

	public float getDme_2()
	{
		return this.dme_2;
	}

	public float getDme_3()
	{
		return this.dme_3;
	}

	public float getDme_4()
	{
		return this.dme_4;
	}

	public float getDme_5()
	{
		return this.dme_5;
	}

	public float getDme_6()
	{
		return this.dme_6;
	}

	public float getDme_7()
	{
		return this.dme_7;
	}

	public float getDme_8()
	{
		return this.dme_8;
	}

	public float getWaterTempFixedHalf()
	{
		return this.waterTempFixedHalf;
	}

	public float getWaterTempMovingHalf()
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

	public void setMachineSize(int machineSize)
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

	public void setDateOfIssue(java.sql.Date dateOfIssue)
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

	public void setInjectionSpeed_1(float injectionSpeed_1)
	{
		this.injectionSpeed_1 = injectionSpeed_1;
		;
	}

	public void setInjectionSpeed_2(float injectionSpeed_2)
	{
		this.injectionSpeed_2 = injectionSpeed_2;
	}

	public void setInjectionSpeed_3(float injectionSpeed_3)
	{
		this.injectionSpeed_3 = injectionSpeed_3;
	}

	public void setInjectionSpeed_4(float injectionSpeed_4)
	{
		this.injectionSpeed_4 = injectionSpeed_4;
	}

	public void setInjectionSpeed_5(float injectionSpeed_5)
	{
		this.injectionSpeed_5 = injectionSpeed_5;
	}

	public void setInjectionSpeed_6(float injectionSpeed_6)
	{
		this.injectionSpeed_6 = injectionSpeed_6;
	}

	public void setInjSpeedPosition_1(float injSpeedPosition_1)
	{
		this.injSpeedPosition_1 = injSpeedPosition_1;
	}

	public void setInjSpeedPosition_2(float injSpeedPosition_2)
	{
		this.injSpeedPosition_2 = injSpeedPosition_2;
	}

	public void setInjSpeedPosition_3(float injSpeedPosition_3)
	{
		this.injSpeedPosition_3 = injSpeedPosition_3;
	}

	public void setInjSpeedPosition_4(float injSpeedPosition_4)
	{
		this.injSpeedPosition_4 = injSpeedPosition_4;
	}

	public void setInjSpeedPosition_5(float injSpeedPosition_5)
	{
		this.injSpeedPosition_5 = injSpeedPosition_5;
	}

	public void setInjSpeedPosition_6(float injSpeedPosition_6)
	{
		this.injSpeedPosition_6 = injSpeedPosition_6;
	}

	public void setHoldingPressure_1(float holdingPressure_1)
	{
		this.holdingPressure_1 = holdingPressure_1;
	}

	public void setHoldingPressure_2(float holdingPressure_2)
	{
		this.holdingPressure_2 = holdingPressure_2;
	}

	public void setHoldingPressure_3(float holdingPressure_3)
	{
		this.holdingPressure_3 = holdingPressure_3;
	}

	public void setHoldingPressure_4(float holdingPressure_4)
	{
		this.holdingPressure_4 = holdingPressure_4;
	}

	public void setHoldingPressure_5(float holdingPressure_5)
	{
		this.holdingPressure_5 = holdingPressure_5;
	}

	public void setHoldingPressure_6(float holdingPressure_6)
	{
		this.holdingPressure_6 = holdingPressure_6;
	}

	public void setHoldingTime_1(float holdingTime_1)
	{
		this.holdingTime_1 = holdingTime_1;
	}

	public void setHoldingTime_2(float holdingTime_2)
	{
		this.holdingTime_2 = holdingTime_2;
	}

	public void setHoldingTime_3(float holdingTime_3)
	{
		this.holdingTime_3 = holdingTime_3;
	}

	public void setHoldingTime_4(float holdingTime_4)
	{
		this.holdingTime_4 = holdingTime_4;
	}

	public void setHoldingTime_5(float holdingTime_5)
	{
		this.holdingTime_5 = holdingTime_5;
	}

	public void setHoldingTime_6(float holdingTime_6)
	{
		this.holdingTime_6 = holdingTime_6;
	}

	public void setMaxPackVel(float maxPackVel)
	{
		this.maxPackVel = maxPackVel;
	}

	public void setPosTran(float posTran)
	{
		this.posTran = posTran;
	}

	public void setMaxInjPre(float maxInjPre)
	{
		this.maxInjPre = maxInjPre;
	}

	public void setMaxInjTime(float maxInjTime)
	{
		this.maxInjTime = maxInjTime;
	}

	public void setShotSize(float shotSize)
	{
		this.shotSize = shotSize;
	}

	public void setDecompressionVel(float decompressionVel)
	{
		this.decompressionVel = decompressionVel;
	}

	public void setCoolTime(float coolTime)
	{
		this.coolTime = coolTime;
	}

	public void setBackPressure_1(float backPressure_1)
	{
		this.backPressure_1 = backPressure_1;
	}

	public void setBackPressure_2(float backPressure_2)
	{
		this.backPressure_2 = backPressure_2;
	}

	public void setBackPressure_3(float backPressure_3)
	{
		this.backPressure_3 = backPressure_3;
	}

	public void setBackPressure_4(float backPressure_4)
	{
		this.backPressure_4 = backPressure_4;
	}

	public void setBackPressure_5(float backPressure_5)
	{
		this.backPressure_5 = backPressure_5;
		;
	}

	public void setBackPressure_6(float backPressure_6)
	{
		this.backPressure_6 = backPressure_6;
		;
	}

	public void setScrewExtSpeed_1(float screwExtSpeed_1)
	{
		this.screwExtSpeed_1 = screwExtSpeed_1;
		;
	}

	public void setScrewExtSpeed_2(float screwExtSpeed_2)
	{
		this.screwExtSpeed_2 = screwExtSpeed_2;
		;
	}

	public void setScrewExtSpeed_3(float screwExtSpeed_3)
	{
		this.screwExtSpeed_3 = screwExtSpeed_3;
		;
	}

	public void setScrewExtSpeed_4(float screwExtSpeed_4)
	{
		this.screwExtSpeed_4 = screwExtSpeed_4;
		;
	}

	public void setScrewExtSpeed_5(float screwExtSpeed_5)
	{
		this.screwExtSpeed_5 = screwExtSpeed_5;
		;
	}

	public void setScrewExtSpeed_6(float screwExtSpeed_6)
	{
		this.screwExtSpeed_6 = screwExtSpeed_6;
		;
	}

	public void setExtProfilePos_1(float extProfilePos_1)
	{
		this.extProfilePos_1 = extProfilePos_1;
		;
	}

	public void setExtProfilePos_2(float extProfilePos_2)
	{
		this.extProfilePos_2 = extProfilePos_2;
		;
	}

	public void setExtProfilePos_3(float extProfilePos_3)
	{
		this.extProfilePos_3 = extProfilePos_3;
		;
	}

	public void setExtProfilePos_4(float extProfilePos_4)
	{
		this.extProfilePos_4 = extProfilePos_4;
		;
	}

	public void setExtProfilePos_5(float extProfilePos_5)
	{
		this.extProfilePos_5 = extProfilePos_5;
		;
	}

	public void setExtProfilePos_6(float extProfilePos_6)
	{
		this.extProfilePos_6 = extProfilePos_6;
		;
	}

	public void setNozzelTemperature(float nozzelTemperature)
	{
		this.nozzelTemperature = nozzelTemperature;
		;
	}

	public void setBarrelTemperature_1(float barrelTemperature_1)
	{
		this.barrelTemperature_1 = barrelTemperature_1;
		;
	}

	public void setBarrelTemperature_2(float barrelTemperature_2)
	{
		this.barrelTemperature_2 = barrelTemperature_2;
		;
	}

	public void setBarrelTemperature_3(float barrelTemperature_3)
	{
		this.barrelTemperature_3 = barrelTemperature_3;
		;
	}

	public void setBarrelTemperature_4(float barrelTemperature_4)
	{
		this.barrelTemperature_4 = barrelTemperature_4;
		;
	}

	public void setThroatTemperature(float throatTemperature)
	{
		this.throatTemperature = throatTemperature;
		;
	}

	public void setMouldClosingOpenLimitPos(float mouldClosingOpenLimitPos)
	{
		this.mouldClosingOpenLimitPos = mouldClosingOpenLimitPos;
		;
	}

	public void setMouldClosingOpenLimitSpeed(float mouldClosingOpenLimitSpeed)
	{
		this.mouldClosingOpenLimitSpeed = mouldClosingOpenLimitSpeed;
		;
	}

	public void setMouldClosedLimitPos(float mouldClosedLimitPos)
	{
		this.mouldClosedLimitPos = mouldClosedLimitPos;
		;
	}

	public void setMouldClosedLimitSpeed(float mouldClosedLimitSpeed)
	{
		this.mouldClosedLimitSpeed = mouldClosedLimitSpeed;
		;
	}

	public void setClsSlowPos(float clsSlowPos)
	{
		this.clsSlowPos = clsSlowPos;
		;
	}

	public void setClsSlowSpeed(float clsSlowSpeed)
	{
		this.clsSlowSpeed = clsSlowSpeed;
		;
	}

	public void setMouldOpenBreakAwaySpeed(float mouldOpenBreakAwaySpeed)
	{
		this.mouldOpenBreakAwaySpeed = mouldOpenBreakAwaySpeed;
		;
	}

	public void setMouldOpenStepPos_1(float mouldOpenStepPos_1)
	{
		this.mouldOpenStepPos_1 = mouldOpenStepPos_1;
		;
	}

	public void setMouldOpenStepSpeed_1(float mouldOpenStepSpeed_1)
	{
		this.mouldOpenStepSpeed_1 = mouldOpenStepSpeed_1;
		;
	}

	public void setMouldOpenStepPos_2(float mouldOpenStepPos_2)
	{
		this.mouldOpenStepPos_2 = mouldOpenStepPos_2;
		;
	}

	public void setMouldOpenStepSpeed_2(float mouldOpenStepSpeed_2)
	{
		this.mouldOpenStepSpeed_2 = mouldOpenStepSpeed_2;
		;
	}

	public void setMouldOpenStepPos_3(float mouldOpenStepPos_3)
	{
		this.mouldOpenStepPos_3 = mouldOpenStepPos_3;
		;
	}

	public void setMouldOpenStepSpeed_3(float mouldOpenStepSpeed_3)
	{
		this.mouldOpenStepSpeed_3 = mouldOpenStepSpeed_3;
		;
	}

	public void setMouldOpenTime(float mouldOpenTime)
	{
		this.mouldOpenTime = mouldOpenTime;
		;
	}

	public void setEjectStart(String ejectStart)
	{
		this.ejectStart = ejectStart;
		;
	}

	public void setEjectMode(String ejectMode)
	{
		this.ejectMode = ejectMode;
		;
	}

	public void setEjectPulse(float ejectPulse)
	{
		this.ejectPulse = ejectPulse;
		;
	}

	public void setEjectDelay(float ejectDelay)
	{
		this.ejectDelay = ejectDelay;
		;
	}

	public void setEjectorsFwdPos(float ejectorsFwdPos)
	{
		this.ejectorsFwdPos = ejectorsFwdPos;
		;
	}

	public void setEjectorsFwdSpeed(float ejectorsFwdSpeed)
	{
		this.ejectorsFwdSpeed = ejectorsFwdSpeed;
		;
	}

	public void setEjectorsFwdTime(float ejectorsFwdTime)
	{
		this.ejectorsFwdTime = ejectorsFwdTime;
		;
	}

	public void setEjectorsStopPos(float ejectorsStopPos)
	{
		this.ejectorsStopPos = ejectorsStopPos;
		;
	}

	public void setEjectorsStopSpeed(float ejectorsStopSpeed)
	{
		this.ejectorsStopSpeed = ejectorsStopSpeed;
		;
	}

	public void setEjectorsStopTime(float ejectorsStopTime)
	{
		this.ejectorsStopTime = ejectorsStopTime;
		;
	}

	public void setEjectorsRevPos(float ejectorsRevPos)
	{
		this.ejectorsRevPos = ejectorsRevPos;
		;
	}

	public void setEjectorsRevSpeed(float ejectorsRevSpeed)
	{
		this.ejectorsRevSpeed = ejectorsRevSpeed;
		;
	}

	public void setEjectorsRevTime(float ejectorsRevTime)
	{
		this.ejectorsRevTime = ejectorsRevTime;
		;
	}

	public void setDme_1(float dme_1)
	{
		this.dme_1 = dme_1;
		;
	}

	public void setDme_2(float dme_2)
	{
		this.dme_2 = dme_2;
		;
	}

	public void setDme_3(float dme_3)
	{
		this.dme_3 = dme_3;
		;
	}

	public void setDme_4(float dme_4)
	{
		this.dme_4 = dme_4;
		;
	}

	public void setDme_5(float dme_5)
	{
		this.dme_5 = dme_5;
		;
	}

	public void setDme_6(float dme_6)
	{
		this.dme_6 = dme_6;
		;
	}

	public void setDme_7(float dme_7)
	{
		this.dme_7 = dme_7;
		;
	}

	public void setDme_8(float dme_8)
	{
		this.dme_8 = dme_8;
		;
	}

	public void setWaterTempFixedHalf(float waterTempFixedHalf)
	{
		this.waterTempFixedHalf = waterTempFixedHalf;
		;
	}

	public void setWaterTempMovingHalf(float waterTempMovingHalf)
	{
		this.waterTempMovingHalf = waterTempMovingHalf;
		;
	}

	public void setWaterTempNotes(String waterTempNotes)
	{
		this.waterTempNotes = waterTempNotes;
		;
	}

	public float getClsSPPos()
	{
		return this.clsSPPos;
	}

	public float getClsSPSpeed()
	{
		return this.clsSPSpeed;
	}

	public void setClsSPPos(float clsSPPos)
	{
		this.clsSPPos = clsSPPos;
		;
	}

	public void setClsSPSpeed(float clsSPSpeed)
	{
		this.clsSPSpeed = clsSPSpeed;
		;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getDecompressionDist()
	{
		return decompressionDist;
	}

	public void setDecompressionDist(float decompressionDist)
	{
		this.decompressionDist = decompressionDist;
	}

	public String toString()
	{
		String result = "ProcessSheet:(";
		for (String field : fields)
		{
			result += ",(" + field + ":" + getField(field) + ")\n";
		}
		result += ")";
		return result;
	}

}
