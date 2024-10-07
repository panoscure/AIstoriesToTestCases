package com.intralot.qa.automation.api.lottery;

import com.intralot.qa.automation.core.utilities.CustomProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeadersQueryAndFormData {

    public static Map<String, String> getAuthOnlineHeaders() {
        return new HashMap<String, String>() {{
            put("Authorization", CustomProperties.getPropertyValue("basicAuth"));
            //put("Guid", UUID.randomUUID().toString());
            put("Content-Type", "application/json");
            //put("Content-Type", "application/x-www-form-urlencoded");
            put("Accept", "application/json");

        }};
    }

    public static Map<String, String> getAuthDataOnlineQueryParameters() {
        return new HashMap<String, String>() {{
            put("grant_type","client_credentials");
        }};
    }

    public static Map<String, String> getActiveDrawMobileHeaders(String bearerToken) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("mobileChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("operatorDC"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        return headersHashMap;
    }

    public static Map<String, String> getAuthenticationSignOnHeaders(String bearerToken) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("webChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("operatorDC"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        return headersHashMap;
    }

    public static String getAuthenticationSignOnBody(String username) {
        return "{\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"password\": \"" + CustomProperties.getPropertyValue("BETUSRPSW1") + "\"\n" +
                "}";

    }

    public static Map<String, String> getUpdateProfileHeaders(String bearerToken, String sessionToken) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("sessionToken", sessionToken);
        headersHashMap.put("Authorization", bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("webChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("operatorDC"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        return headersHashMap;
    }

    public static String getUpdateProfileBody(String birthDate) {
        return "{\n" +
                "  \"birthDate\": \"" + birthDate + "\",\n" +
                "  \"currentPassword\": \"" + CustomProperties.getPropertyValue("BETUSRPSW1") + "\"\n" +
                "}";

    }


    ////////////////////////////////////////////////////////////////
    //General Methods for potential future usage////////////////////
    ////////////////////////////////////////////////////////////////
    public static Map<String, String> playVerifyWagerOnlineHeaders(String bearerToken) {
        HashMap<String, String> headersHashMap = new HashMap<>();
            headersHashMap.put("Authorization", "Bearer " + bearerToken);
            headersHashMap.put("Channel", CustomProperties.getPropertyValue("webChannel"));
            headersHashMap.put("Operator", CustomProperties.getPropertyValue("grOperator"));
            headersHashMap.put("Guid", UUID.randomUUID().toString());
            headersHashMap.put("Content-Type", "application/json");
            headersHashMap.put("Accept", "application/json");
            //headersHashMap.put("ParticipationMethod", "verbal");
            return headersHashMap;
    }


    public static Map<String, String> playVerifyWagerTerminalHeaders(String bearerToken, String agentId, String terminalId) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", "Bearer " + bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("terminalChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("grOperator"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("RetailerId", agentId);
        headersHashMap.put("TerminalId", terminalId);
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        //headersHashMap.put("ParticipationMethod", "verbal");
        return headersHashMap;
    }
    public static Map<String, String> payWagerOldTerminalHeaders(String bearerToken, String agentId, String terminalId) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", "Bearer " + bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("terminalChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("grOperator"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("RetailerId", agentId);
        headersHashMap.put("TerminalId", terminalId);
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        //headersHashMap.put("ParticipationMethod", "verbal");
        return headersHashMap;
    }





    public static Map<String, String> playVerifyWagerTerminalHeadersCyprus(String bearerToken, String agentId, String terminalId) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", "Bearer " + bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("terminalChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("cyOperator"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("RetailerId", agentId);
        headersHashMap.put("TerminalId", terminalId);
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        //headersHashMap.put("ParticipationMethod", "verbal");
        return headersHashMap;
    }
    public static Map<String, String> playVerifyWagerSsbtHeaders(String bearerToken, String agentId, String terminalId) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", "Bearer " + bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("ssbtChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("grOperator"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("RetailerId", agentId);
        headersHashMap.put("TerminalId", terminalId);
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        //headersHashMap.put("ParticipationMethod", "verbal");
        return headersHashMap;
    }

    public static Map<String, String> playVerifyWagerByodHeaders(String bearerToken, String agentId, String terminalId) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", "Bearer " + bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("byodChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("grOperator"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("RetailerId", agentId);
        headersHashMap.put("TerminalId", terminalId);
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        //headersHashMap.put("ParticipationMethod", "verbal");
        return headersHashMap;
    }
    public static Map<String, String> playVerifyWagerMobileHeaders(String bearerToken) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Authorization", "Bearer " + bearerToken);
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("mobileChannel"));
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("grOperator"));
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Accept", "application/json");
        //headersHashMap.put("ParticipationMethod", "verbal");
        return headersHashMap;
    }

    public static Map<String, String> payWagerHeaders(String bearerToken) {
        HashMap<String, String> headersHashMap = new HashMap<>();
        headersHashMap.put("Content-Type", "application/json");
        headersHashMap.put("Authorization", "Bearer " + bearerToken);
        headersHashMap.put("Guid", UUID.randomUUID().toString());
        headersHashMap.put("Operator", CustomProperties.getPropertyValue("grOperator"));
        headersHashMap.put("Channel", CustomProperties.getPropertyValue("terminalChannel"));
        headersHashMap.put("Accept", "application/json");
        headersHashMap.put("payment-guid", "pelops-1");
        headersHashMap.put("Cookie", "1a4e2f1ab4ab64ca3fad60ddd4681106=c1a30320114a68ac2f523e5f6e79093e");
        //headersHashMap.put("RetailerId", agentId);
        //headersHashMap.put("TerminalId", terminalId);
        //headersHashMap.put("ParticipationMethod", "verbal");
        return headersHashMap;
    }


}