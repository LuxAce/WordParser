package com.cnsi.xml.vo;

public class BusinessAreaVO {

	private String useCaseName;
	private String stepName;
	private String businessRule;
	private String businessErrorDisplayed;
	private String appliedAt;
	
	
	public String getUseCaseName() {
		return useCaseName;
	}
	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getBusinessRule() {
		return businessRule;
	}
	public void setBusinessRule(String businessRule) {
		this.businessRule = businessRule;
	}
	public String getBusinessErrorDisplayed() {
		return businessErrorDisplayed;
	}
	public void setBusinessErrorDisplayed(String businessErrorDisplayed) {
		this.businessErrorDisplayed = businessErrorDisplayed;
	}
	public String getAppliedAt() {
		return appliedAt;
	}
	public void setAppliedAt(String appliedAt) {
		this.appliedAt = appliedAt;
	}
}
