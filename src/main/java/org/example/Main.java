package org.example;

import org.example.JNIModule.JNI;
import org.example.mock.DIDCommManager;

import java.util.Scanner;

public class Main {

    static String serverDid = "did:iot:gateway";
    static String clientDid = "did:iot:iot_device_touch_1";
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner sc = new Scanner(System.in);
        Gpio gpio = new Gpio();
        int num;

        try {
            while (true)
            {
                System.out.println("1 : 서버 실행");
                System.out.println("2 : 클라이언트 실행");
                System.out.println("3 : DIDComm 테스트");
                System.out.println("4 : DIDComm 테스트 2");
                System.out.println("5 : DID Server 실행");
                System.out.println("6 : DID Client 실행");
                System.out.println("8 : server DID 확인");
                System.out.println("9 : client DID 확인");
                System.out.println("10 : JNITest");
                System.out.println("11 : PI4J Test");
                System.out.println("12 : Button Test");
                System.out.println("13 : GPIO Client Test");
                System.out.println("0 : 종료");

                System.out.println("번호 입력 : ");
                num = sc.nextInt();

                switch (num) {
                    case 1:
                        Server.server();
                        break;
                    case 2:
                        Client.client();
                        break;
                    case 3:
                        System.out.println("input test did : ");
                        String testDid = sc.next();
                        System.out.println("test did : " + testDid);
                        DIDCommManager testDIDComm = new DIDCommManager(testDid);
                        System.out.println("input message : ");
                        String testMessage = sc.next();
                        testDIDComm.TestDIDComm(testMessage);
                        break;
                    case 4:
                        DIDCommManager testDIDComm2 = new DIDCommManager("test");
                        testDIDComm2.TCPDIDCommTest(serverDid, clientDid);
                        break;
                    case 5:
                        Server.DIDCommServer(serverDid);
                        break;
                    case 6:
                        Client.DIDCommClient(clientDid);
                        break;
                    case 8:
                        System.out.println("server DID : " + serverDid);
                        break;
                    case 9:
                        System.out.println("client DID : " + clientDid);
                        break;
                    case 10:
                        System.out.println("JNI Test");
                        JNI jni = new JNI();
                        System.out.println(jni.getSensorResult());
                        break;
                    case 11:
                        System.out.println("PI4J Test");
                        gpio.gpio();
                        break;
                    case 12:
                        System.out.println("Button Test");
                        gpio.gpioButton();
                        break;
                    case 13:
                        System.out.println("GPIO Client Test");
                        Client.DIDCommGpioClient(clientDid);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("잘못된 입력 값");
                        break;
                }

            }

        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }

    }
}
