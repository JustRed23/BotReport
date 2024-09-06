package dev.JustRed23.botreport.config;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class Config {

    public Detection detection;
    public Action action;
    public Notification notification;

    public static class Detection {
        public int maxFailedAttempts;
        public int timeWindow;
    }

    public static class Action {
        public Block block;
        public API report;

        public static class Block {
            public boolean enabled;
            public int time;
        }
    }

    public static class Notification {
        public boolean logDetections;
        public boolean storeDetections;
        public API notifyApi;
    }

    public static class API {
        public boolean enabled;
        public String url;
        public HttpMethod method;
        public List<Header> headers;
        public String data;
        public int timeout;

        public enum HttpMethod {
            GET, POST
        }

        public static class Header {
            public String key;
            public String value;
        }
    }
}
