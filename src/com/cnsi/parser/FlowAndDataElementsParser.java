package com.cnsi.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class FlowAndDataElementsParser {

	

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlowAndDataElementsParser() {
        super();
        // TODO Auto-generated constructor stub
    }


	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void parser() throws  IOException {
		// TODO Auto-generated method stub
		//File file = null;
		
				File tbdRules = null;
				
				String fullRule = null;
				FileWriter fw = null;
				BufferedWriter  bw = null;
				
				//String locationDSD = request.getParameter("locationDSD");
				String subsystemName = "";
				//String subsystemName = request.getParameter("brsubsystemName");
				File folder = null;
				
				//FileWriter fw = null;
				//BufferedWriter  bw = null;
				FileInputStream fis = null;
				try {
					
					
					
				   //FileInputStream fis=new FileInputStream(file.getAbsolutePath());
				   
				  //  locationDSD = locationDSD.replace("\\", "\\\\");
					
				    folder = new File("c:\\test2\\");

					File[] listOfFiles = folder.listFiles();
					
				/*	//PrintWriter out = response.getWriter();
					System.out.println("<head>");
					System.out.println("<meta charset=\"utf-8\">");
					System.out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
					System.out.println("<meta name=\"description\" content=\"\">");
					System.out.println("<meta name=\"author\" content=\"\">");
					System.out.println("<meta HTTP-EQUIV=\"Content-type\" CONTENT=\"text/html;charset=UTF-8\">");
				
					System.out.println("<TITLE>Welcome to Technical World</TITLE>");
				
					System.out.println("<link href=\"/toolApp/newUI/css/cnsi-ux.css\" type=\"text/css\" rel=\"stylesheet\">");	
					System.out.println("<link href=\"/toolApp/newUI/css/ecams-custom.css\" type=\"text/css\" rel=\"stylesheet\">	"); 
					System.out.println("<link href=\"/toolApp/newUI/css/print/print.css\" type=\"text/css\" rel=\"stylesheet\" media=\"print\">");
					System.out.println("<link rel=\"stylesheet\" href=\"/toolAppp/newUI/css/print/print-preview.css\" type=\"text/css\" media=\"screen\">");
					
					System.out.println("</head>");
				   
					System.out.println("<body>");
					
					System.out.println("<TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=\"0\">");
					System.out.println("<tr>");
					System.out.println("<td colspan=\"4\">");
					System.out.println("<table width=\"100%\" class=\"widget-box\">");
					System.out.println("<tbody><tr><td>" +
							"<table class=\"widget-title\" width=\"100%\"> ");
					System.out.println("<tr><td><span class=\"icon\"><i class=\"icon-th\"></i></span><h5>Mismatch Found </h5>" +
							"<a class=\"btn-minimize pull-right\" href=\"#\"><i class=\"icon-chevron-up heading\"></i></a>");
					System.out.println("<div class=\"ImgButtonDiv\"></div>");
					System.out.println("</td>");
					System.out.println("</tr></table></td></tr>");
					System.out.println("<tr><td><div class=\"widget-content\"><table width=\"100%\"><tr><td class=\"pad-enab\"><table width=\"100%\"><tbody>");
					System.out.println("<tr><td><div id=\"complex\"><TABLE Width = \"100%\" class=\"table table-bordered table-hover draggable resizable mtop to-print\" Align = Center>");
					System.out.println("<TR>");
					System.out.println("<TH> Use Case Name</TH>");
					System.out.println("<TH> Flow Section Name</TH>");
					System.out.println("<TH> Actor Does</TH>");
					System.out.println("<TH> Field Rule</TH>");
					System.out.println("<TH> Data Attribute Section Name</TH>");
					System.out.println("<TH> Field Name</TH>");
					System.out.println("<TH> Required (R, CR, O, n/a)</TH>");
					System.out.println("<TH> Field Value</TH>");
					System.out.println("<TH> Field Type</TH>");
					System.out.println("</TR>");
					*/
					
					IBodyElement bodyElement = null;
					   IBody body = null;
					   
					   List<XWPFParagraph> paragraphs = null; 
					   XWPFParagraph xpara = null;
					   XWPFParagraph paragraph = null;
					   XWPFTable flowTable = null;
					   XWPFTableRow flowTitleRow = null;
					   XWPFTableRow flowRow = null;
					   String flowTitle = null;
					   XWPFTable dataTable = null;
					   String paraText = null;
					   String flowName = null;
					   
					   String fieldNameCal = null;
					   String fieldName = null;
					   String actorDoes = null;	
					   String fieldRule = null;
					   String systemDoes = null;
					   
					   String zeroColVal = null;
					   String firstColVal = null;
					   String secColVal = null;
					   String thirdColVal = null;
					   String forColVal = null;
					   String fifColVal = null;
					   String sixColVal = null;
					   String sevColVal = null;
					   String eigColVal = null;
					   
					   HashMap<String, HashMap> useCaseFlowMap = new HashMap();
					   HashMap<String, HashMap> useCaseDTMap = new HashMap();
					   String useCaseName = null;
					   String useCaseNameOld = null;
					   
					   HashMap<String, ArrayList> flowMap = new HashMap();
					   ArrayList<String> flowList = null;//new ArrayList<String>();
					   
					   HashMap<String, ArrayList> dataAttributeMap = new HashMap();
					   ArrayList<String> dataAttributeList = null;//new ArrayList<String>();
					   
					   BodyElementType bodyElementType = null;
					   
					for (File file : listOfFiles) {
						
						if (file.isFile()) {
							
							System.out.println("file.getAbsolutePath()>"+file.getAbsolutePath());
						   fis=new FileInputStream(file.getAbsolutePath());
				   
						   XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
						   
						   List<IBodyElement> bodyElements = xdoc.getBodyElements();
						   
						   
						   System.out.println("Size>"+bodyElements.size());
						   
						   for (int i = 0; i < bodyElements.size(); i++) {
							   
							   bodyElement = bodyElements.get(i);
							   
							   bodyElementType = bodyElement.getElementType();
							   
							   if(bodyElementType == BodyElementType.PARAGRAPH) {
								   
								   paragraph = (XWPFParagraph) bodyElement;
								   
								   //Use Case - R5-FIN-UC001-A/P Transaction/A/R Transaction Creation 
								   
								   if(paragraph.getText().startsWith("Use Case – R5-"+subsystemName+"-") || paragraph.getText().startsWith("Use Case R5–"+subsystemName+"-")
										   || paragraph.getText().startsWith("Use Case - R5-"+subsystemName+"-") || paragraph.getText().startsWith("Use Case R5-"+subsystemName+"-")) {	
									   								  
									   useCaseName = paragraph.getText();	
									   flowMap = new HashMap();	
									   dataAttributeMap = new HashMap();
								   }				 
								   System.out.println("paragraph.getText()>"+paragraph.getText()+"<<<<<<useCaseName>"+useCaseName+"<<subsystemName>>"+subsystemName);
							   }
							   
							   if(bodyElementType == BodyElementType.TABLE) {
								   
								   flowTable = (XWPFTable) bodyElement;
								   
								   flowTitleRow = flowTable.getRow(0);
								   
								   List<XWPFTableCell> tableCells = flowTitleRow.getTableCells();	//flowName = flowTable.getText();
								   
								   flowTitle = tableCells.get(0).getText();
								   
								   if(flowTitle.startsWith("Step ")) {// || flowTitle.startsWith("Alternate Flow ")) {
									   
									   flowList = new ArrayList();
									   
									   if(flowTitle.startsWith("Step ")) {
										   for (int x = 4; x < flowTable.getNumberOfRows() - 2; x++) {
											   
											   flowRow = flowTable.getRow(x);
											   
											   List<XWPFTableCell> flowTableCells = flowRow.getTableCells();
											   
											   if(flowTitle.startsWith("Step ") && flowTableCells.size() == 4) {
												   
												   actorDoes = flowTableCells.get(1).getText();
												   fieldRule = flowTableCells.get(2).getText();
												   systemDoes = flowTableCells.get(3).getText();
												   
												   if(actorDoes.trim().equals("")) {actorDoes = "BLANK VALUE";}
												   if(fieldRule.trim().equals("")) {fieldRule = "BLANK VALUE";}
												   if(systemDoes.trim().equals("")) {systemDoes = "BLANK VALUE";}
												   
												   if(actorDoes.indexOf("[") > 0) {
													   fieldNameCal = actorDoes.substring(actorDoes.indexOf("["), actorDoes.length() - 1);
													   if(fieldNameCal.indexOf(" ") > 0) {
														   fieldName = fieldNameCal.substring(fieldNameCal.indexOf(" ")).trim();
													   }
													   else {
														   fieldName = "INCORRECT FIELD NAME";
													   }
												   }
												   else {
													   fieldName = "INCORRECT FIELD NAME";
												   }								   
											   }
											   flowList.add("<fieldName>"+fieldName+"<actorDoes>"+actorDoes+"<fieldRule>"+fieldRule+"<systemDoes>"+systemDoes);
											   flowMap.put(flowTitle, flowList);
										   }
									   }
									   
									   /*
									   if(flowTitle.startsWith("Alternate Flow ")) {
									   
										   for (int x = 2; x < flowTable.getNumberOfRows() - 2; x++) {
											   
											   flowRow = flowTable.getRow(x);
											   
											   List<XWPFTableCell> flowTableCells = flowRow.getTableCells();
											   
											   if(flowTitle.startsWith("Alternate Flow ") && flowTableCells.size() == 4) {
												   
												   actorDoes = flowTableCells.get(1).getText().trim();
												   fieldRule = flowTableCells.get(2).getText().trim();
												   systemDoes = flowTableCells.get(3).getText().trim();
												   
												   if(actorDoes.trim().equals("")) {actorDoes = "BLANK VALUE";}
												   if(fieldRule.trim().equals("")) {fieldRule = "BLANK VALUE";}
												   if(systemDoes.trim().equals("")) {systemDoes = "BLANK VALUE";}
												   
												   if(actorDoes.indexOf("[") > 0) {
													   fieldNameCal = actorDoes.substring(actorDoes.indexOf("["), actorDoes.length() - 1).trim();
													   if(fieldNameCal.indexOf(" ") > 0) {
														   fieldName = fieldNameCal.substring(fieldNameCal.indexOf(" ")).trim();
													   }
													   else {
														   fieldName = "INCORRECT FIELD NAME";
													   }
												   }
												   else {
													   fieldName = "INCORRECT FIELD NAME";
												   }								   
											   }
											   flowList.add("<fieldName>"+fieldName+"<actorDoes>"+actorDoes+"<fieldRule>"+fieldRule+"<systemDoes>"+systemDoes);		
											   flowMap.put(flowTitle, flowList);
										   }
								   	   } */
									   
									   //System.System.out.println("useCaseName>"+useCaseName+"<flowMap>"+flowMap);
									   useCaseFlowMap.put(useCaseName.trim(), flowMap);					   
								   }
								   
								   if(flowTitle.startsWith("Data Attributes ")) {
									   
									   dataAttributeList = new ArrayList();
									   					   
									   for (int x = 2; x < flowTable.getNumberOfRows(); x++) {
										   
										   flowRow = flowTable.getRow(x);
										   
										   List<XWPFTableCell> DTTableCells = flowRow.getTableCells();
										   
										   if(flowTitle.startsWith("Data Attributes ") && DTTableCells.size() == 9) {
											   
											   zeroColVal = DTTableCells.get(0).getText().trim();
											   firstColVal = DTTableCells.get(1).getText().trim();
											   secColVal = DTTableCells.get(2).getText().trim();
											   thirdColVal = DTTableCells.get(3).getText().trim();
											   forColVal = DTTableCells.get(4).getText().trim();
											   fifColVal = DTTableCells.get(5).getText().trim();
											   sixColVal = DTTableCells.get(6).getText().trim();
											   sevColVal = DTTableCells.get(7).getText().trim();
											   eigColVal = DTTableCells.get(8).getText().trim();
											   
											   if(zeroColVal.trim().equals("")) {zeroColVal = "BLANK VALUE";}
											   if(firstColVal.trim().equals("")) {firstColVal = "BLANK VALUE";}
											   if(secColVal.trim().equals("")) {secColVal = "BLANK VALUE";}
											   if(thirdColVal.trim().equals("")) {thirdColVal = "BLANK VALUE";}
											   if(forColVal.trim().equals("")) {forColVal = "BLANK VALUE";}
											   if(fifColVal.trim().equals("")) {fifColVal = "BLANK VALUE";}
											   if(sixColVal.trim().equals("")) {sixColVal = "BLANK VALUE";}
											   if(sevColVal.trim().equals("")) {sevColVal = "BLANK VALUE";}
											   if(eigColVal.trim().equals("")) {eigColVal = "BLANK VALUE";}
											   
											   if(!(secColVal.equalsIgnoreCase("Static Text") || (secColVal.equalsIgnoreCase("Expand/Collapse")) || (secColVal.equalsIgnoreCase("Column")))) {
												   dataAttributeList.add("<Field Name>"+zeroColVal+"<Required (R, CR, O, n/a)>"+firstColVal+"<Field Type>"+secColVal+"<Field Value>"+thirdColVal+
														   "<Page Control Name>"+forColVal+"<Table Name>"+fifColVal+"<Column Name>"+sixColVal+"<Data Type>"+sevColVal+"<Default Value>"+eigColVal);
											   }
										   }
										   dataAttributeMap.put(flowTitle, dataAttributeList);
									   }	
									   //System.System.out.println("useCaseName>"+useCaseName+"<dataAttributeMap>"+dataAttributeMap);
									   useCaseDTMap.put(useCaseName.trim(), dataAttributeMap);
									   //System.System.out.println("useCaseDTMap>"+useCaseDTMap);
								   }
							   }			   			   			   			 
						   }
						}
					}
				   
				   dataAttributeMap = null;
				   flowMap = null;
				   
				   flowList = null;
				   dataAttributeList = null;
				   
				   Set<String> flowKeys = useCaseFlowMap.keySet();
				   
				   Iterator<String> flowIterator = flowKeys.iterator();
				   String ucName = null;
				   String pgFlowName = null;
				   String comFlowName = null;
				   String pgDTName = null;
				   String comDTName = null;
				   
				   String flow = null;
				   String dataAttribute = null;
				   
				   fieldNameCal = null;
				   fieldName = null;
				   actorDoes = null;	
				   fieldRule = null;
				   systemDoes = null;
				   
				   zeroColVal = null;
				   firstColVal = null;
				   secColVal = null;
				   thirdColVal = null;
				   forColVal = null;
				   fifColVal = null;
				   sixColVal = null;
				   sevColVal = null;
				   eigColVal = null;
				   
				   //System.System.out.println("useCaseDTMap>"+useCaseDTMap);
				   
				   while(flowIterator.hasNext()) {
					   
					   ucName = flowIterator.next();
					   //System.System.out.println("ucName>"+ucName);
					   flowMap = useCaseFlowMap.get(ucName);
					   Set<String> flowTitleKeys = flowMap.keySet();
					   Iterator<String> flowTitleIterator = flowTitleKeys.iterator();
					   
					   dataAttributeMap = useCaseDTMap.get(ucName);
					   if(dataAttributeMap != null) {
					   Set<String> DTTitleKeys = dataAttributeMap.keySet();
					   while(flowTitleIterator.hasNext()) {
						   
						   comFlowName = flowTitleIterator.next();
						   System.out.println("comFlowName>"+comFlowName);
						   if(comFlowName.indexOf("[") > 0) {
							   int firstIndex = comFlowName.indexOf("[");
							   if(comFlowName.indexOf("]", firstIndex + 1) > 0)
								   pgFlowName = comFlowName.substring(comFlowName.indexOf("["), comFlowName.indexOf("]", firstIndex + 1));
							   else
								   pgFlowName = comFlowName.substring(comFlowName.indexOf("["), comFlowName.length());
						   }
						   
						   
						   Iterator<String> DTTitleIterator = DTTitleKeys.iterator();
						   while(DTTitleIterator.hasNext()) {
							   
							   comDTName = DTTitleIterator.next();
							   //System.System.out.println("comDTName>"+comDTName);
							   pgDTName = comDTName.substring(comDTName.indexOf("["), comDTName.indexOf("]"));
							   
							   if(pgDTName.equalsIgnoreCase(pgFlowName)) {
								   
								   flowList = flowMap.get(comFlowName);
								   
								   for(int i = 0; i < flowList.size(); i++) {
									   
									    flow = flowList.get(i);
									   
									    fieldName = flow.substring(flow.indexOf("<fieldName>") + "<fieldName>".length(), flow.indexOf("<actorDoes>"));
									    actorDoes = flow.substring(flow.indexOf("<actorDoes>") + "<actorDoes>".length(), flow.indexOf("<fieldRule>"));
									    fieldRule = flow.substring(flow.indexOf("<fieldRule>") + "<fieldRule>".length(), flow.indexOf("<systemDoes>"));
									    systemDoes = flow.substring(flow.indexOf("<systemDoes>") + "<systemDoes>".length(), flow.length());
									    
									    dataAttributeList = dataAttributeMap.get(comDTName);
									    
									    for(int x = 0; x < dataAttributeList.size(); x++) {
									    	
									    	dataAttribute = dataAttributeList.get(x);
									    	zeroColVal = dataAttribute.substring(dataAttribute.indexOf("<Field Name>") + "<Field Name>".length(), dataAttribute.indexOf("<Required (R, CR, O, n/a)>"));
									    	firstColVal = dataAttribute.substring(dataAttribute.indexOf("<Required (R, CR, O, n/a)>") + "<Required (R, CR, O, n/a)>".length(), dataAttribute.indexOf("<Field Type>"));
									    	secColVal = dataAttribute.substring(dataAttribute.indexOf("<Field Type>") + "<Field Type>".length(), dataAttribute.indexOf("<Field Value>"));
									    	thirdColVal = dataAttribute.substring(dataAttribute.indexOf("<Field Value>") + "<Field Value>".length(), dataAttribute.indexOf("<Page Control Name>"));
									    	//System.System.out.println("zeroColVal>"+zeroColVal+"<fieldName>"+fieldName);
									    	
									    	if("Provider Code".equals(zeroColVal)) {
									    		System.out.println("dataAttributeList>"+dataAttributeList);
									    	}
									    	
									    	if(zeroColVal.equals(fieldName) && ((actorDoes.indexOf("[lb ") > 0 && secColVal.equalsIgnoreCase("List Box")) || 
									    			(actorDoes.indexOf("[fld ") > 0 && secColVal.equalsIgnoreCase("Text Field")) || 
									    			(actorDoes.indexOf("[btn ") > 0 && secColVal.equalsIgnoreCase("Button"))) 
									    			&& !((fieldRule.equals(firstColVal)))) {
									    		
									    		System.out.println("<TR>");
									    		System.out.println("<TD> "+ucName+"</TD>");
												System.out.println("<TD> "+comFlowName+"</TD>");
												System.out.println("<TD> "+actorDoes+"</TD>");
												System.out.println("<TD> <font color=\"green\">"+fieldRule+"</font></TD>");
												System.out.println("<TD> "+comDTName+"</TD>");
												System.out.println("<TD> "+zeroColVal+"</TD>");
												System.out.println("<TD> <font color=\"red\">"+firstColVal+"</font></TD>");
												System.out.println("<TD> "+thirdColVal+"</TD>");
												System.out.println("<TD> "+secColVal+"</TD>");
												System.out.println("</TR>");
												
									    		System.out.println("MISMATCH FOUND in "+comFlowName+" of "+ucName+" for fieldName>"+fieldName+"<actorDoes>"+actorDoes+"<fieldRule>"+fieldRule+"<zeroColVal>"+zeroColVal+"<firstColVal>"+firstColVal);
									    	}
									    	
									    	
									    } 
									    /*
									    zeroColVal = fieldVal.substring(fieldVal.indexOf("Field Name:") + "Field Name:".length(), fieldVal.indexOf("Required (R, CR, O, n/a)"));
										firstColVal = fieldVal.substring(fieldVal.indexOf("Required (R, CR, O, n/a)") + "Required (R, CR, O, n/a)".length(), fieldVal.indexOf("Field Type"));
										secColVal = fieldVal.substring(fieldVal.indexOf("Field Type") + "Field Type".length(), fieldVal.indexOf("Field Value"));
										thirdColVal = fieldVal.substring(fieldVal.indexOf("Field Value") + "Field Value".length(), fieldVal.indexOf("Page Control Name"));
										forColVal = fieldVal.substring(fieldVal.indexOf("Page Control Name") + "Page Control Name".length(), fieldVal.indexOf("Table Name"));
										fifColVal = fieldVal.substring(fieldVal.indexOf("Table Name") + "Table Name".length(), fieldVal.indexOf("Column Name"));
										sixColVal = fieldVal.substring(fieldVal.indexOf("Column Name") + "Column Name".length(), fieldVal.indexOf("Data Type"));
										sevColVal = fieldVal.substring(fieldVal.indexOf("Data Type") + "Data Type".length(), fieldVal.indexOf("Default Value"));
										eigColVal = fieldVal.substring(fieldVal.indexOf("Default Value") + "Default Value".length(), fieldVal.length());	   
									
									   */
								   }
							   }
							   
						   }
						   
					   }
					   
					    
				   }
					   
					   
					   
					   
					   
					   
				   }
				   
				   
				   
				   System.out.println("</TABLE></td></tr></tbody></table></td></tr></table></div></td></tr></td></tr></table></td></tr></tbody></table></td></tr></TABLE>");
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}



	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FlowAndDataElementsParser parser = new FlowAndDataElementsParser();
		parser.parser();
	}

}
