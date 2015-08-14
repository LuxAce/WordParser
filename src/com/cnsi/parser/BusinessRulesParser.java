package com.cnsi.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.cnsi.xml.generator.CreateXML;

public class BusinessRulesParser {

	public CreateXML createXML;

	public String bizParser(XWPFTableRow tablerow, XWPFTable table,
			String useCaseName, String stepName) {
		String zeroColVal = null;
		String zeroNewColVal = null;
		String firstColVal = null;
		String secColVal = null;
		String thirdColVal = null;
		String forColVal = null;
		String fifColVal = null;
		String sixColVal = null;
		String sevColVal = null;
		String eigColVal = null;
		

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
		builder.append("\t\t\t<businessAreaRules id=\"" + useCaseName + "\" stepName=\""+stepName+"\">\n");

		if (tablerow != null) {
			tableCells = tablerow.getTableCells();
			if (tableCells.size() == 3) {
				zeroColVal = tableCells.get(0).getText().trim();
				firstColVal = tableCells.get(1).getText().trim();
				secColVal = tableCells.get(2).getText().trim();

				xmlHeaders = new ArrayList<String>();
				xmlHeaders.add(zeroColVal);
				xmlHeaders.add(firstColVal);
				xmlHeaders.add(secColVal);

				builder.append(createXML.XMLBusinessHeader(xmlHeaders)
						.toString());

				// builder.append(createXML.XMLHeaderGen(xmlHeaders));

				if ("Business Rules".equalsIgnoreCase(zeroColVal)
						&& "Business Error Displayed"
								.equalsIgnoreCase(firstColVal)
						&& "Applied At".equalsIgnoreCase(secColVal)) {

					for (int i = 2; i < table.getNumberOfRows(); i++) {

						tablerow = table.getRow(i);
						tableCells = tablerow.getTableCells();
						if (tableCells.size() == 3) {
							zeroColVal = tableCells.get(0).getText().trim();

							// System.out.println("SIZE--->"+tableCells.get(0).getBodyElements().size());
							if (tableCells.get(0).getBodyElements().size() > 1) {
								for (int k = 1; k < tableCells.get(0)
										.getBodyElements().size(); k++) {
									BodyElementType bodyElementType2 = tableCells
											.get(0).getBodyElements().get(k)
											.getElementType();
									
								 if (bodyElementType2 == BodyElementType.PARAGRAPH) {
										XWPFParagraph paragraph2 = (XWPFParagraph) tableCells.get(0).getBodyElements().get(k);
										zeroColVal = zeroColVal+ paragraph2.getText();
										
									}
								 if (bodyElementType2 == BodyElementType.TABLE) {
									 zeroColVal = zeroColVal
												+ "[Complex]";
										
									}
								}
							} else {
								zeroNewColVal = zeroColVal;
							}
							// System.System.out.println("<zeroColVal>"+zeroColVal);
							firstColVal = tableCells.get(1).getText().trim();
							secColVal = tableCells.get(2).getText().trim();

							xmlCol = new ArrayList<String>();

							xmlCol.add(zeroColVal);
							xmlCol.add(firstColVal);
							xmlCol.add(secColVal);

							// createXML.XMLGen(xmlCol);
							builder.append(createXML.XMLBusiness(xmlCol,
									xmlHeaders));

						}

					}

				}
			}
		}
		builder.append("\t\t\t</businessAreaRules>\n");
		return builder.toString();

	}
}
