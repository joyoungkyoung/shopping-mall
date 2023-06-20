package com.nicky.shoppingmall.config;

public class Constant {
    public static final String API_VER1 = "/api/v1";
    public static final String HAS_ROLE_ALL = "hasAnyAuthority('ROLE_OWNER', 'ROLE_STAFF', 'ROLE_VIEWER')";
    public static final String HAS_ENABLE_UPDATE = "hasAnyAuthority('ROLE_OWNER', 'ROLE_STAFF')";
    public static final String HAS_ONLY_VIEW = "hasAnyAuthority('ROLE_VIEWER')";
}
