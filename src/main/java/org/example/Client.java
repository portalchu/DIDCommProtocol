package org.example;

import org.example.mock.DIDCommManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void client() {

        // 클라이언트 소켓 생성

        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        OutputStream os = null;
        OutputStreamWriter osw = null;
        PrintWriter pw = null;

        // new InetSocketAddress(InetAddress.getLocalHost() 6077

        try {
            socket.connect(new InetSocketAddress("220.68.5.140",6077), 6077);
            System.out.println("[client] connected with server");

            while (true) {

                is = socket.getInputStream();
                isr = new InputStreamReader(is, "UTF-8");
                br = new BufferedReader(isr);

                os = socket.getOutputStream();
                osw = new OutputStreamWriter(os, "UTF-8");
                pw = new PrintWriter(osw, true);

                // 읽는거
                System.out.print(">>");
                String data = sc.nextLine();

                if ("exit".equals(data))
                    break;

                // DIDComm 적용 위치?

                pw.println(data);

                data = br.readLine();
                System.out.println("<< " + data);

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            sc.close();

        }

    }

    public static void DIDCommClient(String did) {
        System.out.println("DIDComm Client did is " + did);

        //
        DIDCommManager didCommManager = new DIDCommManager(did);

        // 클라이언트 소켓 생성

        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        OutputStream os = null;
        OutputStreamWriter osw = null;
        PrintWriter pw = null;

        // new InetSocketAddress(InetAddress.getLocalHost() 6077

        try {
            socket.connect(new InetSocketAddress("220.68.5.140",6077), 6077);
            System.out.println("[client] connected with server");

            while (true) {

                is = socket.getInputStream();
                isr = new InputStreamReader(is, "UTF-8");
                br = new BufferedReader(isr);

                os = socket.getOutputStream();
                osw = new OutputStreamWriter(os, "UTF-8");
                pw = new PrintWriter(osw, true);

                // 읽는거
                System.out.print(">>");
                String data = sc.nextLine();

                if ("exit".equals(data))
                    break;

                // DIDComm 적용 위치?
                String encryData = didCommManager.messageEncryption(Main.serverDid, data);
                System.out.println("encryData : " + encryData);

                pw.println(encryData);

                data = br.readLine();
                System.out.println("<< " + data);

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            sc.close();

        }

    }
}
