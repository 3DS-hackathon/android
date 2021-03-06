package com.github.dan4ik95dv.app.util;

public class Constants {
    public static final String PACKAGE = "com.github.dan4ik95dv.app";
    public static final int SCHEMA_VERSION = 1;
    public static final String NAME_SCHEME = "app";
    public static final int SPLASH_DELAY = 3000;
    public static final int SHOW_TOAST_ERROR_INTERVAL = 5000;
    public static final int COUNT = 15;
    public static final String TASK_ID = "taskId";
    public static final String DEPARTMENT_ID = "departmentId";

    public static class Api {
        public static final int CONNECT_TIMEOUT = 10;
        public static final int READ_TIMEOUT = 30;
        public static final int WRITE_TIMEOUT = 10;

        public static final String DEVICE_TYPE = "android";
        public final static String API_V1_VERSION = "v1";
        public static final String DOMAIN_URL = "http://s3d.daniil-r.ru";
        public static final String API_URL = "http://s3d.daniil-r.ru/api/";
        public static final String API_URL_DEV = "http://s3d.daniil-r.ru/api/";

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
