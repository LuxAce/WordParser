package com.cnsi.xml.reader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cnsi.parser.Test;
import com.cnsi.xml.vo.BusinessAreaVO;
import com.cnsi.xml.vo.BusinessFlowVO;
import com.cnsi.xml.vo.DataAttributesVO;
import com.cnsi.xml.vo.OtherUseCasesVO;

public class UnMarshalXML {
	
public UnMarshalXML() throws IOException{
	test.dumpStacks();
}
	
	
	public Test  test = new Test();

	
	
	
	public OtherUseCasesVO otherUseCasesVO;
	public BusinessFlowVO flowVO;
	public BusinessAreaVO businessAreaVo;
	public DataAttributesVO dataAttributesVO;
	public List<OtherUseCasesVO> otherCaseList = new ArrayList<OtherUseCasesVO>();
	public List<BusinessFlowVO> businessFlowList;
	public List<BusinessAreaVO> businessAreaList;
	public List<DataAttributesVO> dataAttributeList ;

	public void unMarsherller() {/*
								 * 
								 * try { File inputFile = new
								 * File("c://test//test1.xml");
								 * DocumentBuilderFactory dbFactory =
								 * DocumentBuilderFactory .newInstance();
								 * DocumentBuilder dBuilder;
								 * 
								 * dBuilder = dbFactory.newDocumentBuilder();
								 * 
								 * Document doc = dBuilder.parse(inputFile);
								 * doc.getDocumentElement().normalize();
								 * 
								 * XPath xPath =
								 * XPathFactory.newInstance().newXPath();
								 * 
								 * String expression = "/uttmisDoc/useCase";
								 * NodeList nodeList = (NodeList)
								 * xPath.compile(expression).evaluate( doc,
								 * XPathConstants.NODESET); for (int i = 0; i <
								 * nodeList.getLength(); i++) { Node nNode =
								 * nodeList.item(i);
								 * System.out.println("\nCurrent Element :" +
								 * nNode.getNodeName()); if (nNode.getNodeType()
								 * == Node.ELEMENT_NODE) { Element eElement =
								 * (Element) nNode; String useCaseName =
								 * eElement
								 * .getElementsByTagName("useCaseName").item(0)
								 * .getTextContent();
								 * getOtherUseCase(useCaseName, xPath, doc);
								 * 
								 * getStepDetails(useCaseName, xPath, doc);
								 * 
								 * System.out.println("Use Case Name : " +
								 * eElement.getElementsByTagName("useCaseName")
								 * .item(0).getTextContent());
								 * System.out.println("Actors : " +
								 * eElement.getElementsByTagName
								 * ("actor").item(0) .getTextContent());
								 * System.out.println("Triggers : " +
								 * eElement.getElementsByTagName
								 * ("triggers").item(0) .getTextContent());
								 * System.out.println("Pre Conditons : " +
								 * eElement.getElementsByTagName("preConditons")
								 * .item(0).getTextContent());
								 * System.out.println("Post Conditons : " +
								 * eElement
								 * .getElementsByTagName("postConditons")
								 * .item(0).getTextContent());
								 * System.out.println("Constraints : " +
								 * eElement.getElementsByTagName("contstraints")
								 * .item(0).getTextContent()); NodeList list =
								 * eElement
								 * .getElementsByTagName("otherUseCase"); for
								 * (int tem = 0; tem < list.getLength(); tem++)
								 * { Node node = list.item(tem); if
								 * (node.getNodeType() == Node.ELEMENT_NODE) {
								 * Element eElement1 = (Element) node; if
								 * ((useCaseName.contains(eElement1
								 * .getAttribute("id"))) &&
								 * (eElement1.getNodeName()
								 * .contains("otherUseCase"))) { otherUseCasesVO
								 * = new OtherUseCasesVO();
								 * otherUseCasesVO.setUseCaseID(useCaseName);
								 * for (int k = 0; k < eElement1
								 * .getElementsByTagName("otherUseCaseRow")
								 * .getLength(); k++) {
								 * 
								 * otherUseCasesVO.setUseCaseName(eElement
								 * .getElementsByTagName( "otherUseCaseCell0")
								 * .item(k).getTextContent());
								 * otherUseCasesVO.setUseCaseDSD(eElement
								 * .getElementsByTagName( "otherUseCaseCell1")
								 * .item(k).getTextContent());
								 * otherUseCasesVO.setUseCasePurpose(eElement
								 * .getElementsByTagName( "otherUseCaseCell2")
								 * .item(k).getTextContent());
								 * 
								 * otherCaseList.add(otherUseCasesVO);
								 * 
								 * } } }
								 * 
								 * } NodeList list1 =
								 * eElement.getElementsByTagName("step"); for
								 * (int k = 0; k < list1.getLength(); k++) {
								 * Node node = list1.item(k); if
								 * (node.getNodeType() == Node.ELEMENT_NODE) {
								 * Element eElement2 = (Element) node; if
								 * ((useCaseName.contains(eElement2
								 * .getAttribute("id"))) &&
								 * (eElement2.getNodeName() .contains("step")))
								 * { getStepElements(eElement);
								 * 
								 * for (int l = 0; l < eElement2
								 * .getElementsByTagName("flow") .getLength();
								 * l++) {
								 * 
								 * }
								 * 
								 * } } }
								 * 
								 * System.out.println("Step : " +
								 * eElement.getElementsByTagName("step").item(0)
								 * .getNodeName());
								 * System.out.println("Step Name : " +
								 * eElement.getElementsByTagName
								 * ("stepName").item(0) .getTextContent());
								 * 
								 * } } } catch (ParserConfigurationException e)
								 * { e.printStackTrace(); } catch (SAXException
								 * e) { e.printStackTrace(); } catch
								 * (IOException e) { e.printStackTrace(); }
								 * catch (XPathExpressionException e) {
								 * e.printStackTrace(); }
								 */
	}

