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

public class OtherUseCaseParser {

	public CreateXML createXML;

	public String useCaseParserOther(XWPFTableRow tablerow, XWPFTable table, String useCaseName) {

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
		createXML = new CreateXML();
		StringBuilder stb = new StringBuilder();

		if (tablerow != null) {
			tableCells = tablerow.getTableCells();

			if (tableCells.size() == 3) {
				zeroColVal = tableCells.get(0).getText().trim();
				firstColVal = tableCells.get(1).getText().trim();
				secColVal = tableCells.get(2).getText().trim();
				//thirdColVal = tableCells.get(3).getText().trim();

				xmlHeaders = new ArrayList<String>();
				xmlHeaders.add(zeroColVal);
				xmlHeaders.add(firstColVal);
				xmlHeaders.add(secColVal);
				//xmlHeaders.add(thirdColVal);

				stb.append(createXML.XMLOtherUseCase(xmlHeaders, useCaseName));

				// builder.append(createXML.XMLHeaderGen(xmlHeaders));

				if ("Use Case Number/Name".equalsIgnoreCase(zeroColVal)
						&& ("Detailed System Design (DSD)where this Use Case is Included".equalsIgnoreCase(firstColVal))||("DSD where this Use Case is Included".equalsIgnoreCase(firstColVal))
						&& "Purpose of Including the Use Case".equalsIgnoreCase(secColVal)) {

					for (int i = 1; i < table.getNumberOfRows(); i++) {

						tablerow = table.getRow(i);
						tableCells = tablerow.getTableCells();
						if (tableCells.size() == 3) {
							zeroColVal = tableCells.get(0).getText().trim();
							// System.System.out.println("<zeroColVal>"+zeroColVal);
							firstColVal = tableCells.get(1).getText().trim();
							secColVal = tableCells.get(2).getText().trim();
							//thirdColVal = tableCells.get(3).getText().trim();

							xmlCol = new ArrayList<String>();

							xmlCol.add(zeroColVal);
							xmlCol.add(firstColVal);
							xmlCol.add(secColVal);
						//	xmlCol.add(thirdColVal);

							// createXML.XMLGen(xmlCol);
							stb.append((createXML.XMLOtherUseCaseRows(xmlCol)));

						}

					}

				}
			}/*
			 * else if(tableCells.size()== 4) {
			 * stBuilder.append("<Row>"+tableCells.get(0)+"</Row>"); }
			 */
		} /* System.out.println(stBuilder.append(stb)); */

		// stBuilder.append("</Attributes>");
		// System.out.println("Final XML ------------->>>>>>>>>>>");
		// System.out.println(stBuilder.toString());
		stb.append("\t\t</otherUseCase>\n");
		return stb.toString();

	}

}
