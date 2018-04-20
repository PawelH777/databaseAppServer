package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.Dimiensions;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Paweł on 2018-04-03.
 */
public interface DimRepoCustom {


    List<Dimiensions> findByDim(BigDecimal firstDimension, BigDecimal secondDimension, BigDecimal thickness, BigDecimal weight);
}
