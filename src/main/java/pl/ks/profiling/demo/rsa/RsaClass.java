package pl.ks.profiling.demo.rsa;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;

class RsaClass {
    private final RSAPrivateKey privateKey;

    @SneakyThrows
    RsaClass()  {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(
                new BigInteger("57791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb1", 16),
                new BigInteger("57791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb1", 16)
        );

        privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
    }

    @SneakyThrows
    byte[] cypher() {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(RandomStringUtils.random(10).getBytes());
    }
}
