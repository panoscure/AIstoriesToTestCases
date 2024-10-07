package com.intralot.qa.automation.api.CashTransfer;

import com.intralot.qa.automation.core.utilities.CustomProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeadersBody {



    public static Map<String, String> getCashHeaders() {
        return new HashMap<String, String>() {{
            //put("Channel", "100");
            //put("Operator", "1");
            put("guid", UUID.randomUUID().toString());
            put("Content-Type", "application/json");
            put("Accept", "application/json");

        }};
    }



    public static String getCashTransferApproveBody(String transactionID) {
        return "{\n" +
                "  \"transactionId\": "+transactionID+",\n" +
                "  \"retailerId\": "+ CustomProperties.getPropertyValue("retailer.id")+",\n" +
                "  \"amount\": "+CustomProperties.getPropertyValue("cash.amount")+"\n" +
                "}";

    }

    public static String getResetAllLimitsBody() {
        //Reset Play/Loss/Deposit/Withdraw/Session Limits
        return "[\n" +
                "  {\n" +
                "    \"duration\": 0,\n" +
                "    \"groupId\": 1,\n" +
                "    \"value\": 30000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 1,\n" +
                "    \"groupId\": 1,\n" +
                "    \"value\": 30000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 2,\n" +
                "    \"groupId\": 1,\n" +
                "    \"value\": 30000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 0,\n" +
                "    \"groupId\": 4,\n" +
                "    \"value\": 1000000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 1,\n" +
                "    \"groupId\": 4,\n" +
                "    \"value\": 1000000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 2,\n" +
                "    \"groupId\": 4,\n" +
                "    \"value\": 1000000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 0,\n" +
                "    \"groupId\": 7,\n" +
                "    \"value\": 86400\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 1,\n" +
                "    \"groupId\": 7,\n" +
                "    \"value\": 604800\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 2,\n" +
                "    \"groupId\": 7,\n" +
                "    \"value\": 2592000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 0,\n" +
                "    \"groupId\": 8,\n" +
                "    \"value\": 2000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 1,\n" +
                "    \"groupId\": 8,\n" +
                "    \"value\": 2000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 2,\n" +
                "    \"groupId\": 8,\n" +
                "    \"value\": 2000\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 0,\n" +
                "    \"groupId\": 9,\n" +
                "    \"value\": 9999900\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 1,\n" +
                "    \"groupId\": 9,\n" +
                "    \"value\": 9999900\n" +
                "  },\n" +
                "  {\n" +
                "    \"duration\": 2,\n" +
                "    \"groupId\": 9,\n" +
                "    \"value\": 9999900\n" +
                "  }\n" +
                "]";

    }


    public static Map<String, String> loginPamBOHeaders() {
        return new HashMap<String, String>() {
            {
                put("x-csrftoken", "foo");
                put("Cookie", "csrftoken=foo");
                put("Content-Type", "application/json");
            }
        };
    }

    public static Map<String, String> playerDataHeaders(String setCookie) {
        return new HashMap<String, String>() {
            {
                put("Cookie", setCookie);
            }
        };
    }

    public static Map<String, String> playerDataQueryParameters(String param, String value) {
        return new HashMap<String, String>() {
            {
                put(param, value);
            }
        };
    }

    public static Map<String, String> playerDataHeadersRedirected(String refererUrl, String cookieBuilder, String playerName) {
        return new HashMap<String, String>() {
            {
                put("Host", refererUrl.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)",""));
                put("Cookie", cookieBuilder);
                put("Referer", refererUrl + "/cc/customer/rest/v1/customers?filterBy=search&searchTerm=" + playerName);
            }
        };
    }

    public static Map<String, String> playerSMSCommunicationDataHeadersRedirected(String refererUrl, String cookieBuilder, String playerId) {
        return new HashMap<String, String>() {
            {
                put("Host", refererUrl.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)",""));
                put("Cookie", cookieBuilder);
                put("Referer", refererUrl + "/cc/communication/rest/v1/sms?playerId=" + playerId);
            }
        };
    }

    public static Map<String, String> playerEmailCommunicationDataHeadersRedirected(String refererUrl, String cookieBuilder, String playerId) {
        return new HashMap<String, String>() {
            {
                put("Host", refererUrl.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)",""));
                put("Cookie", cookieBuilder);
                put("Referer", refererUrl + "/cc/communication/rest/v1/emails?playerId=" + playerId);
            }
        };
    }

}
