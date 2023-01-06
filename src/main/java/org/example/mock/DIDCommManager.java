package org.example.mock;

import org.didcommx.didcomm.DIDComm;
import org.didcommx.didcomm.message.Message;
import org.didcommx.didcomm.model.PackEncryptedParams;
import org.didcommx.didcomm.model.PackEncryptedResult;
import org.didcommx.didcomm.model.UnpackParams;
import org.didcommx.didcomm.model.UnpackResult;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DIDCommManager {

    public String did;

    private DIDComm didComm;

    public DIDCommManager(String did) {
        this.did = did;

        DIDDocResolverMock didDocResolverMock = new DIDDocResolverMock();
        didDocResolverMock.SetDIDDoc();

        if(this.did.equals("did:iot:iot_device_touch_1"))
        {
            System.out.println("did1");
            BobSecretResolverMock secretResolverInMemoryMock = new BobSecretResolverMock();
            secretResolverInMemoryMock.SetSecret();
            this.didComm = new DIDComm(didDocResolverMock, secretResolverInMemoryMock);
        }
        else if(this.did.equals("did:iot:gateway"))
        {
            System.out.println("did2");
            AliceSecretResolverMock secretResolverInMemoryMock = new AliceSecretResolverMock();
            secretResolverInMemoryMock.SetSecret();
            this.didComm = new DIDComm(didDocResolverMock, secretResolverInMemoryMock);
        }
        else
        {
            System.out.println("did3");
            SecretResolverInMemoryMock secretResolverInMemoryMock = new SecretResolverInMemoryMock();
            secretResolverInMemoryMock.SetSecret();
            this.didComm = new DIDComm(didDocResolverMock, secretResolverInMemoryMock);
        }
    }

    public String messageEncryption (String resiverDid, String message) throws Exception {

        JSONObject messageJsonObj = new JSONObject();
        messageJsonObj.put("id", this.did);
        messageJsonObj.put("controller", "did:deu:alice1234");
        messageJsonObj.put("body", message);

        Map<String, String> body = new HashMap<>();
        body.put("message", messageJsonObj.toString());

        List<String> to = new ArrayList<>();
        to.add(resiverDid);

        long time = System.currentTimeMillis();
        double randomNumber = Math.random() * 1000000;
        String randomNumberString = Double.toString(randomNumber);

        Message didcommMessage = Message.Companion.builder(
                        randomNumberString, body, "http://example.com/protocols/lets_do_lunch/1.0/proposal")
                .from(this.did)
                .to(to)
                .createdTime(time)
                .expiresTime(1706943600L)
                .build();

        System.out.println("didcommMessage : " + didcommMessage.toString());

        PackEncryptedParams packEncryptedParams = PackEncryptedParams.Companion.builder(
                        didcommMessage, resiverDid)
                .from(this.did)
                .build();

        PackEncryptedResult packEncryptedResult = didComm.packEncrypted(packEncryptedParams);

        System.out.println("packEncryptedResult : " + packEncryptedResult.toString());

        return packEncryptedResult.getPackedMessage();
    }

    public String messageDecryption (String message) throws Exception {

        UnpackParams unpackParams = new UnpackParams.Builder(message)
                .build();

        UnpackResult unpackResult = didComm.unpack(unpackParams);

        String unpackMessage = unpackResult.getMessage().toString();

        System.out.println("UnPack Message : " + unpackMessage);

        String unpackMessage2 = unpackResult.getMessage().getBody().toString();

        System.out.println("UnPack Message : " + unpackMessage2);

        return unpackMessage;
    }

    public void TestDIDComm(String message) throws Exception {
        System.out.println("========== Test DIDComm ==========");

        System.out.println("input message : " + message);

        String testEncryMsg = messageEncryption(this.did, message);
        System.out.println("testEncryMsg : " + testEncryMsg);

        String testDecryMsg = messageDecryption(testEncryMsg);
        System.out.println("testDecryMsg : " + testDecryMsg);
    }

    public void TCPDIDCommTest(String serverDid, String clientDid) throws Exception {
        System.out.println("========== TCP DIDComm Test ==========");

        DIDCommManager serverDIDComm = new DIDCommManager(serverDid);
        DIDCommManager clientDIDComm = new DIDCommManager(clientDid);

        String testMsg = "Hello";

        String encryTestMsg = clientDIDComm.messageEncryption(serverDid, testMsg);
        System.out.println("encryTestMsg : " + encryTestMsg);

        String decryTestMsg = serverDIDComm.messageDecryption(encryTestMsg);
        System.out.println("decryTestMsg : " + decryTestMsg);
    }
}
