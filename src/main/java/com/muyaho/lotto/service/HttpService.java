package com.muyaho.lotto.service;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class HttpService {
    public String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public String getBrowser(HttpServletRequest request) {
        String user_agent = request.getHeader("user-agent");
        String webKind = "";

        if (user_agent.toUpperCase().indexOf("MSIE") != -1) {
            if (user_agent.toUpperCase().indexOf("OPERA") != -1) {
                webKind = "Opera (MSIE/Opera/Compatible)";
            } else {
                webKind = "Internet Explorer (MSIE/Compatible)";
            }
        } else if (user_agent.toUpperCase().indexOf("SAFARI") != -1) {
            if (user_agent.toUpperCase().indexOf("CHROME") != -1) {
                webKind = "Google Chrome";
            } else {
                webKind = "Safari";
            }
        } else if (user_agent.toUpperCase().indexOf("GECKO") != -1) {
            if (user_agent.toUpperCase().indexOf("NESCAPE") != -1) {
                webKind = "Netscape (Gecko/Netscape)";
            } else if (user_agent.toUpperCase().indexOf("FIREFOX") != -1) {
                webKind = "Mozilla Firefox (Gecko/Firefox)";
            } else {
                webKind = "Mozilla (Gecko/Mozilla)";
            }
        } else if (user_agent.toUpperCase().indexOf("THUNDERBIRD") != -1) {
            webKind = "Thunderbird";
        } else {
            webKind = "Other Web Browsers";
        }
        return webKind;
    }

    public static String getDevice(HttpServletRequest request) {
        Device device = DeviceUtils.getCurrentDevice(request);
        if (device.isNormal()) {
            return "PC";
        } else if (device.isMobile()) {
            return "mobile";
        } else if (device.isTablet()) {
            return "tablet";
        }
        return "UNKNOWN";
    }
}
