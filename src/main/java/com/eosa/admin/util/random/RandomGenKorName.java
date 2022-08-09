package com.eosa.admin.util.random;

import lombok.Data;

@Data
public class RandomGenKorName {
    
    private String[] LastName = {
        "간", "고", "국", "김", "기", "나", "노", "동", "라", "마", "문",
        "박", "사", "신", "안", "여", "오", "우", "유", "윤", "은", "이",
        "제", "조", "지", "차", "최", "하", "한", "황", "허", "현"
    };

    private String[] FirstName = {
        "가", "구", "국", "규", "균", "나", "노", "누",
        "다", "독", "돈", "동", "라", "락", "란", "리",
        "마", "미", "민", "무", "묵", "문", "빈", "시",
        "산", "선", "수", "순", "소", "손", "속",
        "아", "이", "우", "욱", "운", "원", "오", "옥", "의", "예",
        "자", "전", "주", "준", "중", "조", "종", "재", "제",
        "지", "진", "춘", "하", "한", "허", "헌", "혁", "형",
        "호", "효", "환", "후", "훈", "희"
    };

    public String RandomGenKorName(int nameLength) {
        String result = "";
        
        result += LastName[(int) Math.round(Math.random() * LastName.length)];
        for(int i = 0; i < nameLength - 1; i++) {
            int temp = (int) Math.round(Math.random() * LastName.length);
            result += FirstName[temp];
        }

        return result;
    } 

}
