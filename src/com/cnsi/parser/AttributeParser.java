package com.cnsi.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.cnsi.xml.generator.CreateXML;

public class AttributeParser {

	private static final long serialVersionUID = 1L;
	
	public CreateXML createXML;

	public String findAttributes(XWPFTableRow tablerow, XWPFTable table, String stepName, String useCaseName){
		String zeroColVal = null;
		String firstColVal = null;
		String secColVal = null;
		String thirdColVal = null;
		String forColVal = null;
		String fifColVal = null;
		String sixColVal = null;
		String sevColVal = null;
		String eigColVal = null;
		String flowTitle = null;
		
		HashMap<String, String> tempMap = null;
		HashMap<String, ArrayList> hmp = new HashMap();

		ArrayList<String> arrFieldList = new ArrayList<String>();

		ArrayList<String> myList = null;// new ArrayList<String>();
		HashMap<String, HashMap> finalMap = new HashMap();
		BodyElementType bodyElementType = null;
		IBodyElement bodyElement = null;
		XWPFParagraph paragraph = null;
		List<XWPFTableCell> tableCells = null;
		List<String> xmlCol;
		List<String> xmlHeaders;
		tablerow = table.getRow(1);
		createXML = new CreateXML();
		StringBuilder builder = new StringBuilder();
		builder.append("\t\t\t<dataAttributes id=\""+useCaseName+"\" stepName=\""+stepName+"\">\n");
		
		if (tablerow != null) {
			tableCells = tablerow.getTableCells();
			if (tableCells.size() == 9) {
				zeroColVal = tableCells.get(0)
						.getText().trim();
				firstColVal = tableCells.get(1)
						.getText().trim();
				secColVal = tableCells.get(2).getText()
						.trim();
				thirdColVal = tableCells.get(3)
						.getText().trim();
				forColVal = tableCells.get(4).getText()
						.trim();
				fifColVal = tableCells.get(5).getText()
						.trim();
				sixColVal = tableCells.get(6).getText()
						.trim();
				sevColVal = tableCells.get(7).getText()
						.trim();
				eigColVal = tableCells.get(8).getText()
						.trim();
				xmlHeaders = new ArrayList<String>();
				xmlHeaders.add(zeroColVal);
				xmlHeaders.add(firstColVal);
				xmlHeaders.add(secColVal);
				xmlHeaders.add(thirdColVal);
				xmlHeaders.add(forColVal);
				xmlHeaders.add(fifColVal);
				xmlHeaders.add(sixColVal);
				xmlHeaders.add(sevColVal);
				xmlHeaders.add(eigColVal);
				builder.append(createXML.XMLHeaderGen(xmlHeaders).toString());
				
				
				//builder.append(createXML.XMLHeaderGen(xmlHeaders));
				
				if (("Field Name"
						.equalsIgnoreCase(zeroColVal)
						&& "Required (R, CR, O, n/a)"
								.equalsIgnoreCase(firstColVal)
						&& "Field Type"
								.equalsIgnoreCase(secColVal))||("Field Name"
										.equalsIgnoreCase(zeroColVal)
										&& "Required (R, CR, O,  n/a)"
												.equalsIgnoreCase(firstColVal)
										&& "Field Type"
												.equalsIgnoreCase(secColVal))) {
					for (int i = 2; i < table
							.getNumberOfRows(); i++) {

						tablerow = table.getRow(i);
						tableCells = tablerow
								.getTableCells();
						if (tableCells.size() == 9) {
							zeroColVal = tableCells
									.get(0).getText()
									.trim();
							// System.System.out.println("<zeroColVal>"+zeroColVal);
							firstColVal = tableCells
									.get(1).getText()
									.trim();
							secColVal = tableCells
									.get(2).getText()
									.trim();
							thirdColVal = tableCells
									.get(3).getText()
									.trim();
							forColVal = tableCells
									.get(4).getText()
									.trim();
							fifColVal = tableCells
									.get(5).getText()
									.trim();
							sixColVal = tableCells
									.get(6).getText()
									.trim();
							sevColVal = tableCells
									.get(7).getText()
									.trim();
							eigColVal = tableCells
									.get(8).getText()
									.trim();
							xmlCol = new ArrayList<String>();
							
							xmlCol.add(zeroColVal);
							xmlCol.add(firstColVal);
							xmlCol.add(secColVal);
							xmlCol.add(thirdColVal);
							xmlCol.add(forColVal);
							xmlCol.add(fifColVal);
							xmlCol.add(sixColVal);
							xmlCol.add(sevColVal);
							xmlCol.add(eigColVal);
							//createXML.XMLGen(xmlCol);
						builder.append(createXML.XMLGen(xmlCol, xmlHeaders));
									
						
						}
						
					
						
						
		}
					
	}
}
}			builder.append("\t\t\t</dataAttributes>\n");
		return builder.toString();

			
		
			
			
}
		

		
	
	public AttributeParser() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
