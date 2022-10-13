package com.eosa.web.usersreview.entity;

import java.time.LocalDateTime;

public interface SelectReviewEntity {

    Long getIdx();
    String getUsersProfile();
    String getCompanysName();
    Long getUsersIdx();
    String getUsersNick();
    int getCommunicationScore();
    int getProcessScore();
    int getResultScore();
    int getSpecialityScore();
    LocalDateTime getReviewDate();
    String getReviewDetail();

}
