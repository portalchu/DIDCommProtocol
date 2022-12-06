package org.example.mock;

import org.didcommx.didcomm.DIDComm;
import org.didcommx.didcomm.message.Message;
import org.didcommx.didcomm.model.PackEncryptedParams;
import org.didcommx.didcomm.model.PackEncryptedResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DIDCommManager {

    public String did;
    public String didKey;

    private DIDComm didComm;

    public DIDCommManager(String did, String didKey) {
        this.did = did;
        this.didKey = didKey;

        DIDDocResolverMock didDocResolverMock = new DIDDocResolverMock();
        didDocResolverMock.SetDIDDoc();

        SecretResolverInMemoryMock secretResolverInMemoryMock = new SecretResolverInMemoryMock();
        secretResolverInMemoryMock.SetSecret();

        this.didComm = new DIDComm(didDocResolverMock, secretResolverInMemoryMock);
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
}
