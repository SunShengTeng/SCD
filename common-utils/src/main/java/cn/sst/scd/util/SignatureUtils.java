package cn.sst.scd.util;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/1 2:27 下午
 * @Version 1.1.0
 **/
public interface SignatureUtils {
    String HMAC_ALGORITHM = "HmacSHA256";

    char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static String bytesToHex(byte[] bytes) {

        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    /**
     * 生成签名，开发版本oracle jdk 1.8.0_221
     *
     * @param secretId        邮件下发的secret_id
     * @param secretKey       邮件下发的secret_key
     * @param httpMethod      http请求方法 GET/POST/PUT等
     * @param headerNonce     X-TC-Nonce请求头，随机数
     * @param headerTimestamp X-TC-Timestamp请求头，当前时间的秒级时间戳
     * @param requestUri      请求uri，eg：/v1/meetings
     * @param requestBody     请求体，没有的设为空串
     * @return 签名，需要设置在请求头X-TC-Signature中
     * @throws NoSuchAlgorithmException e
     * @throws InvalidKeyException      e
     */
    static String sign(String secretId, String secretKey, String httpMethod, String headerNonce, String headerTimestamp, String requestUri, String requestBody)
            throws NoSuchAlgorithmException, InvalidKeyException {

        String tobeSig =
                httpMethod + "\nX-TC-Key=" + secretId + "&X-TC-Nonce=" + headerNonce + "&X-TC-Timestamp=" + headerTimestamp + "\n" + requestUri + "\n" + requestBody;
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(tobeSig.getBytes(StandardCharsets.UTF_8));
        String hexHash = bytesToHex(hash);
        return new String(Base64.getEncoder().encode(hexHash.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 加密消息
     *
     * @param content   : 加密消息
     * @param secretKey : 签名Key
     * @return java.lang.String
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @author shengtengsun
     * @Date 2020/9/11 4:58 下午
     **/
    static String encodeMessage(String secretKey, String content) throws NoSuchAlgorithmException, InvalidKeyException {
        String signature = generalSignature(secretKey, content);
        return new String(Base64.getEncoder().encode(signature.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 解密
     *
     * @param secretKey
     * @param content
     * @return java.lang.String
     * @author shengtengsun
     * @Date 2020/9/11 5:16 下午
     **/
    static String decodeMessage(String secretKey, String content) throws InvalidKeyException, NoSuchAlgorithmException {

        String contentStr = new String(Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8)));
        return "";
    }

    /**
     * 生成签名
     *
     * @param secretKey
     * @param message
     * @return java.lang.String
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @author shengtengsun
     * @Date 2020/9/11 5:14 下午
     **/
    static String generalSignature(String secretKey, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        String tobeSign = "SECRET_KEY" + secretKey + "CONTENT" + message;

        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
        mac.init(keySpec);

        return bytesToHex(mac.doFinal(tobeSign.getBytes(StandardCharsets.UTF_8)));
    }
}
