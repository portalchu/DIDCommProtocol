package org.example.mock;

import org.didcommx.didcomm.common.VerificationMaterial;
import org.didcommx.didcomm.common.VerificationMaterialFormat;
import org.didcommx.didcomm.common.VerificationMethodType;
import org.didcommx.didcomm.diddoc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DIDDocResolverMock implements DIDDocResolver {

    private List<DIDDoc> didDocList = new ArrayList<>();

    public DIDDocResolverInMemory didDocResolverInMemory;

    public void SetDIDDoc() {
        VerificationMethod AliceMethod = new VerificationMethod(
                "did:iot:gateway#key-1",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"OKP\"," +
                                "\"crv\":\"Ed25519\"," +
                                "\"x\":\"1eESIYXnbLGwyNPeH0Nwxasd7exQJR2UD1OBGqoZDcg\"}"
                ),
                "did:iot:gateway#key-1"
        );

        VerificationMethod AliceMethod1 = new VerificationMethod(
                "did:iot:gateway#key-2",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"OKP\"," +
                                "\"crv\":\"X25519\"," +
                                "\"x\":\"F0g1QxzOMqTo00hg6PIf4zHY0_6FMe_OBujYsenYz3Q\"}"
                ),
                "did:iot:gateway#key-2"
        );

        VerificationMethod AliceMethod2 = new VerificationMethod(
                "did:iot:gateway#key-3",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"EC\"," +
                                "\"crv\":\"P-256\"," +
                                "\"x\":\"ivYUvybjaokTJantAbzGg96L4qkCjngDbliNp3yPkzM\"," +
                                "\"y\":\"ZtMsrzFOx-kdqQd_jJc2TnN_ASFJc2m0C7R2VhkfSJs\"}"
                ),
                "did:iot:gateway#key-3"
        );

        VerificationMethod AliceMethod3 = new VerificationMethod(
                "did:iot:gateway#key-4",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"EC\"," +
                                "\"crv\":\"P-384\"," +
                                "\"x\":\"wKqfYznOMAtdHuMfzn3kxSXj-em2PHnzBRwalbJZRVfnrvO5zMgpwL0cvBw89QML\"," +
                                "\"y\":\"QLmGVThWGUF3yOo1WdBuuepKygs4xOnpZErxJrp33UjST8uaF75l7RR5YdDcuxPs\"}"
                ),
                "did:iot:gateway#key-4"
        );

        VerificationMethod AliceMethod4 = new VerificationMethod(
                "did:iot:gateway#key-5",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"EC\"," +
                                "\"crv\":\"P-521\"," +
                                "\"x\":\"AdhTtFmjcApJOXNNH9DASL1V6_q3Vs_PUVX-5HxVMywPtX7dAO02_kUBej4Wf7hbwNXktnAkn-YXrOohGQ9IBMPS\"," +
                                "\"y\":\"AaG7be12d_uptxBUL1p9cey0TRTR5mxVMfe8OxZUjrRUgFmguBzEKUgPIIG9WQofvbjKxPcLVjHrPwGBH8QsHmcW\"}"
                ),
                "did:iot:gateway#key-5"
        );

        VerificationMethod AliceMethod6 = new VerificationMethod(
                "did:iot:gateway#key-6",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"EC\"," +
                                "\"crv\":\"secp256k1\"," +
                                "\"x\":\"YtMKHzQ7XfvEXGE_XvDNPYxhdvOGZes0UlNqkzXUNSM\"," +
                                "\"y\":\"M37EnuxZv85ucjHINqKadm9Y84t97hn8P5KRyxZIlmE\"}"
                ),
                "did:iot:gateway#key-6"
        );

        VerificationMethod BobMethod1 = new VerificationMethod(
                "did:iot:iot_device_touch_1#key-1",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"OKP\"," +
                                "\"crv\":\"Ed25519\"," +
                                "\"x\":\"n7R4go0dfPvU2MVD56nz-osbSfYxi0sb7di-oKcsX7k\"}"
                ),
                "did:iot:iot_device_touch_1#key-1"
        );

        VerificationMethod BobMethod2 = new VerificationMethod(
                "did:iot:iot_device_touch_1#key-2",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"OKP\"," +
                                "\"crv\":\"X25519\"," +
                                "\"x\":\"gL9h8PIoyDbWYqPJuV9oLlq_Y4jOHVFO2EspbgO3fyE\"}"
                ),
                "did:iot:iot_device_touch_1#key-2"
        );

        VerificationMethod BobMethod3 = new VerificationMethod(
                "did:iot:iot_device_touch_1#key-3",
                VerificationMethodType.JSON_WEB_KEY_2020,
                new VerificationMaterial(
                        VerificationMaterialFormat.JWK,
                        "{\"kty\":\"EC\"," +
                                "\"crv\":\"P-256\"," +
                                "\"x\":\"FQVaTOksf-XsCUrt4J1L2UGvtWaDwpboVlqbKBY2AIo\"," +
                                "\"y\":\"6XFB9PYo7dyC5ViJSO9uXNYkxTJWn0d_mqJ__ZYhcNY\"}"
                ),
                "did:iot:iot_device_touch_1#key-3"
        );

        List<VerificationMethod> verificationMethodList = new ArrayList<>();
        verificationMethodList.add(AliceMethod);
        verificationMethodList.add(AliceMethod1);
        verificationMethodList.add(AliceMethod2);
        verificationMethodList.add(AliceMethod3);
        verificationMethodList.add(AliceMethod4);
        verificationMethodList.add(AliceMethod6);

        List<DIDCommService> didCommServiceList = new ArrayList<>();

        List<String> keyAgreementList = new ArrayList<>();
        keyAgreementList.add("did:iot:gateway#key-2");
        keyAgreementList.add("did:iot:gateway#key-3");
        keyAgreementList.add("did:iot:gateway#key-4");
        keyAgreementList.add("did:iot:gateway#key-5");

        List<String> authentications = new ArrayList<>();
        authentications.add("did:iot:gateway#key-1");
        authentications.add("did:iot:gateway#key-3");
        authentications.add("did:iot:gateway#key-6");
        authentications.add("did:iot:gateway#key-6");

        DIDDoc didDoc1 = new DIDDoc(
                "did:iot:gateway",
                keyAgreementList,
                authentications,
                verificationMethodList,
                didCommServiceList
        );

        List<VerificationMethod> verificationMethodList2 = new ArrayList<>();
        verificationMethodList2.add(BobMethod1);
        verificationMethodList2.add(BobMethod2);
        verificationMethodList2.add(BobMethod3);

        List<String> keyAgreementList2 = new ArrayList<>();
        keyAgreementList2.add("did:iot:iot_device_touch_1#key-2");
        keyAgreementList2.add("did:iot:iot_device_touch_1#key-3");

        List<String> authentications2 = new ArrayList<>();
        authentications2.add("did:iot:iot_device_touch_1#key-1");
        authentications2.add("did:iot:iot_device_touch_1#key-3");

        DIDDoc didDoc2 = new DIDDoc(
                "did:iot:iot_device_touch_1",
                keyAgreementList2,
                authentications2,
                verificationMethodList2,
                didCommServiceList
        );

        didDocList.add(didDoc1);
        didDocList.add(didDoc2);

        didDocResolverInMemory = new DIDDocResolverInMemory(didDocList);
    }

    @Override
    public Optional<DIDDoc> resolve(String did) {
        return didDocResolverInMemory.resolve(did);
    }
}
