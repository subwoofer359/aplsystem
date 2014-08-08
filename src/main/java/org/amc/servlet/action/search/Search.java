package org.amc.servlet.action.search;

import java.util.Set;

/**
 * Class that store information of a User's search parameters
 * @author Adrian McLaughlin
 *
 */
public interface Search
{
	public Set<SearchParameters> getFields();
}
