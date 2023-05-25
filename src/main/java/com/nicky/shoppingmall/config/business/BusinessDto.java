package com.nicky.shoppingmall.config.business;

public abstract class BusinessDto {
    /**
     * dto 내의 데이터에 앞, 뒤 공백이 포함되어있는지 검사
     * @return true : 공백이 포함되어있음. false : 공백이 포함되어있지 않음.
     */
    protected boolean isBlank(String str) {
        if(str.startsWith(" ") || str.endsWith(" ")) {
            return true;
        }
        return false;
    }

    /**
     * dto 내의 데이터에 공백이 있는지 검사하고, 공백이 있는 데이터 정보를 반환
     * @return 앞 또는 뒤에 공백이 존재하는 변수명 반환
     */
    abstract public String invalidBlank();
}
