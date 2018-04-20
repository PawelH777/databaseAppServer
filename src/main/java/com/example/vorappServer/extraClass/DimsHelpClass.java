package com.example.vorappServer.extraClass;

import java.math.BigDecimal;

/**
 * Created by Paweł on 2018-04-03.
 */
public class DimsHelpClass {

    private Long dimension_id;

    private BigDecimal firstDimension;

    private BigDecimal secondDimension;

    private BigDecimal thickness;

    private BigDecimal weight;

    public Long getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(Long dimension_id) {
        this.dimension_id = dimension_id;
    }

    public BigDecimal getFirstDimension() {
        return firstDimension;
    }

    public void setFirstDimension(BigDecimal firstDimension) {
        this.firstDimension = firstDimension;
    }

    public BigDecimal getSecondDimension() {
        return secondDimension;
    }

    public void setSecondDimension(BigDecimal secondDimension) {
        this.secondDimension = secondDimension;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public DimsHelpClass(BigDecimal firstDimension, BigDecimal secondDimension, BigDecimal thickness, BigDecimal weight) {
        this.firstDimension = firstDimension;
        this.secondDimension = secondDimension;
        this.thickness = thickness;
        this.weight = weight;
    }

    public DimsHelpClass() {
    }
}
