package com.cnsi.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.w3c.dom.Document;

import com.cnsi.xml.generator.CreateXML;

public class DocumentParser {

	public CreateXML createXML;
	public DocumentBuilder dom;
	public StringBuilder builder = new StringBuilder();

	public void docParser() throws ParserConfigurationException,
			TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		String subsystemName = "";
		String subName = "ARCH";
		String extensionRemovedFileName = "";

		File folder = null;

		FileWriter fw = null;
		BufferedWriter bw = null;
		FileInputStream fis = null;
		XWPFDocument xdoc = null;
		Iterator<XWPFTable> tableIterator = null;
		XWPFTable table = null;
		List<XWPFTableRow> tableRows = null;
		XWPFTableRow tablerow = null;
		List<XWPFTableCell> tableCells = null;

		try {

			folder = new File("c:\\test2\\");

			File[] listOfFiles = folder.listFiles();

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
			String stepName = null;

			HashMap<String, String> tempMap = null;
			HashMap<String, ArrayList> hmp = new HashMap();

			ArrayList<String> arrFieldList = new ArrayList<String>();

			ArrayList<String> myList = null;

			HashMap<String, HashMap> finalMap = new HashMap();
			BodyElementType bodyElementType = null;
			IBodyElement bodyElement = null;
			XWPFParagraph paragraph = null;
			String useCaseName = null;
		

			for (File file : listOfFiles) {

				if (file.isFile()) {

					String fileName = file.getName();
					extensionRemovedFileName = fileName.split("\\.")[0];
					System.out.println(extensionRemovedFileName);
					fis = new FileInputStream(file.getAbsolutePath());

					xdoc = new XWPFDocument(OPCPackage.open(fis));
					List<IBodyElement> bodyElements = xdoc.getBodyElements();
					List<String> xmlCol;
					List<String> xmlHeaders;
					createXML = new CreateXML();
					for (int a = 0; a < bodyElements.size(); a++) {

						bodyElement = bodyElements.get(a);

						bodyElementType = bodyElement.getElementType();

						if (bodyElementType == BodyElementType.PARAGRAPH) {

							paragraph = (XWPFParagraph) bodyElement;
							if (paragraph.getText().startsWith(
									"Use Case – R3-" + subName + "-")
									|| paragraph.getText().startsWith(
											"Use Case R3–" + subName + "-")
									|| paragraph.getText().startsWith(
											"Use Case - R3-" + subName + "-")
									|| paragraph.getText().startsWith(
											"Use Case R3-" + subName + "-")

							)

							{
								if (builder.length() != 0) {
									builder.append("\t</useCase>\n");
								} else if (builder.length() == 0) {
									builder.append("<?xml version=" + "\"1.0\""
											+ " encoding=" + "\"UTF-8\""
											+ "?>\n");
									builder.append("<uttmisDoc>\n");
								}
								builder.append("\t<useCase>\n");
								useCaseName = paragraph.getText();
								String useCaseXML = createXML
										.XMLUseCaseNameGen(useCaseName
												.toString());
								builder.append(useCaseXML);

							} else if ((paragraph.getText()
									.startsWith("Actors, Triggers, Pre-Conditions, Post-Conditions, and Constraints"))
									|| paragraph
											.getText()
											.startsWith(
													"Actors, Triggers, Pre-Conditions, Post Conditions, and Constraints")) {
								int temp = a;
								bodyElement = bodyElements.get(temp + 1);
								bodyElementType = bodyElement.getElementType();
								if (bodyElementType == BodyElementType.TABLE) {
									table = (XWPFTable) bodyElement;
									List<String> actorList = new ArrayList<String>();
									for (int i = 0; i < table.getNumberOfRows(); i++) {
										tablerow = table.getRow(i);
										tableCells = tablerow.getTableCells();
										if (tableCells.size() == 2) {
											zeroColVal = tableCells.get(0)
													.getText().trim();
											firstColVal = tableCells.get(1)
													.getText().trim();

											actorList.add(firstColVal);
										}
									}
									builder.append(createXML.XMLActorGen(
											actorList, useCaseName));
								}
							}

							else if ((paragraph.getText()
									.startsWith("Other Use Case Invoked "))
									|| (paragraph.getText()
											.startsWith("Other Use Case Invoked"))) {
								int temp = a;
								bodyElement = bodyElements.get(temp + 1);
								bodyElementType = bodyElement.getElementType();
								if (bodyElementType == BodyElementType.TABLE) {
									table = (XWPFTable) bodyElement;
									tablerow = table.getRow(0);
									tableCells = tablerow.getTableCells();

									builder.append(new OtherUseCaseParser()
											.useCaseParserOther(tablerow,
													table, useCaseName));

								}
							} else if (paragraph.getText().startsWith("Step ")
									|| (paragraph.getText().startsWith(" Step"))
									|| (paragraph.getText().startsWith("Step"))) {
								int temp = a;

								builder.append("\t\t<step id=\"" + useCaseName
										+ "\" type=\"normal\">");

								bodyElement = bodyElements.get(temp + 2);
								bodyElementType = bodyElement.getElementType();
								if (bodyElementType == BodyElementType.TABLE) {
									table = (XWPFTable) bodyElement;
									flowTitle = table.getRow(0).getTableCells()
											.get(0).getText();
									stepName = flowTitle;
									builder.append("\n\t\t\t<stepName>"
											+ flowTitle + "</stepName>\n");
									if (flowTitle.startsWith("Step")) {
										tablerow = table.getRow(1);
										if ((tablerow.getCell(0).getText()
												.contains("Navigation: "))
												|| (tablerow.getCell(0)
														.getText()
														.contains("Navigation:"))
												|| (tablerow.getCell(0)
														.getText()
														.contains("Navigation"))) {
											builder.append("\t\t\t<navigation>"
													+ tablerow.getCell(1)
															.getText()
													+ "</navigation>\n");
											builder.append("\t\t\t<flow id=\""
													+ useCaseName
													+ "\" stepId=\""
													+ flowTitle + "\">\n");
											ApplicationParser applicationParser = new ApplicationParser();
											tablerow = table.getRow(3);
											builder.append(applicationParser
													.appParser(tablerow, table));
											builder.append("\t\t\t</flow>\n");
											// }
										}

									}

								}

							} else if (paragraph.getText().contains(
									"Business Area Rules")) {
								int temp = a;
								bodyElement = bodyElements.get(temp + 1);
								bodyElementType = bodyElement.getElementType();
								if (bodyElementType == BodyElementType.TABLE) {
									table = (XWPFTable) bodyElement;
									if (table.getRow(0).getTableCells().get(0)
											.getText()
											.contains("Business Area Rules")) {
										tablerow = table.getRow(1);
										BusinessRulesParser businessRulesParser = new BusinessRulesParser();
										builder.append(businessRulesParser
												.bizParser(tablerow, table,
														useCaseName, stepName));
									}
								}
							}

							else if (paragraph.getText().startsWith(
									"Data Attributes")) {
								int temp = a;
								bodyElement = bodyElements.get(temp + 1);
								bodyElementType = bodyElement.getElementType();
								if ((builder.substring((builder.length() - 11),
										(builder.length()))).toString()
										.equalsIgnoreCase("\n\t\t</step>\n")) {
									builder.delete((builder.length() - 13),
											(builder.length()));
									builder.append("s>\n");
								}
								
								if (bodyElementType == BodyElementType.TABLE) {
									table = (XWPFTable) bodyElement;
									flowTitle = table.getRow(0).getTableCells()
											.get(0).getText();
									if ((flowTitle
											.startsWith("Data Attributes"))
											|| (flowTitle
													.contains("Data Attributes"))) {
										if ((builder.substring((builder.length() -34),
												(builder.length()))).toString()
												.contains("\t\t\t</alternateDataAttributes>")){
											dataAttributesCheck(bodyElement);
										}else{
										tablerow = table.getRow(1);
										AttributeParser attributeParser = new AttributeParser();
										builder.append(attributeParser
												.findAttributes(tablerow,
														table, stepName,
														useCaseName));
										builder.append("\t\t</step>\n");
										}}
								} else if (bodyElementType == BodyElementType.PARAGRAPH) {
									XWPFParagraph paragraph2 = (XWPFParagraph) bodyElement;
									builder.append("\t\t\t<dataAttributes>");
									builder.append(paragraph2.getText());
									builder.append("</dataAttributes>\n");
									builder.append("\t\t</step>\n");
								}

							}

							else if ((paragraph.getText()
									.startsWith("Alternate Flow"))
									|| (paragraph.getText()
											.startsWith(" Alternate Flow"))
									|| (paragraph.getText()
											.startsWith("Alternate Flow "))
									|| (paragraph.getText()
											.contains("Alternate Flow "))) {
								String alternateFlowName = paragraph.getText();
								builder.append("\t\t<step type=\"alternate\">\n\t\t\t<alternateFlowName>"
										+ alternateFlowName
										+ "</alternateFlowName>\n");

								int temp = a;
								for (int k = temp; k < bodyElements.size(); k++) {
									bodyElement = bodyElements.get(k);
									if (bodyElement.getElementType() == bodyElementType.TABLE) {
										table = (XWPFTable) bodyElement;
										flowTitle = table.getRow(0)
												.getTableCells().get(0)
												.getText();
										if (flowTitle
												.startsWith("Alternate Flow")) {

											tablerow = table.getRow(1);
											AlternateFlowParser alternateFlowParser = new AlternateFlowParser();
											builder.append(alternateFlowParser
													.alternateFlowParser(
															tablerow, table));
											continue;

										} else if (flowTitle
												.startsWith("Business Area Rules")) {
											table = (XWPFTable) bodyElement;
											tablerow = table.getRow(1);
											AlternateFlowParser businessRulesParser = new AlternateFlowParser();
											builder.append(businessRulesParser
													.alternateFlowBusinessParser(
															tablerow, table));
											continue;
										} else if ((flowTitle
												.startsWith("Data Attributes"))
												|| (flowTitle
														.startsWith(" Data Attributes"))
												|| (flowTitle
														.startsWith(" Data Attributes "))
												|| (flowTitle
														.startsWith("Data Attributes"))) {
											table = (XWPFTable) bodyElement;
											tablerow = table.getRow(1);
											AlternateFlowParser businessRulesParser = new AlternateFlowParser();
											builder.append(businessRulesParser
													.alternateDAParser(
															tablerow, table));
											a = k;
											break;
										}

									} else if (bodyElement.getElementType() == BodyElementType.PARAGRAPH) {
										IBodyElement bodyElement2 = (IBodyElement) bodyElements
												.get(k);

										XWPFParagraph paragraph2 = (XWPFParagraph) bodyElement2;
										if ((paragraph2.getText()
												.startsWith("Data Attributes"))
												|| (paragraph2.getText()
														.startsWith(" Data Attributes"))
												|| (paragraph2.getText()
														.startsWith(" Data Attributes "))
												|| (paragraph2.getText()
														.startsWith("Data Attributes"))) {
											int temp2 = k;

											bodyElement2 = bodyElements
													.get(temp2 + 1);

											if ((bodyElement2.getElementType() == BodyElementType.PARAGRAPH)) {

												paragraph2 = (XWPFParagraph) bodyElement2;
												if (!(paragraph2.getText()
														.equals(""))) {
													builder.append("\t\t\t<alternateDataAttributes>");
													builder.append(paragraph2
															.getText());
													builder.append("</alternateDataAttributes>\n");
													a = k;
													break;
												}

											}

										}
									}
								}

								builder.append("\t\t</step>\n");
							}

						}

					}
					builder.append("\t</useCase>");
				}
			}

			builder.append("\n</uttmisDoc>");
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(
							"C:\\Users\\turlapatip\\Desktop\\UTMMIS\\R3\\XMLFiles\\"
									+ extensionRemovedFileName + ".xml"),
					"UTF-8"));
			out.write(builder.toString());

			out.close();

			System.out.println("File saved!");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void dataAttributesCheck(IBodyElement bodyElement1) {
		XWPFTable tab = (XWPFTable) bodyElement1;
					String tabTitle = tab.getRow(0).getTableCells().get(0)
							.getText();
					if ((tabTitle
							.startsWith("Data Attributes"))
							|| (tabTitle
									.startsWith(" Data Attributes"))
							|| (tabTitle
									.startsWith(" Data Attributes "))
							|| (tabTitle
									.startsWith("Data Attributes"))) {
						
						XWPFTableRow tabrow = tab.getRow(1);
						AlternateFlowParser businessRulesParser = new AlternateFlowParser();
						builder.append(businessRulesParser
								.alternateDAParser(
										tabrow, tab));
						builder.append("\t\t</step>\n");

					}

			
		
	
		}
	

	public static void main(String[] args) throws ParserConfigurationException,
			TransformerException {
		DocumentParser documentParser = new DocumentParser();
		documentParser.docParser();
	}

}
