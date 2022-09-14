package com.eosa.web.requestform.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface SelectRequestFormList {
    Long getRequestFormIdx();
    Long getUsersIdx();
    String getUsersAccount();
    Integer getUsersAge();
    Long getCompanysIdx();
    String getRequestFormRegion1();
    String getRequestFormRegion2();
    String getRequestFormStatus();
    LocalDateTime getRequestConsultDate();
    LocalDateTime getRequestFormDate();
    LocalDateTime getRequestFormAcceptDate();
    LocalDateTime getRequestFormCompDate();
    String getRequestFormRejectMessage();
    List<String> getRequestFormCategory();
}
