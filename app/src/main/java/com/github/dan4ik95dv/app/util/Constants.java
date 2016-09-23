package com.github.dan4ik95dv.app.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    public static final String PACKAGE = "com.github.dan4ik95dv.app";
    public static final int SCHEMA_VERSION = 1;
    public static final String NAME_SCHEME = "app";

    public static class Api {
        public static final int CONNECT_TIMEOUT = 10;
        public static final int READ_TIMEOUT = 30;
        public static final int WRITE_TIMEOUT = 10;

        public static final String DEVICE_TYPE = "android";
        public final static String API_V1_VERSION = "v1";
        public static final String API_URL = "http://...";
        public static final String API_URL_DEV = "http://...";

        public static final String SERVICE_RECEIVER = "ApiServiceReceiver";

        public static long CONNECTION_TIMEOUT = 10000;


        public static class Errors {
            public static final String INVALIDE_TOKEN = "InvalideToken";
        }
    }

    public static class IntentExtras {
        public static final String BUNDLE = "BUNDLE";
    }

    public static class Configs {
        public static final String TOKEN = "TOKEN";
        public static final String START_FIRST = "START_FIRST";
    }

}
