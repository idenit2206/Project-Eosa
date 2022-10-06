package com.eosa.web.users.temprandom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KorNameTempData {

    private final String[] LASTNAME = {
        "김", "기", "고", "공", "구",
        "나", "동", "라", "마",
        "박", "복", "신",        
        "양", "엄", "우", "원", "유", "윤", "오", "이",
        "조", "주", "진", "지", 
        "차", "최",
        "하", "한", "허", "홍" 
    };

    private final String[] CHO = {
        "ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ",
		"ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ",
        "ㅍ", "ㅎ"
    };

    private final String[] JOONG = {
        "ㅏ", "ㅐ", "ㅑ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
		"ㅛ", "ㅜ", "ㅝ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"
    };
    
    private final String[] JONG = {
        "", "ㄱ", "ㄴ", "ㄹ", "ㅁ", "ㅇ"
    };

    
    /** 
     * @param nameLength
     * @return String
     */
    //(char) ((fv * 21 + sv) * 28 + tv + 0XAC00)

    public String korNameGen(int nameLength) {
        String result = "";
        Double doubleLastName = Math.floor(Math.random() * LASTNAME.length);
        int indexLastName = doubleLastName.intValue();
        // System.out.println("성씨: " + LASTNAME[indexLastName]);
        // result += LASTNAME[indexLastName];

        for(int i = 0; i < nameLength; i++) {
            Double dfv = Math.floor(Math.random() * CHO.length);
            Double dsv = Math.floor(Math.random() * JOONG.length);
            Double dtv = Math.floor(Math.random() * JONG.length);

            int fv = dfv.intValue();
            int sv = dsv.intValue();
            int tv = dtv.intValue();
            
            // 한글 초성 19개 중성21개 종성28개
            // 0XAC00 + 21 * 28 * 초성 + 28 * 중성 + 종성
            
            int x = (fv * JOONG.length * JONG.length) + (sv * JONG.length) + tv;
            char c = (char) (x + 0XAC00);
            result += c; 
        }
        return result;
    } 
    
}
