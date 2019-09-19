package com.BusinessStatisticsUnitFiles.interfaces.models;

import java.util.Date;

public interface IAffiliatedCompanyModel {

    float getPercentageShared();

    void setPercentageShared(float percentageShared);

    Date getIntegrationDate();

    void setIntegrationDate(Date integrationDate);

    int getStatus();

    void setStatus(int status);
}
