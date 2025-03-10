package com.remaslover.dataanalyzermicroservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sensorId;
    private LocalDateTime timestamp;
    private double measurement;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MeasurementType measurementType;

    public Data() {}


    public Data(Long sensorId, LocalDateTime timestamp, double measurement, MeasurementType measurementType) {
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.measurement = measurement;
        this.measurementType = measurementType;
    }

    public enum MeasurementType {
        TEMPERATURE,
        VOLTAGE,
        POWER
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    @Override
    public String toString() {
        return "Data{" +
               "sensorId=" + sensorId +
               ", timestamp=" + timestamp +
               ", measurement=" + measurement +
               ", measurementType=" + measurementType +
               '}';
    }
}
