package org.amc.servlet.action.search;

/**
 * 
 * @author Adrian McLaughlin
 *
 */
public class MaterialSearch extends WebFormSearch
{
	public enum MaterialSearchFields implements SearchFields
	{
		COMPANY("company"),
		NAME("name"),
		TYPE("type");
		/**
		 * Database column name
		 */
		private String name;
		private MaterialSearchFields(String name)
		{
			this.name=name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
	
	/**
	 * @return the company
	 */
	public String getCompany()
	{
		
		return (getFieldMap().get(MaterialSearchFields.COMPANY)==null)?null:String.valueOf(getFieldMap().get(MaterialSearchFields.COMPANY));
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return (getFieldMap().get(MaterialSearchFields.NAME)==null)?null:String.valueOf(getFieldMap().get(MaterialSearchFields.NAME));
	}
	/**
	 * @return the type
	 */
	public String getType()
	{
		return (getFieldMap().get(MaterialSearchFields.TYPE)==null)?null:String.valueOf(getFieldMap().get(MaterialSearchFields.TYPE));
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company)
	{
		getFieldMap().put(MaterialSearchFields.COMPANY,company);
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		getFieldMap().put(MaterialSearchFields.NAME,name);
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		getFieldMap().put(MaterialSearchFields.TYPE,type);
	}
	
}
