package com.gstock.kis;

public final class KisConstants {
    public static final String DEFAULT_ERR_MSG = "system.error";
    public static final String PROJECT_NAME = "GSTOCK";
    public static final String ADMINISTRATOR = "GH";
    public static final String REST_BASE_URL = "https://openapi.koreainvestment.com:9443";
    public final static String GRANT_INFO = "client_credentials";

    public final static String APP_KEY = "PSO4fj0gaiojDFaNm6vOQbuvrXk8PamP0dpm";
    public final static String APP_SECRET_KEY = "5Uv5dsYod3QYTMDQsyayjmdeEoyMYf2x9C77uhpGZdKGx5USsBwx7m9PnlkaiYw7+JaGzKg6nXeFYGPk4LaGJ16PRBO8WT2ioGZFR/QXG8MWUuysou0IoxAyUQkb70Pqm00xCSrhg2WdQNY59HWO7DJ+oibjq0O4r0OanAKjiKzKA21Y8GA=";

    //STOCK INFO
    public final static String DOMESTIC_STOCK_INFO_URL = "/uapi/domestic-stock/v1/quotations/search-stock-info";
    public final static String DOMESTIC_STOCK_INFO_KEY = "CTPF1002R";

    public final static String TOTAL_STOCK_INFO_URL = "/uapi/domestic-stock/v1/quotations/search-info";
    public final static String TOTAL_STOCK_INFO_KEY = "CTPF1604R";
    public final static String STOCK_INFO_PRODUCT_TYPE_CODE = "prdt_type_cd=";
    public final static String STOCK_INFO_PRODUCT_NO = "pdno=";

    public final static String DOMESTIC_STOCK_INFO_QUERY_KR = "?prdt_type_cd=300&pdno=";
    public final static String DOMESTIC_STOCK_INFO_QUERY_US = "?prdt_type_cd=512&pdno=";

    //STOCK PRICE
    public final static String DOMESTIC_STOCK_PRICE_URL = "/uapi/domestic-stock/v1/quotations/inquire-price";
    public final static String DOMESTIC_STOCK_PRICE_KEY = "FHKST01010100";
    public final static String DOMESTIC_STOCK_PRICE_QUERY = "?fid_cond_mrkt_div_code=J&fid_input_iscd=";
    public final static String OVERSEAS_STOCK_PRICE_URL = "/uapi/overseas-price/v1/quotations/price";
    public final static String OVERSEAS_STOCK_PRICE_KEY = "HHDFS00000300";
    public final static String OVERSEAS_STOCK_PRICE_QUERY = "?auth=&excd=NAS&symb=";

    public static final String DOMESTIC = "300";
    public static final String NASDAQ = "512";
    public static final String NEWYORK = "513";
    public static final String NAS = "NAS";
    public static final String AMEX= "529";
    public static final String AME = "AMS";
    public static final String KOR = "KOR";
    public static final String FORE = "F";
    public static final String DOME = "D";

    public static final String KIS_MSG_CD_FAIL = "KIER2620";

}
