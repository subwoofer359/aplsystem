package org.amc.model;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;

import javax.ejb.Stateful;

@Stateful
public class MouldingProcessBean implements MouldingProcessBeanRemote , Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2782845506401132766L;
	
	private int id;//Database ID
	//Top Header
	private String partId;
	private int machineSize;
	private String machineNo;
	private int material;
	private String masterbatchNo;
	private Date dateOfIssue;
	private String signOffBy;
	private String processNotes;
	
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
	private float decompressionDist;
	private float decompressionVel;
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
	
	//mouldClosing
	private float mouldClosingOpenLimitPos;
	private float mouldClosingOpenLimitSpeed;
	
	private float mouldClosedLimitPos;
	private float mouldClosedLimitSpeed;
	
	private float clsSlowPos;
	private float clsSlowSpeed;
	
	private float clsSPPos;
	private float clsSPSpeed;
	//mould opening
	
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
	
	
	public MouldingProcessBean()
	{

	}
	
	//Getters/Setters
	
	
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setField(java.lang.String, java.lang.Float)
	 */
	@Override
	public void setField(String field,Float f)
	{
		try {
			Field refField=this.getClass().getDeclaredField(field);
			refField.setFloat(this, f);
		} catch (NoSuchFieldException e) {
	
			e.printStackTrace();
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} 
		
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setField(java.lang.String, java.sql.Date)
	 */
	@Override
	public void setField(String field,java.sql.Date s)
	{
		try {
			Field refField=this.getClass().getDeclaredField(field);
			refField.set(this, s);
		} catch (NoSuchFieldException e) {
	
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
		
			e.printStackTrace();
		} catch (IllegalAccessException e) {
		
			e.printStackTrace();
		} 
		
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setField(java.lang.String, java.lang.String)
	 */
	@Override
	public void setField(String field,String s)
	{
		try {
			Field refField=this.getClass().getDeclaredField(field);
			refField.set(this, s);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setField(java.lang.String, java.lang.Integer)
	 */
	@Override
	public void setField(String field,Integer s)
	{
		try {
			Field refField=this.getClass().getDeclaredField(field);
			refField.setInt(this, s);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setField(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setField(String field,Object obj)
	{
		try {
			Field refField=this.getClass().getDeclaredField(field);
			refField.set(this, obj);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getField(java.lang.String)
	 */
	@Override
	public Object getField(String field)
	{
		try {
			Field refField=this.getClass().getDeclaredField(field);
			return refField.get(this);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	
	
	
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getPartId()
	 */
	@Override
	public String getPartId() {
		return this.partId;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMachineSize()
	 */
	@Override
	public int getMachineSize() {
		return this.machineSize;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMachineNo()
	 */
	@Override
	public String getMachineNo() {
		return this.machineNo;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMaterial()
	 */
	@Override
	public int getMaterial() {
		return this.material;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMasterbatchNo()
	 */
	@Override
	public String getMasterbatchNo() {
		return this.masterbatchNo;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDateOfIssue()
	 */
	@Override
	public java.sql.Date getDateOfIssue() {
		return (java.sql.Date)this.dateOfIssue;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getSignOffBy()
	 */
	@Override
	public String getSignOffBy() {
		return this.signOffBy;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getProcessNotes()
	 */
	@Override
	public String getProcessNotes() {
		return this.processNotes;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjectionSpeed_1()
	 */
	@Override
	public float getInjectionSpeed_1() {
		return this.injectionSpeed_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjectionSpeed_2()
	 */
	@Override
	public float getInjectionSpeed_2() {
		return this.injectionSpeed_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjectionSpeed_3()
	 */
	@Override
	public float getInjectionSpeed_3() {
		return this.injectionSpeed_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjectionSpeed_4()
	 */
	@Override
	public float getInjectionSpeed_4() {
		return this.injectionSpeed_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjectionSpeed_5()
	 */
	@Override
	public float getInjectionSpeed_5() {
		return this.injectionSpeed_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjectionSpeed_6()
	 */
	@Override
	public float getInjectionSpeed_6() {
		return this.injectionSpeed_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjSpeedPosition_1()
	 */
	@Override
	public float getInjSpeedPosition_1() {
		return this.injSpeedPosition_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjSpeedPosition_2()
	 */
	@Override
	public float getInjSpeedPosition_2() {
		return this.injSpeedPosition_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjSpeedPosition_3()
	 */
	@Override
	public float getInjSpeedPosition_3() {
		return this.injSpeedPosition_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjSpeedPosition_4()
	 */
	@Override
	public float getInjSpeedPosition_4() {
		return this.injSpeedPosition_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjSpeedPosition_5()
	 */
	@Override
	public float getInjSpeedPosition_5() {
		return this.injSpeedPosition_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getInjSpeedPosition_6()
	 */
	@Override
	public float getInjSpeedPosition_6() {
		return this.injSpeedPosition_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingPressure_1()
	 */
	@Override
	public float getHoldingPressure_1() {
		return this.holdingPressure_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingPressure_2()
	 */
	@Override
	public float getHoldingPressure_2() {
		return this.holdingPressure_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingPressure_3()
	 */
	@Override
	public float getHoldingPressure_3() {
		return this.holdingPressure_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingPressure_4()
	 */
	@Override
	public float getHoldingPressure_4() {
		return this.holdingPressure_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingPressure_5()
	 */
	@Override
	public float getHoldingPressure_5() {
		return this.holdingPressure_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingPressure_6()
	 */
	@Override
	public float getHoldingPressure_6() {
		return this.holdingPressure_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingTime_1()
	 */
	@Override
	public float getHoldingTime_1() {
		return this.holdingTime_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingTime_2()
	 */
	@Override
	public float getHoldingTime_2() {
		return this.holdingTime_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingTime_3()
	 */
	@Override
	public float getHoldingTime_3() {
		return this.holdingTime_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingTime_4()
	 */
	@Override
	public float getHoldingTime_4() {
		return this.holdingTime_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingTime_5()
	 */
	@Override
	public float getHoldingTime_5() {
		return this.holdingTime_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getHoldingTime_6()
	 */
	@Override
	public float getHoldingTime_6() {
		return this.holdingTime_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMaxPackVel()
	 */
	@Override
	public float getMaxPackVel() {
		return this.maxPackVel;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getPosTran()
	 */
	@Override
	public float getPosTran() {
		return this.posTran;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMaxInjPre()
	 */
	@Override
	public float getMaxInjPre() {
		return this.maxInjPre;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMaxInjTime()
	 */
	@Override
	public float getMaxInjTime() {
		return this.maxInjTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getShotSize()
	 */
	@Override
	public float getShotSize() {
		return this.shotSize;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDecompressionVel()
	 */
	@Override
	public float getDecompressionVel() {
		return this.decompressionVel;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getCoolTime()
	 */
	@Override
	public float getCoolTime() {
		return this.coolTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBackPressure_1()
	 */
	@Override
	public float getBackPressure_1() {
		return this.backPressure_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBackPressure_2()
	 */
	@Override
	public float getBackPressure_2() {
		return this.backPressure_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBackPressure_3()
	 */
	@Override
	public float getBackPressure_3() {
		return this.backPressure_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBackPressure_4()
	 */
	@Override
	public float getBackPressure_4() {
		return this.backPressure_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBackPressure_5()
	 */
	@Override
	public float getBackPressure_5() {
		return this.backPressure_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBackPressure_6()
	 */
	@Override
	public float getBackPressure_6() {
		return this.backPressure_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getScrewExtSpeed_1()
	 */
	@Override
	public float getScrewExtSpeed_1() {
		return this.screwExtSpeed_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getScrewExtSpeed_2()
	 */
	@Override
	public float getScrewExtSpeed_2() {
		return this.screwExtSpeed_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getScrewExtSpeed_3()
	 */
	@Override
	public float getScrewExtSpeed_3() {
		return this.screwExtSpeed_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getScrewExtSpeed_4()
	 */
	@Override
	public float getScrewExtSpeed_4() {
		return this.screwExtSpeed_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getScrewExtSpeed_5()
	 */
	@Override
	public float getScrewExtSpeed_5() {
		return this.screwExtSpeed_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getScrewExtSpeed_6()
	 */
	@Override
	public float getScrewExtSpeed_6() {
		return this.screwExtSpeed_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getExtProfilePos_1()
	 */
	@Override
	public float getExtProfilePos_1() {
		return this.extProfilePos_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getExtProfilePos_2()
	 */
	@Override
	public float getExtProfilePos_2() {
		return this.extProfilePos_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getExtProfilePos_3()
	 */
	@Override
	public float getExtProfilePos_3() {
		return this.extProfilePos_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getExtProfilePos_4()
	 */
	@Override
	public float getExtProfilePos_4() {
		return this.extProfilePos_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getExtProfilePos_5()
	 */
	@Override
	public float getExtProfilePos_5() {
		return this.extProfilePos_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getExtProfilePos_6()
	 */
	@Override
	public float getExtProfilePos_6() {
		return this.extProfilePos_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getNozzelTemperature()
	 */
	@Override
	public float getNozzelTemperature() {
		return this.nozzelTemperature;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBarrelTemperature_1()
	 */
	@Override
	public float getBarrelTemperature_1() {
		return this.barrelTemperature_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBarrelTemperature_2()
	 */
	@Override
	public float getBarrelTemperature_2() {
		return this.barrelTemperature_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBarrelTemperature_3()
	 */
	@Override
	public float getBarrelTemperature_3() {
		return this.barrelTemperature_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getBarrelTemperature_4()
	 */
	@Override
	public float getBarrelTemperature_4() {
		return this.barrelTemperature_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getThroatTemperature()
	 */
	@Override
	public float getThroatTemperature() {
		return this.throatTemperature;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldClosingOpenLimitPos()
	 */
	@Override
	public float getMouldClosingOpenLimitPos() {
		return this.mouldClosingOpenLimitPos;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldClosingOpenLimitSpeed()
	 */
	@Override
	public float getMouldClosingOpenLimitSpeed() {
		return this.mouldClosingOpenLimitSpeed;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldClosedLimitPos()
	 */
	@Override
	public float getMouldClosedLimitPos() {
		return this.mouldClosedLimitPos;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldClosedLimitSpeed()
	 */
	@Override
	public float getMouldClosedLimitSpeed() {
		return this.mouldClosedLimitSpeed;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getClsSlowPos()
	 */
	@Override
	public float getClsSlowPos() {
		return this.clsSlowPos;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getClsSlowSpeed()
	 */
	@Override
	public float getClsSlowSpeed() {
		return this.clsSlowSpeed;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenBreakAwaySpeed()
	 */
	@Override
	public float getMouldOpenBreakAwaySpeed() {
		return this.mouldOpenBreakAwaySpeed;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenStepPos_1()
	 */
	@Override
	public float getMouldOpenStepPos_1() {
		return this.mouldOpenStepPos_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenStepSpeed_1()
	 */
	@Override
	public float getMouldOpenStepSpeed_1() {
		return this.mouldOpenStepSpeed_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenStepPos_2()
	 */
	@Override
	public float getMouldOpenStepPos_2() {
		return this.mouldOpenStepPos_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenStepSpeed_2()
	 */
	@Override
	public float getMouldOpenStepSpeed_2() {
		return this.mouldOpenStepSpeed_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenStepPos_3()
	 */
	@Override
	public float getMouldOpenStepPos_3() {
		return this.mouldOpenStepPos_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenStepSpeed_3()
	 */
	@Override
	public float getMouldOpenStepSpeed_3() {
		return this.mouldOpenStepSpeed_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getMouldOpenTime()
	 */
	@Override
	public float getMouldOpenTime() {
		return this.mouldOpenTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectStart()
	 */
	@Override
	public String getEjectStart() {
		return this.ejectStart;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectMode()
	 */
	@Override
	public String getEjectMode() {
		return this.ejectMode;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectPulse()
	 */
	@Override
	public float getEjectPulse() {
		return this.ejectPulse;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectDelay()
	 */
	@Override
	public float getEjectDelay() {
		return this.ejectDelay;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsFwdPos()
	 */
	@Override
	public float getEjectorsFwdPos() {
		return this.ejectorsFwdPos;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsFwdSpeed()
	 */
	@Override
	public float getEjectorsFwdSpeed() {
		return this.ejectorsFwdSpeed;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsFwdTime()
	 */
	@Override
	public float getEjectorsFwdTime() {
		return this.ejectorsFwdTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsStopPos()
	 */
	@Override
	public float getEjectorsStopPos() {
		return this.ejectorsStopPos;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsStopSpeed()
	 */
	@Override
	public float getEjectorsStopSpeed() {
		return this.ejectorsStopSpeed;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsStopTime()
	 */
	@Override
	public float getEjectorsStopTime() {
		return this.ejectorsStopTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsRevPos()
	 */
	@Override
	public float getEjectorsRevPos() {
		return this.ejectorsRevPos;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsRevSpeed()
	 */
	@Override
	public float getEjectorsRevSpeed() {
		return this.ejectorsRevSpeed;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getEjectorsRevTime()
	 */
	@Override
	public float getEjectorsRevTime() {
		return this.ejectorsRevTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_1()
	 */
	@Override
	public float getDme_1() {
		return this.dme_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_2()
	 */
	@Override
	public float getDme_2() {
		return this.dme_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_3()
	 */
	@Override
	public float getDme_3() {
		return this.dme_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_4()
	 */
	@Override
	public float getDme_4() {
		return this.dme_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_5()
	 */
	@Override
	public float getDme_5() {
		return this.dme_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_6()
	 */
	@Override
	public float getDme_6() {
		return this.dme_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_7()
	 */
	@Override
	public float getDme_7() {
		return this.dme_7;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDme_8()
	 */
	@Override
	public float getDme_8() {
		return this.dme_8;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getWaterTempFixedHalf()
	 */
	@Override
	public float getWaterTempFixedHalf() {
		return this.waterTempFixedHalf;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getWaterTempMovingHalf()
	 */
	@Override
	public float getWaterTempMovingHalf() {
		return this.waterTempMovingHalf;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getWaterTempNotes()
	 */
	@Override
	public String getWaterTempNotes() {
		return this.waterTempNotes;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setPartId(java.lang.String)
	 */
	@Override
	public void setPartId(String partId) {
		this.partId=partId;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMachineSize(int)
	 */
	@Override
	public void setMachineSize(int machineSize) {
		this.machineSize=machineSize;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMachineNo(java.lang.String)
	 */
	@Override
	public void setMachineNo(String machineNo) {
		this.machineNo=machineNo;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMaterial(int)
	 */
	@Override
	public void setMaterial(int material) {
		this.material=material;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMasterbatchNo(java.lang.String)
	 */
	@Override
	public void setMasterbatchNo(String masterbatchNo) {
		this.masterbatchNo=masterbatchNo;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDateOfIssue(java.sql.Date)
	 */
	@Override
	public void setDateOfIssue(java.sql.Date dateOfIssue) {
		this.dateOfIssue=dateOfIssue;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setSignOffBy(java.lang.String)
	 */
	@Override
	public void setSignOffBy(String signOffBy) {
		this.signOffBy=signOffBy;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setProcessNotes(java.lang.String)
	 */
	@Override
	public void setProcessNotes(String processNotes) {
		this.processNotes=processNotes;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjectionSpeed_1(float)
	 */
	@Override
	public void setInjectionSpeed_1(float injectionSpeed_1) {
		this.injectionSpeed_1=injectionSpeed_1;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjectionSpeed_2(float)
	 */
	@Override
	public void setInjectionSpeed_2(float injectionSpeed_2) {
		this.injectionSpeed_2=injectionSpeed_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjectionSpeed_3(float)
	 */
	@Override
	public void setInjectionSpeed_3(float injectionSpeed_3) {
		this.injectionSpeed_3=injectionSpeed_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjectionSpeed_4(float)
	 */
	@Override
	public void setInjectionSpeed_4(float injectionSpeed_4) {
		this.injectionSpeed_4=injectionSpeed_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjectionSpeed_5(float)
	 */
	@Override
	public void setInjectionSpeed_5(float injectionSpeed_5) {
		this.injectionSpeed_5=injectionSpeed_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjectionSpeed_6(float)
	 */
	@Override
	public void setInjectionSpeed_6(float injectionSpeed_6) {
		this.injectionSpeed_6=injectionSpeed_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjSpeedPosition_1(float)
	 */
	@Override
	public void setInjSpeedPosition_1(float injSpeedPosition_1) {
		this.injSpeedPosition_1=injSpeedPosition_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjSpeedPosition_2(float)
	 */
	@Override
	public void setInjSpeedPosition_2(float injSpeedPosition_2) {
		this.injSpeedPosition_2=injSpeedPosition_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjSpeedPosition_3(float)
	 */
	@Override
	public void setInjSpeedPosition_3(float injSpeedPosition_3) {
		this.injSpeedPosition_3=injSpeedPosition_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjSpeedPosition_4(float)
	 */
	@Override
	public void setInjSpeedPosition_4(float injSpeedPosition_4) {
		this.injSpeedPosition_4=injSpeedPosition_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjSpeedPosition_5(float)
	 */
	@Override
	public void setInjSpeedPosition_5(float injSpeedPosition_5) {
		this.injSpeedPosition_5=injSpeedPosition_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setInjSpeedPosition_6(float)
	 */
	@Override
	public void setInjSpeedPosition_6(float injSpeedPosition_6) {
		this.injSpeedPosition_6=injSpeedPosition_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingPressure_1(float)
	 */
	@Override
	public void setHoldingPressure_1(float holdingPressure_1) {
		this.holdingPressure_1=holdingPressure_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingPressure_2(float)
	 */
	@Override
	public void setHoldingPressure_2(float holdingPressure_2) {
		this.holdingPressure_2=holdingPressure_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingPressure_3(float)
	 */
	@Override
	public void setHoldingPressure_3(float holdingPressure_3) {
		this.holdingPressure_3=holdingPressure_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingPressure_4(float)
	 */
	@Override
	public void setHoldingPressure_4(float holdingPressure_4) {
		this.holdingPressure_4=holdingPressure_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingPressure_5(float)
	 */
	@Override
	public void setHoldingPressure_5(float holdingPressure_5) {
		this.holdingPressure_5=holdingPressure_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingPressure_6(float)
	 */
	@Override
	public void setHoldingPressure_6(float holdingPressure_6) {
		this.holdingPressure_6=holdingPressure_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingTime_1(float)
	 */
	@Override
	public void setHoldingTime_1(float holdingTime_1) {
		this.holdingTime_1=holdingTime_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingTime_2(float)
	 */
	@Override
	public void setHoldingTime_2(float holdingTime_2) {
		this.holdingTime_2=holdingTime_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingTime_3(float)
	 */
	@Override
	public void setHoldingTime_3(float holdingTime_3) {
		this.holdingTime_3=holdingTime_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingTime_4(float)
	 */
	@Override
	public void setHoldingTime_4(float holdingTime_4) {
		this.holdingTime_4=holdingTime_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingTime_5(float)
	 */
	@Override
	public void setHoldingTime_5(float holdingTime_5) {
		this.holdingTime_5=holdingTime_5;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setHoldingTime_6(float)
	 */
	@Override
	public void setHoldingTime_6(float holdingTime_6) {
		this.holdingTime_6=holdingTime_6;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMaxPackVel(float)
	 */
	@Override
	public void setMaxPackVel(float maxPackVel) {
		this.maxPackVel=maxPackVel;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setPosTran(float)
	 */
	@Override
	public void setPosTran(float posTran) {
		this.posTran=posTran;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMaxInjPre(float)
	 */
	@Override
	public void setMaxInjPre(float maxInjPre) {
		this.maxInjPre=maxInjPre;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMaxInjTime(float)
	 */
	@Override
	public void setMaxInjTime(float maxInjTime) {
		this.maxInjTime=maxInjTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setShotSize(float)
	 */
	@Override
	public void setShotSize(float shotSize) {
		this.shotSize=shotSize;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDecompressionVel(float)
	 */
	@Override
	public void setDecompressionVel(float decompressionVel) {
		this.decompressionVel=decompressionVel;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setCoolTime(float)
	 */
	@Override
	public void setCoolTime(float coolTime) {
		this.coolTime=coolTime;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBackPressure_1(float)
	 */
	@Override
	public void setBackPressure_1(float backPressure_1) {
		this.backPressure_1=backPressure_1;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBackPressure_2(float)
	 */
	@Override
	public void setBackPressure_2(float backPressure_2) {
		this.backPressure_2=backPressure_2;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBackPressure_3(float)
	 */
	@Override
	public void setBackPressure_3(float backPressure_3) {
		this.backPressure_3=backPressure_3;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBackPressure_4(float)
	 */
	@Override
	public void setBackPressure_4(float backPressure_4) {
		this.backPressure_4=backPressure_4;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBackPressure_5(float)
	 */
	@Override
	public void setBackPressure_5(float backPressure_5) {
		this.backPressure_5=backPressure_5;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBackPressure_6(float)
	 */
	@Override
	public void setBackPressure_6(float backPressure_6) {
		this.backPressure_6=backPressure_6;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setScrewExtSpeed_1(float)
	 */
	@Override
	public void setScrewExtSpeed_1(float screwExtSpeed_1) {
		this.screwExtSpeed_1=screwExtSpeed_1;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setScrewExtSpeed_2(float)
	 */
	@Override
	public void setScrewExtSpeed_2(float screwExtSpeed_2) {
		this.screwExtSpeed_2=screwExtSpeed_2;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setScrewExtSpeed_3(float)
	 */
	@Override
	public void setScrewExtSpeed_3(float screwExtSpeed_3) {
		this.screwExtSpeed_3=screwExtSpeed_3;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setScrewExtSpeed_4(float)
	 */
	@Override
	public void setScrewExtSpeed_4(float screwExtSpeed_4) {
		this.screwExtSpeed_4=screwExtSpeed_4;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setScrewExtSpeed_5(float)
	 */
	@Override
	public void setScrewExtSpeed_5(float screwExtSpeed_5) {
		this.screwExtSpeed_5=screwExtSpeed_5;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setScrewExtSpeed_6(float)
	 */
	@Override
	public void setScrewExtSpeed_6(float screwExtSpeed_6) {
		this.screwExtSpeed_6=screwExtSpeed_6;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setExtProfilePos_1(float)
	 */
	@Override
	public void setExtProfilePos_1(float extProfilePos_1) {
		this.extProfilePos_1=extProfilePos_1;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setExtProfilePos_2(float)
	 */
	@Override
	public void setExtProfilePos_2(float extProfilePos_2) {
		this.extProfilePos_2=extProfilePos_2;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setExtProfilePos_3(float)
	 */
	@Override
	public void setExtProfilePos_3(float extProfilePos_3) {
		this.extProfilePos_3=extProfilePos_3;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setExtProfilePos_4(float)
	 */
	@Override
	public void setExtProfilePos_4(float extProfilePos_4) {
		this.extProfilePos_4=extProfilePos_4;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setExtProfilePos_5(float)
	 */
	@Override
	public void setExtProfilePos_5(float extProfilePos_5) {
		this.extProfilePos_5=extProfilePos_5;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setExtProfilePos_6(float)
	 */
	@Override
	public void setExtProfilePos_6(float extProfilePos_6) {
		this.extProfilePos_6=extProfilePos_6;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setNozzelTemperature(float)
	 */
	@Override
	public void setNozzelTemperature(float nozzelTemperature) {
		this.nozzelTemperature=nozzelTemperature;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBarrelTemperature_1(float)
	 */
	@Override
	public void setBarrelTemperature_1(float barrelTemperature_1) {
		this.barrelTemperature_1=barrelTemperature_1;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBarrelTemperature_2(float)
	 */
	@Override
	public void setBarrelTemperature_2(float barrelTemperature_2) {
		this.barrelTemperature_2=barrelTemperature_2;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBarrelTemperature_3(float)
	 */
	@Override
	public void setBarrelTemperature_3(float barrelTemperature_3) {
		this.barrelTemperature_3=barrelTemperature_3;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setBarrelTemperature_4(float)
	 */
	@Override
	public void setBarrelTemperature_4(float barrelTemperature_4) {
		this.barrelTemperature_4=barrelTemperature_4;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setThroatTemperature(float)
	 */
	@Override
	public void setThroatTemperature(float throatTemperature) {
		this.throatTemperature=throatTemperature;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldClosingOpenLimitPos(float)
	 */
	@Override
	public void setMouldClosingOpenLimitPos(float mouldClosingOpenLimitPos) {
		this.mouldClosingOpenLimitPos=mouldClosingOpenLimitPos;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldClosingOpenLimitSpeed(float)
	 */
	@Override
	public void setMouldClosingOpenLimitSpeed(float mouldClosingOpenLimitSpeed) {
		this.mouldClosingOpenLimitSpeed=mouldClosingOpenLimitSpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldClosedLimitPos(float)
	 */
	@Override
	public void setMouldClosedLimitPos(float mouldClosedLimitPos) {
		this.mouldClosedLimitPos=mouldClosedLimitPos;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldClosedLimitSpeed(float)
	 */
	@Override
	public void setMouldClosedLimitSpeed(float mouldClosedLimitSpeed) {
		this.mouldClosedLimitSpeed=mouldClosedLimitSpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setClsSlowPos(float)
	 */
	@Override
	public void setClsSlowPos(float clsSlowPos) {
		this.clsSlowPos=clsSlowPos;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setClsSlowSpeed(float)
	 */
	@Override
	public void setClsSlowSpeed(float clsSlowSpeed) {
		this.clsSlowSpeed=clsSlowSpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenBreakAwaySpeed(float)
	 */
	@Override
	public void setMouldOpenBreakAwaySpeed(float mouldOpenBreakAwaySpeed) {
		this.mouldOpenBreakAwaySpeed=mouldOpenBreakAwaySpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenStepPos_1(float)
	 */
	@Override
	public void setMouldOpenStepPos_1(float mouldOpenStepPos_1) {
		this.mouldOpenStepPos_1=mouldOpenStepPos_1;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenStepSpeed_1(float)
	 */
	@Override
	public void setMouldOpenStepSpeed_1(float mouldOpenStepSpeed_1) {
		this.mouldOpenStepSpeed_1=mouldOpenStepSpeed_1;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenStepPos_2(float)
	 */
	@Override
	public void setMouldOpenStepPos_2(float mouldOpenStepPos_2) {
		this.mouldOpenStepPos_2=mouldOpenStepPos_2;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenStepSpeed_2(float)
	 */
	@Override
	public void setMouldOpenStepSpeed_2(float mouldOpenStepSpeed_2) {
		this.mouldOpenStepSpeed_2=mouldOpenStepSpeed_2;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenStepPos_3(float)
	 */
	@Override
	public void setMouldOpenStepPos_3(float mouldOpenStepPos_3) {
		this.mouldOpenStepPos_3=mouldOpenStepPos_3;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenStepSpeed_3(float)
	 */
	@Override
	public void setMouldOpenStepSpeed_3(float mouldOpenStepSpeed_3) {
		this.mouldOpenStepSpeed_3=mouldOpenStepSpeed_3;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setMouldOpenTime(float)
	 */
	@Override
	public void setMouldOpenTime(float mouldOpenTime) {
		this.mouldOpenTime=mouldOpenTime;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectStart(java.lang.String)
	 */
	@Override
	public void setEjectStart(String ejectStart) {
		this.ejectStart=ejectStart;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectMode(java.lang.String)
	 */
	@Override
	public void setEjectMode(String ejectMode) {
		this.ejectMode=ejectMode;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectPulse(float)
	 */
	@Override
	public void setEjectPulse(float ejectPulse) {
		this.ejectPulse=ejectPulse;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectDelay(float)
	 */
	@Override
	public void setEjectDelay(float ejectDelay) {
		this.ejectDelay=ejectDelay;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsFwdPos(float)
	 */
	@Override
	public void setEjectorsFwdPos(float ejectorsFwdPos) {
		this.ejectorsFwdPos=ejectorsFwdPos;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsFwdSpeed(float)
	 */
	@Override
	public void setEjectorsFwdSpeed(float ejectorsFwdSpeed) {
		this.ejectorsFwdSpeed=ejectorsFwdSpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsFwdTime(float)
	 */
	@Override
	public void setEjectorsFwdTime(float ejectorsFwdTime) {
		this.ejectorsFwdTime=ejectorsFwdTime;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsStopPos(float)
	 */
	@Override
	public void setEjectorsStopPos(float ejectorsStopPos) {
		this.ejectorsStopPos=ejectorsStopPos;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsStopSpeed(float)
	 */
	@Override
	public void setEjectorsStopSpeed(float ejectorsStopSpeed) {
		this.ejectorsStopSpeed=ejectorsStopSpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsStopTime(float)
	 */
	@Override
	public void setEjectorsStopTime(float ejectorsStopTime) {
		this.ejectorsStopTime=ejectorsStopTime;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsRevPos(float)
	 */
	@Override
	public void setEjectorsRevPos(float ejectorsRevPos) {
		this.ejectorsRevPos=ejectorsRevPos;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsRevSpeed(float)
	 */
	@Override
	public void setEjectorsRevSpeed(float ejectorsRevSpeed) {
		this.ejectorsRevSpeed=ejectorsRevSpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setEjectorsRevTime(float)
	 */
	@Override
	public void setEjectorsRevTime(float ejectorsRevTime) {
		this.ejectorsRevTime=ejectorsRevTime;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_1(float)
	 */
	@Override
	public void setDme_1(float dme_1) {
		this.dme_1=dme_1;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_2(float)
	 */
	@Override
	public void setDme_2(float dme_2) {
		this.dme_2=dme_2;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_3(float)
	 */
	@Override
	public void setDme_3(float dme_3) {
		this.dme_3=dme_3;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_4(float)
	 */
	@Override
	public void setDme_4(float dme_4) {
		this.dme_4=dme_4;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_5(float)
	 */
	@Override
	public void setDme_5(float dme_5) {
		this.dme_5=dme_5;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_6(float)
	 */
	@Override
	public void setDme_6(float dme_6) {
		this.dme_6=dme_6;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_7(float)
	 */
	@Override
	public void setDme_7(float dme_7) {
		this.dme_7=dme_7;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDme_8(float)
	 */
	@Override
	public void setDme_8(float dme_8) {
		this.dme_8=dme_8;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setWaterTempFixedHalf(float)
	 */
	@Override
	public void setWaterTempFixedHalf(float waterTempFixedHalf) {
		this.waterTempFixedHalf=waterTempFixedHalf;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setWaterTempMovingHalf(float)
	 */
	@Override
	public void setWaterTempMovingHalf(float waterTempMovingHalf) {
		this.waterTempMovingHalf=waterTempMovingHalf;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setWaterTempNotes(java.lang.String)
	 */
	@Override
	public void setWaterTempNotes(String waterTempNotes) {
		this.waterTempNotes=waterTempNotes;;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getClsSPPos()
	 */
	@Override
	public float getClsSPPos() {
		return this.clsSPPos;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getClsSPSpeed()
	 */
	@Override
	public float getClsSPSpeed() {
		return this.clsSPSpeed;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setClsSPPos(float)
	 */
	@Override
	public void setClsSPPos(float clsSPPos) {
		this.clsSPPos=clsSPPos;;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setClsSPSpeed(float)
	 */
	@Override
	public void setClsSPSpeed(float clsSPSpeed) {
		this.clsSPSpeed=clsSPSpeed;;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getId()
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id=id;
	}
	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#getDecompressionDist()
	 */
	@Override
	public float getDecompressionDist() {
		return decompressionDist;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MouldProcessBeanRemote#setDecompressionDist(float)
	 */
	@Override
	public void setDecompressionDist(float decompressionDist) {
		this.decompressionDist = decompressionDist;
	}
	
	@Override
	public String toString()
	{
		String result="ProcessSheet:(";
		for(String field:fields)
		{
			result+=",("+field+":"+getField(field)+")\n";
		}
		result+=")";
		return result;
	}
		
}
