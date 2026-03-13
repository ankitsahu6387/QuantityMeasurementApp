package com.Apps.quantitymeasurement.repository;

import java.util.List;

import com.Apps.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> findAll();
    
    void deleteAllMeasurements();
}