	private void getOtherUseCase(String useCaseName, XPath xPath, Document doc)
			throws XPathExpressionException {/*
											 * String expression =
											 * "/uttmisDoc/useCase/otherUseCase"
											 * ; NodeList nodeList = (NodeList)
											 * xPath
											 * .compile(expression).evaluate
											 * (doc, XPathConstants.NODESET);
											 * for (int i = 0; i <
											 * nodeList.getLength(); i++) { Node
											 * nNode = nodeList.item(i); if
											 * (nNode.getNodeType() ==
											 * Node.ELEMENT_NODE) { Element
											 * eElement = (Element) nNode;
											 * String id =
											 * eElement.getAttribute("id"); if
											 * (useCaseName.contains(id)) {
											 * NodeList nodeList2 = eElement
											 * .getElementsByTagName
											 * ("otherUseCaseRow");
											 * otherCaseList = new
											 * ArrayList<OtherUseCasesVO>(); for
											 * (int k = 0; k <
											 * nodeList2.getLength(); k++) {
											 * Node node = nodeList2.item(k); if
											 * (node.getNodeType() ==
											 * Node.ELEMENT_NODE) {
											 * otherUseCasesVO = new
											 * OtherUseCasesVO(); Element
											 * eElement1 = (Element) nNode;
											 * otherUseCasesVO
											 * .setUseCaseName(eElement1
											 * .getElementsByTagName
											 * ("otherUseCaseCell0")
											 * .item(k).getTextContent());
											 * otherUseCasesVO
											 * .setUseCaseDSD(eElement1
											 * .getElementsByTagName
											 * ("otherUseCaseCell1")
											 * .item(k).getTextContent());
											 * otherUseCasesVO
											 * .setUseCasePurpose(eElement1
											 * .getElementsByTagName
											 * ("otherUseCaseCell2")
											 * .item(k).getTextContent());
											 * otherCaseList
											 * .add(otherUseCasesVO); } } } } }
											 */
	}

