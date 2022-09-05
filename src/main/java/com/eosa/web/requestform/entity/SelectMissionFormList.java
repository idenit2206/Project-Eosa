package com.eosa.web.requestform.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface SelectMissionFormList {
    Long getMissionFormIdx();
    Long getUsersIdx();
    String getUsersAccount();
    Long getCompanysIdx();
    String getMissionFormRegion1();
    String getMissionFormRegion2();
    String getMissionFormStatus();
    LocalDateTime getMissionFormAcceptDate();
    LocalDateTime getMissionFormCompDate();
    String getMissionFormMessage();
    List<String> getRequestFormCategory();
}
