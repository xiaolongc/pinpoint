package com.nhn.pinpoint.web.vo;

import com.nhn.pinpoint.web.applicationmap.rawdata.Histogram;

import java.util.*;

/**
 * @author emeroad
 */
public class ResponseTime {
    // rowKey
    private final String applicationName;
    private final short applicationServiceType;
    private final long timeStamp;

    // agentId 가 key임.
    private final Map<String, Histogram> responseHistogramMap = new HashMap<String, Histogram>();


    public ResponseTime(String applicationName, short applicationServiceType, long timeStamp) {
        if (applicationName == null) {
            throw new NullPointerException("applicationName must not be null");
        }
        this.applicationName = applicationName;
        this.applicationServiceType = applicationServiceType;
        this.timeStamp = timeStamp;
    }


    public String getApplicationName() {
        return applicationName;
    }

    public short getApplicationServiceType() {
        return applicationServiceType;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Histogram getHistogram(String agentId) {
        if (agentId == null) {
            throw new NullPointerException("agentId must not be null");
        }
        Histogram histogram = responseHistogramMap.get(agentId);
        if (histogram == null) {
            histogram = new Histogram(applicationServiceType);
            responseHistogramMap.put(agentId, histogram);
        }
        return histogram;
    }

    public void addResponseTime(String agentId, short slotNumber, long count) {
        getHistogram(agentId).addCallCount(slotNumber, count);
    }

    public void addResponseTime(String agentId, int elapsedTime) {
        getHistogram(agentId).addCallCountByElapsedTime(elapsedTime);
    }

    public Collection<Histogram> getAgentResponseHistogramList() {
        return responseHistogramMap.values();
    }

    public Histogram getApplicationResponseHistogram() {
        Histogram result = new Histogram(applicationServiceType);
        for (Histogram histogram : responseHistogramMap.values()) {
            result.add(histogram);
        }
        return result;
    }

    public Set<Map.Entry<String, Histogram>> getAgentHistogram() {
        return this.responseHistogramMap.entrySet();
    }

    @Override
    public String toString() {
        return "ResponseTime{" +
                "applicationName='" + applicationName + '\'' +
                ", applicationServiceType=" + applicationServiceType +
                ", timeStamp=" + timeStamp +
                ", responseHistogramMap=" + responseHistogramMap +
                '}';
    }

}