	private void getStepDetails(String useCaseName, XPath xPath, Document doc)
			throws XPathExpressionException {/*
											 * String expression =
											 * "/uttmisDoc/useCase/step";
											 * NodeList nodeList = (NodeList)
											 * xPath
											 * .compile(expression).evaluate
											 * (doc, XPathConstants.NODESET);
											 * for (int i = 0; i <
											 * nodeList.getLength(); i++) { Node
											 * nNode = nodeList.item(i); if
											 * (nNode.getNodeType() ==
											 * Node.ELEMENT_NODE) { Element
											 * eElement = (Element) nNode;
											 * String id =
											 * eElement.getAttribute("id");
											 * String type =
											 * eElement.getAttribute("type"); if
											 * (useCaseName.contains(id)) { if
											 * (type.contains("normal")) {
											 * String stepName = eElement
											 * .getElementsByTagName
											 * ("stepName").item(i)
											 * .getTextContent(); NodeList
											 * nodeList2 =
											 * eElement.getElementsByTagName
											 * ("otherUseCaseRow");
											 * System.out.println
											 * (nodeList2.getLength()); Node
											 * node = nodeList2.item(i); if
											 * (nNode.getNodeType() ==
											 * Node.ELEMENT_NODE) {
											 * 
											 * for (int k = 0; k <
											 * nodeList2.getLength(); k++) {
											 * Node node1 = nodeList2.item(k);
											 * 
											 * Element aElement = (Element)
											 * node1; if
											 * ((useCaseName.contains(aElement
											 * .getAttribute("id"))) &&
											 * (stepName.contains(aElement
											 * .getAttribute("stepId")))) { if
											 * (node1.getNodeType() ==
											 * Node.ELEMENT_NODE) { Element
											 * eElement1 = (Element) node1;
											 * System.out.println((eElement1
											 * .getElementsByTagName(
											 * "flowCell0").item(k)
											 * .getTextContent()));
											 * System.out.println((eElement1
											 * .getElementsByTagName(
											 * "flowCell1").item(k)
											 * .getTextContent()));
											 * System.out.println((eElement1
											 * .getElementsByTagName(
											 * "flowCell2").item(k)
											 * .getTextContent()));
											 * 
											 * } } } } }
											 * 
											 * } } }
											 */
	}

	private void getStepElements(Element element) {/*
													 * NodeList list =
													 * element.getElementsByTagName
													 * ("step"); for(int i=0;
													 * i<list.getLength(); i
													 * ++){ Node nNode =
													 * list.item(i);
													 * System.out.println
													 * ("\nCurrent Element :" +
													 * nNode.getNodeName()); if
													 * (nNode.getNodeType() ==
													 * Node.ELEMENT_NODE){
													 * Element element2 =
													 * (Element)nNode;
													 * System.out.println(
													 * "\nCurrent Element :" +
													 * nNode.getNodeName());
													 * 
													 * } }
													 * 
													 * // System.out.println(
													 * "Step Name-->>"
													 * +stepName);
													 */
	}

	public void xercesParser() throws IOException, SAXException {
		/*
		 * File file = new File("c://test//test1.xml"); URI fileName =
		 * file.toURI();
		 * 
		 * Digester digester = new Digester();
		 * 
		 * digester.addCallMethod("utmmis/useCase", "useCase");
		 * digester.addCallParam("utmmis/useCase/useCaseName", 0);
		 * digester.parse(fileName.toString());
		 */
	}

	public void useCaseName(String useCasename) {
		System.out.println(useCasename);
	}

