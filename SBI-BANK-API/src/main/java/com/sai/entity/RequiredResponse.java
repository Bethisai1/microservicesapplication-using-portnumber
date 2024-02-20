package com.sai.entity;

import com.sai.model.bankapplications;

import java.util.List;


public class RequiredResponse {
    private int center;
    private List<bankapplication> citizens;
	public RequiredResponse() {
		super();
	}
	public RequiredResponse(int center, List<bankapplication> citizens) {
		super();
		this.center = center;
		this.citizens = citizens;
	}
	public int getCenter() {
		return center;
	}
	public void setCenter(int center) {
		this.center = center;
	}
	public List<bankapplication> getCitizens() {
		return citizens;
	}
	public void setCitizens(List<bankapplication> citizens) {
		this.citizens = citizens;
	}
	@Override
	public String toString() {
		return "RequiredResponse [center=" + center + ", citizens=" + citizens + "]";
	}
    
    
    
    
    
}

  