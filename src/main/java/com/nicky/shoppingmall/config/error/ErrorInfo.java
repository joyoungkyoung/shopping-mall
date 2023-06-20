package com.nicky.shoppingmall.config.error;

public enum ErrorInfo {
    // Default Http
	NO_CONTENT                                      (204, "No content"),
    ACCESS_UNAUTHORIZED                             (401, "Access is unauthorized"),
    ACCESS_DENIED                                   (403, "Access is denied"),
    SYSTEM_ERROR                                    (500, "Internal system error"),
    // Auth
    WRONG_USERNAME_OR_PASSWORD                      (10000, "아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_REGEX_EMAIL                             (10001, "이메일 형식이 맞지 않습니다."),
    ALREADY_EXIST_ACCOUNT                           (10002, "이미 존재하는 계정입니다."),
    INVALID_TOKEN                                   (10004, "토큰이 유효하지 않습니다."),
    // User
    NOT_FOUND_USER_DATA                             (20000, "유저 정보를 찾지 못했습니다."),
    // Admin Mng
    REQUIRE_ELEVATION_OF_PRIVILEGE                  (30000, "권한 상승이 필요합니다."),
    DUPLICATE_USERNAME                              (30001, "아이디가 중복됩니다."),
    // Product
    INVALID_PRODUCT_META                            (40000, "상품 메타데이터가 없습니다."),
    INVALID_PRODUCT_OPT                             (40001, "상품 옵션이 없습니다."),
    INVALID_PRODUCT_COMPLETE                        (40002, "상품 등록 완료 처리가 유효하지 않습니다.(이미 완료된 상품)"),
    INVALID_PRODUCT_SUB_IMAGE                       (40003, "상품 서브이미지가 없습니다."),
    INVALID_PRODUCT_ID                              (40004, "상품 아이디가 유효하지 않습니다."),
    PRODUCT_NOT_FOUND                               (40005, "상품을 찾을 수 없습니다."),
    // Image
    INVALID_IMAGE_FORMAT                            (80000, "이미지가 아닌 파일이 존재합니다."),
    IMAGE_NOT_EXIST                                 (80001, "이미지가 없습니다."),
    // Common
    INCLUDE_BLANK_PARAMETER                         (90000, "Some strings contain spaces before and after them : {0}"), // 일부 문자열의 앞,뒤에 공백이 포함되어있습니다 : {0}
    ELEVATION_REQUIRED                              (90001, "The requested operation requires elevation."), // 요청된 작업에는 권한 상승이 필요합니다.
    INVALID_LIST_ZERO                               (90002, "리스트의 갯수가 0개가 될 수 없습니다.")        
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