	public void saxReader() throws DocumentException, IOException {
		File inputFile = new File("c://test//test1.xml");
		test.dumpStacks();
		SAXReader reader = new SAXReader();
		Document document = (Document) reader.read(inputFile);
		System.out.println("Root element :"
				+ document.getRootElement().getName());
		List<Node> nodes = document.selectNodes("/uttmisDoc/useCase");
		for (Node node : nodes) {
			String useCaseName = node.selectSingleNode("useCaseName").getText();
			List<Node> otherUseCaseNode = document
					.selectNodes("/uttmisDoc/useCase/otherUseCase");
			for (Node otNode : otherUseCaseNode) {
				String otherNodeID = otNode.valueOf("@id");
				if (useCaseName.contains(otherNodeID)) {
					Iterator<Node> itr = otNode.selectNodes("otherUseCaseRow")
							.iterator();
					otherCaseList = new ArrayList<OtherUseCasesVO>();
					while (itr.hasNext()) {
						otherUseCasesVO = new OtherUseCasesVO();
						otherUseCasesVO.setUseCaseID(otherNodeID);
						Node node2 = itr.next();
						otherUseCasesVO.setUseCaseName(node2.selectSingleNode(
								"otherUseCaseCell0").getText());
						otherUseCasesVO.setUseCaseDSD(node2.selectSingleNode(
								"otherUseCaseCell1").getText());
						otherUseCasesVO.setUseCasePurpose(node2
								.selectSingleNode("otherUseCaseCell2")
								.getText());
						otherCaseList.add(otherUseCasesVO);
					}
				}
			}

			List<Node> stepNode = document
					.selectNodes("/uttmisDoc/useCase/step");
			for (Node stNode : stepNode) {
				String stepName = stNode.selectSingleNode("stepName").getText();
				List<Node> flowList = stNode.selectNodes("flow");
				String id = null;
				String StepId = null;
				businessFlowList = new ArrayList<BusinessFlowVO>();
				businessAreaList = new ArrayList<BusinessAreaVO>();
				dataAttributeList = new ArrayList<DataAttributesVO>();
				for (Node flNode : flowList) {
					id = flNode.valueOf("@id");
					StepId = flNode.valueOf("@stepId");
					if ((useCaseName.toString().equals(id.toString()))
							&& (stepName.toString().equals(StepId
									.toString()))) {

						@SuppressWarnings("unchecked")
						Iterator<Node> itr = flNode.selectNodes("flowRow")
								.iterator();
						while (itr.hasNext()) {
							flowVO = new BusinessFlowVO();
							flowVO.setUseCaseName(useCaseName);
							Node node2 = itr.next();
							flowVO.setFlow(node2.selectSingleNode("flowCell0")
									.getText());
							flowVO.setActorDoes(node2.selectSingleNode(
									"flowCell1").getText());
							flowVO.setFieldRules(node2.selectSingleNode(
									"flowCell2").getText());
							flowVO.setSystemDoes(node2.selectSingleNode(
									"flowCell3").getText());
							businessFlowList.add(flowVO);
						}
						
						List<Node> businessAreaRules = stNode.selectNodes("businessAreaRules");
						for (Node barNode : flowList) {
							id = barNode.valueOf("@id");
							StepId = barNode.valueOf("@stepId");
							if ((useCaseName.toString().equals(id.toString()))
									&& (stepName.toString().equals(StepId
											.toString()))) {
						@SuppressWarnings("unchecked")
						Iterator<Node> itr1 = flNode.selectNodes(
								"businessAreaRow").iterator();
						while (itr1.hasNext()) {
							businessAreaVo = new BusinessAreaVO();
							businessAreaVo.setUseCaseName(useCaseName);
							businessAreaVo.setStepName(stepName);
							Node node2 = itr1.next();
							businessAreaVo.setBusinessRule(node2.selectSingleNode(
									"businessAreaCell0").getText());
							businessAreaVo.setBusinessErrorDisplayed(node2.selectSingleNode(
									"businessAreaCell1").getText());
							businessAreaVo.setAppliedAt(node2.selectSingleNode(
									"businessAreaCell2").getText());

							businessAreaList.add(businessAreaVo);

						}
						
						@SuppressWarnings("unchecked")
						Iterator<Node> itr2 = flNode.selectNodes(
								"dataAttributesRow").iterator();
						while (itr2.hasNext()) {
							dataAttributesVO = new DataAttributesVO();
							dataAttributesVO.setUseCaseName(useCaseName);
							dataAttributesVO.setStepName(stepName);
							Node node2 = itr2.next();
							dataAttributesVO.setFieldName(node2.selectSingleNode(
									"dataAttributesCell0").getText());
							dataAttributesVO.setRequired(node2.selectSingleNode(
									"dataAttributesCell1").getText());
							dataAttributesVO.setFieldType(node2.selectSingleNode(
									"dataAttributesCell2").getText());
							dataAttributesVO.setFieldValue(node2.selectSingleNode(
									"dataAttributesCell3").getText());
							dataAttributesVO.setPageControlName(node2.selectSingleNode(
									"dataAttributesCell4").getText());
							dataAttributesVO.setTableName(node2.selectSingleNode(
									"dataAttributesCell5").getText());
							dataAttributesVO.setColumnName(node2.selectSingleNode(
									"dataAttributesCell6").getText());
							dataAttributesVO.setDataType(node2.selectSingleNode(
									"dataAttributesCell7").getText());
							dataAttributesVO.setDefaultValue(node2.selectSingleNode(
									"dataAttributesCell8").getText());
							dataAttributeList.add(dataAttributesVO);

						}
					}

				}
			}
				}}}

	}

	public static void main(String[] args) throws IOException, SAXException,
			DocumentException {
		UnMarshalXML unMarshalXML = new UnMarshalXML();
		unMarshalXML.saxReader();
		
	}
}
