package com.cnsi.xml.reader;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UttmisDoc {
	


	private String useCase;
	private String useCaseName;
	private String actors;
	private String triggers;
	private String preConditons;
	private String contstraints;
	private List<String> OtherUseCase;
	private String stepName;
	private String Navigation;
	private List<String> flow;

	private List<String> BusinessAreaRules;
	private List<String> DataAttributes;

	// setters and getters

	@XmlAttribute
	public String getUseCase(){
		return useCase;
	}
	
	public void setUseCase(){
		this.useCase = useCase;
	}
	
	@XmlAttribute
	public String getUseCaseName() {
		return useCaseName;
	}

	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
	}

	@XmlAttribute
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	@XmlAttribute
	public String getTriggers() {
		return triggers;
	}

	public void setTriggers(String triggers) {
		this.triggers = triggers;
	}

	@XmlAttribute
	public String getPreConditons() {
		return preConditons;
	}

	public void setPreConditons(String preConditons) {
		this.preConditons = preConditons;
	}

	@XmlAttribute
	public String getContstraints() {
		return contstraints;
	}

	public void setContstraints(String contstraints) {
		this.contstraints = contstraints;
	}

	@XmlAttribute
	public List<String> getOtherUseCase() {
		return OtherUseCase;
	}

	public void setOtherUseCase(List<String> otherUseCase) {
		OtherUseCase = otherUseCase;
	}

	@XmlAttribute
	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	@XmlAttribute
	public String getNavigation() {
		return Navigation;
	}

	public void setNavigation(String navigation) {
		Navigation = navigation;
	}

	@XmlAttribute
	public List<String> getFlow() {
		return flow;
	}

	public void setFlow(List<String> flow) {
		this.flow = flow;
	}

	@XmlAttribute
	public List<String> getBusinessAreaRules() {
		return BusinessAreaRules;
	}

	public void setBusinessAreaRules(List<String> businessAreaRules) {
		BusinessAreaRules = businessAreaRules;
	}

	@XmlAttribute
	public List<String> getDataAttributes() {
		return DataAttributes;
	}

	public void setDataAttributes(List<String> dataAttributes) {
		DataAttributes = dataAttributes;
	}



}
