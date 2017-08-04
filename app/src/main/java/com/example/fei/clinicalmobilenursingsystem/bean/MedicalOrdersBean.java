package com.example.fei.clinicalmobilenursingsystem.bean;

/**
 * Created by fly on 2017-04-18.
 */

public class MedicalOrdersBean {
    private String property;
    private String advice_project;
    private String standard;
    private String unit;
    private String dosage;
    private boolean is_selected;

    public MedicalOrdersBean() {
    }

    public MedicalOrdersBean(String property, String advice_project, String standard, String unit, String dosage) {
        this.property = property;
        this.advice_project = advice_project;
        this.standard = standard;
        this.unit = unit;
        this.dosage = dosage;
        this.is_selected = false;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getAdvice_project() {
        return advice_project;
    }

    public void setAdvice_project(String advice_project) {
        this.advice_project = advice_project;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public boolean is_selected() {
        return is_selected;
    }

    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }
}
