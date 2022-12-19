package org.example.mock;

import org.didcommx.didcomm.common.VerificationMaterial;
import org.didcommx.didcomm.common.VerificationMaterialFormat;
import org.didcommx.didcomm.common.VerificationMethodType;
import org.didcommx.didcomm.secret.Secret;
import org.didcommx.didcomm.secret.SecretResolver;
import org.didcommx.didcomm.secret.SecretResolverInMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BobSecretResolverMock implements SecretResolver {

    private List<Secret> secrets;

    public SecretResolverInMemory secretResolverInMemory;

    public Secret secret1 = new Secret(
            "did:example:bob#key-1",
            VerificationMethodType.JSON_WEB_KEY_2020,
            new VerificationMaterial(
                    VerificationMaterialFormat.JWK,
                    "{\"kty\":\"OKP\"," +
                            "\"d\":\"Z0vrri4NNkinrZ8EY4rG9Xr5yGApoejFZbrOBG6iyYM\"," +
                            "\"crv\":\"Ed25519\"," +
                            "\"x\":\"gL9h8PIoyDbWYqPJuV9oLlq_Y4jOHVFO2EspbgO3fyE\"}"
            )
    );

    public Secret secret2 = new Secret(
            "did:example:bob#key-2",
            VerificationMethodType.JSON_WEB_KEY_2020,
            new VerificationMaterial(
                    VerificationMaterialFormat.JWK,
                    "{\"kty\":\"OKP\"," +
                            "\"d\":\"Z0vrri4NNkinrZ8EY4rG9Xr5yGApoejFZbrOBG6iyYM\"," +
                            "\"crv\":\"X25519\"," +
                            "\"x\":\"gL9h8PIoyDbWYqPJuV9oLlq_Y4jOHVFO2EspbgO3fyE\"}"
            )
    );

    public Secret secret3 = new Secret(
            "did:example:bob#key-3",
            VerificationMethodType.JSON_WEB_KEY_2020,
            new VerificationMaterial(
                    VerificationMaterialFormat.JWK,
                    "{\"kty\":\"EC\"," +
                            "\"d\":\"PgwHnlXxt8pwR6OCTUwwWx-P51BiLkFZyqHzquKddXQ\"," +
                            "\"crv\":\"P-256\"," +
                            "\"x\":\"FQVaTOksf-XsCUrt4J1L2UGvtWaDwpboVlqbKBY2AIo\"," +
                            "\"y\":\"6XFB9PYo7dyC5ViJSO9uXNYkxTJWn0d_mqJ__ZYhcNY\"}"
            )
    );

    public void SetSecret() {
        secrets = new ArrayList<>();
        secrets.add(secret1);
        secrets.add(secret2);
        secrets.add(secret3);

        secretResolverInMemory = new SecretResolverInMemory(secrets);
    }

    @Override
    public Set<String> findKeys(List<String> list) {
        return secretResolverInMemory.findKeys(list);
    }

    @Override
    public Optional<Secret> findKey(String s) {
        return secretResolverInMemory.findKey(s);
    }

}
