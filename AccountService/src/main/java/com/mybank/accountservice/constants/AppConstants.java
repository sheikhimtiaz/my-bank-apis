package com.mybank.accountservice.constants;

public class AppConstants {
    static public String ERROR = "error";
    static public String SUCCESS = "success";
    static public String STATUS = "status";
    static public String STATUS_TYPE = "statusType";
    static public String MESSAGE = "message";
    static public String DATA = "data";
    static public String TOTAL = "total";
    static public int SC_SERVER_ERROR = 500;
    static public int SC_BAD_REQUEST = 400;
    static public int SC_NOT_ACCEPTABLE = 406;
    static public int SC_OK = 200;
    static public String TOPIC_EXCHANGE_ACCOUNT = "bank-user-account";
    static public String TOPIC_EXCHANGE_TRANSACTION = "bank-user-transaction";
    static public String TOPIC_EXCHANGE = "bank-user";
    static public String ROUTING_KEY_ACCOUNT = "account";
    static public String ROUTING_KEY_TRANSACTION = "transaction";
    static public String DIRECTION_IN = "IN";
    static public String DIRECTION_OUT = "OUT";
    static public String[] ALLOWED_CURRENCY = new String[]{"EUR", "SEK", "GBP", "USD"};

}
