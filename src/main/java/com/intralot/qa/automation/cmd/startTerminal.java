package com.intralot.qa.automation.cmd;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import com.jcraft.jsch.*;

public class startTerminal {
    /**
     * Java SSH Connection Program
     */


    public static void terminalStart() throws Exception{
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        Session session = null;
        ChannelShell channel = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession("qauser", "10.85.50.6", 22);
            session.setConfig(config);
            session.setPassword("123456q!");
            session.connect();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            channel = (ChannelShell) session.openChannel("shell");
            channel.setOutputStream(outputStream);
            PrintStream stream = new PrintStream(channel.getOutputStream());
            channel.connect();

            stream.println("cd auto_update/taiwan_terminal_ui_2/");
            stream.flush();
            String response = waitForPrompt(outputStream, "$");
            System.out.println(response);


            stream.println("pm2 start npm --name \"taiwan terminal port 8201\" -- start");
            stream.flush();
            response = waitForPrompt(outputStream, "$");
            System.out.println(response);


        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    static public String waitForPrompt(ByteArrayOutputStream outputStream, String prompt) throws Exception {
        int retries = 3;
        for (int x = 1; x < retries; x++) {
            TimeUnit.SECONDS.sleep(1);
            if (outputStream.toString().indexOf(prompt) > 0) {
                String responseString = outputStream.toString();
                outputStream.reset();
                return responseString;
            }
        }
        throw new Exception("Prompt failed to show after specified timeout");
    }

}
