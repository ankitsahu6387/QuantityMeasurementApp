package com.Apps.quantitymeasurement.service;

import com.Apps.quantitymeasurement.dto.QuantityInputDTO;
import com.Apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementService {

	QuantityMeasurementEntity convert(QuantityInputDTO input);

    QuantityMeasurementEntity add(QuantityInputDTO input);
    
    QuantityMeasurementEntity subtract(QuantityInputDTO input);
    
    QuantityMeasurementEntity divide(QuantityInputDTO input);

    List<QuantityMeasurementEntity> getHistoryByOperation(String operation);

    long getOperationCount(String operation);

	QuantityMeasurementEntity compare(QuantityInputDTO input);
}