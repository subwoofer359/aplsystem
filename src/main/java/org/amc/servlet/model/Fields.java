package org.amc.servlet.model;

public enum Fields 
{
		ID, 					/** Identifier */
		COMPANY,				/** company for whom the part was created */
		NAME,	 				/** Product name */
		PRODUCT_ID,
		VERSION, 
		REVISION,
		COLOUR, 				/** colour of part */
		EXTERNAL, 				/** Is it an external part */
		QSS_NO,
		PART_ID,				/** JobTemplate Reference 			*/
		DATE,		 			/** Date of problem 					*/
		DEFECT_ID,				/** Defect Rerefence				*/
		AUTHORISED_BY,			/** who decided it was a problem 	*/
		PASSED, 				/** If the problem was allowed on 	*/
		PICTURE, 				/** URL to picture of part 			*/
		PROBLEM_ID, 			/** Defect related to */
		LOCATION,	 			/** location of defect on part */
		LEVEL_OF_VISIBILITLY, 	/** how visible is it */
		NOTE,
		//Workbook fields
		OPERATOR,				/** Operator on the machine */
		PATROL_CHECK,			/** Patrol check conducted  */
		PATROL_COMMENT,   
		SAMPLES,				/** Sample check results    */
		SAMPLE_COMMENT,   
		SHIFT,					/** Shift number 			*/
		QC,						/** QC on duty				*/
		MACHINE,				/** Machine 				*/
		TEMPLATE;
}