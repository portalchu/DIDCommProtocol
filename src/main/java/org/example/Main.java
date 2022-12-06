package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner sc = new Scanner(System.in);
        int num;

        String serverDid = "did:example:alice";

        try {
            while (true)
            {
                System.out.println("1 : 서버 실행");
                System.out.println("2 : 클라이언트 실행");
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