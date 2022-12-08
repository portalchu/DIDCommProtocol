package org.example.mock;

import org.didcommx.didcomm.DIDComm;
import org.didcommx.didcomm.message.Message;
import org.didcommx.didcomm.model.PackEncryptedParams;
import org.didcommx.didcomm.model.PackEncryptedResult;
import org.didcommx.didcomm.model.UnpackParams;
import org.didcommx.didcomm.model.UnpackResult;

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

        if(this.did == "did:example:bob")
        {
            BobSecretResolverMock secretResolverInMemoryMock = new BobSecretResolverMock();
            secretResolverInMemoryMock.SetSecret();
            this.didComm = new DIDComm(didDocResolverMock, secretResolverInMemoryMock);
        }
        if(this.did == "did:example:alice")
        {
            AliceSecretResolverMock secretResolverInMemoryMock = new AliceSecretResolverMock();
            secretResolverInMemoryMock.SetSecret();
            this.didComm = new DIDComm(didDocResolverMock, secretResolverInMemoryMock);
        }
        else
        {
            SecretResolverInMemoryMock secretResolverInMemoryMock = new SecretResolverInMemoryMock();
            secretResolverInMemoryMock.SetSecret();
            this.didComm = new DIDComm(didDocResolverMock, secretResolverInMemoryMock);
        }
    }

    public String messageEncryption (String resiverDid, String message) throws Exception {

        Map<String, String> body = new HashMap<>();
        body.put("message", message);

        List<String> to = new ArrayList<>();
        to.add(resiverDid);

        Message didcommMessage = Message.Companion.builder(
                        "1234", body, "http://example.com/protocols/lets_do_lunch/1.0/proposal")
                .from(this.did)
                .to(to)
                .createdTime(1546521l)
                .expiresTime(1543215l)
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

        return unpackMessage;
    }
}
