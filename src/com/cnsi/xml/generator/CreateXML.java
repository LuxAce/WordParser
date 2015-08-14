package com.cnsi.xml.generator;

import java.util.List;



public class CreateXML {

	public String XMLGen(List<String> lis, List<String> liss) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t<dataAttributesRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t<dataAttributesCell"+i+" id=\""+liss.get(i)+"\">"+lis.get(i)+"</dataAttributesCell"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t</dataAttributesRow>\n");
		
		return stringBuilder.toString();
	}
	
	public String XMLAlternateBusinessArea(List<String> lis, List<String> liss) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t<alternateBusinessAreaRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t<alternateBusinessAreaCell"+i+" id=\""+liss.get(i)+"\">"+lis.get(i)+"</alternateBusinessAreaCell"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t</alternateBusinessAreaRow>\n");
		
		return stringBuilder.toString();
	}
	
	public String XMLAlternateRowGen(List<String> lis) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t\t<alternateFlowRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t\t<cell"+i+">"+lis.get(i)+"</cell"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t\t</alternateFlowRow>\n");
		
		return stringBuilder.toString();
	}
	
	public String XMLAlternateFlowRow(List<String> lis) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t\t<row>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t\t<cell"+i+">"+lis.get(i)+"</cell"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t\t</row>\n");
		
		return stringBuilder.toString();
	}
	public String XMLBusiness(List<String> lis, List<String> liss) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t<businessAreaRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t<businessAreaCell"+i+" id=\""+liss.get(i)+"\">"+lis.get(i)+"</businessAreaCell"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t</businessAreaRow>\n");
		
		return stringBuilder.toString();
	}
	
	public String StepFlowRowsGen(List<String> lis, List<String> liss) {
		StringBuilder builder = new StringBuilder();
		builder.append("\t\t\t\t\t<flowRow>\n");
			for(int i =0; i<lis.size();i++){
				builder.append("\t\t\t\t\t\t<flowCell"+i+" id=\""+liss.get(i)+"\">"+lis.get(i)+"</flowCell"+i+">\n");
			}
			builder.append("\t\t\t\t\t</flowRow>\n");
		
		return builder.toString();
	}
	
	
	public String XMLOtherUseCaseRows(List<String> lis) {
		StringBuilder builder = new StringBuilder();
		builder.append("\t\t\t<otherUseCaseRow>\n");
			for(int i =0; i<lis.size();i++){
				builder.append("\t\t\t\t<otherUseCaseCell"+i+">"+lis.get(i)+"</otherUseCaseCell"+i+">\n");
			}
			builder.append("\t\t\t</otherUseCaseRow>\n");
		
		return builder.toString();
	}
	public String XMLHeaderGen(List<String> lis) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t<dataAttributesHeaderRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t<dataAttributesHeader"+i+">"+lis.get(i)+"</dataAttributesHeader"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t</dataAttributesHeaderRow>\n");
		
		return stringBuilder.toString();
	}
	
	public String XMLAlternateHeaderGen(List<String> lis) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t<alternateBusinessAreaFlowHeaderRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t<alternateBusinessAreaFlowHeader"+i+">"+lis.get(i)+"</alternateBusinessAreaFlowHeader"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t</alternateBusinessAreaFlowHeaderRow>\n");
		
		return stringBuilder.toString();
	}
	
	
	public String XMLAlternateDAHeaderGen(List<String> lis) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t\t<alternateBusinessHeader>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t\t<header"+i+">"+lis.get(i)+"</header"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t\t</alternateBusinessHeader>\n");
		
		return stringBuilder.toString();
	}
	
	
	public String XMLBusinessHeader(List<String> lis) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t<businessHeaderRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t<businessHeader"+i+">"+lis.get(i)+"</businessHeader"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t</businessHeaderRow>\n");
		
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
	}
	
	public String XMLUseCaseNameGen(String useCaseName){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t<useCaseName id=\""+ useCaseName+"\">");
		stringBuilder.append(useCaseName.toString());
		stringBuilder.append("\t\t</useCaseName>\n");
		return stringBuilder.toString();
	}
	
	public String XMLStepFlowGen(List<String> lis){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t\t\t\t<flowHeaderRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t\t\t<flowHeader"+i+">"+lis.get(i)+"</flowHeader"+i+">\n");
			}
		stringBuilder.append("\t\t\t\t\t</flowHeaderRow>\n");
		
		return stringBuilder.toString();
	}
	
	public String XMLOtherUseCase(List<String> lis, String useCaseName){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t<otherUseCase id=\""+useCaseName+"\">\n\t\t\t<otherUseCaseheaderRow>\n");
			for(int i =0; i<lis.size();i++){
				stringBuilder.append("\t\t\t\t<otherUseCaseheader"+i+">"+lis.get(i)+"</otherUseCaseheader"+i+">\n");
			}
		stringBuilder.append("\t\t\t</otherUseCaseheaderRow>\n");
		
		return stringBuilder.toString();
	}
	
	public String XMLActorGen(List<String> lis, String useCaseName){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t<actor id=\""+useCaseName+"\">"+lis.get(0)+"</actor>\n");
		stringBuilder.append("\t\t<triggers id=\""+useCaseName+"\">"+lis.get(1)+"</triggers>\n");
		stringBuilder.append("\t\t<preConditons id=\""+useCaseName+"\">"+lis.get(2)+"</preConditons>\n");
		stringBuilder.append("\t\t<postConditons id=\""+useCaseName+"\">"+lis.get(1)+"</postConditons>\n");
		stringBuilder.append("\t\t<contstraints id=\""+useCaseName+"\">"+lis.get(1)+"</contstraints>\n");
		
		return stringBuilder.toString();
	}
	
	

}
