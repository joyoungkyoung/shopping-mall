package com.nicky.shoppingmall.config.error;

public enum ErrorInfo {
    // Default Http
	NO_CONTENT                                      (204, "No content"),
    ACCESS_DENIED                                   (403, "Access is denied"),
    SYSTEM_ERROR                                    (500, "Internal system error"),
    // Auth
    WRONG_USERNAME_OR_PASSWORD                      (10000, "아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_REGEX_EMAIL                             (10001, "이메일 형식이 맞지 않습니다."),
    ALREADY_EXIST_ACCOUNT                           (10002, "이미 존재하는 계정입니다."),
    // Common
    INCLUDE_BLANK_PARAMETER                         (90000, "Some strings contain spaces before and after them : {0}"), // 일부 문자열의 앞,뒤에 공백이 포함되어있습니다 : {0}
    ELEVATION_REQUIRED                              (90001, "The requested operation requires elevation."), // 요청된 작업에는 권한 상승이 필요합니다.
    ;
    
    private final int code;
    private final String message;

    private ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 오류 메시지 일부를 {0}, {1} 등의 코드를 이용하여 대체함
     * ex)
     * ErrorInfo.AAA -> "test {0},{1}"
     * ErrorInfo.AAA.replaceMessage("replace1", "replace2");
     * result message : test replace1,replace2
     * @param paramArr 대체할 값
     * @return
     */
    public String replaceMessage(String... paramArr){
        String msg = message;
        for(int i = 0 ; i < paramArr.length; i++){
            msg = msg.replace("{"+i+"}", paramArr[i]);
        } 
        return msg;
    }

    public int getCode() {
        return code;
    }
    @Override
    public String toString() {
        return code + ": " + message;
    }
}
