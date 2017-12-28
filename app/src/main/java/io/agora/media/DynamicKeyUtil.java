package io.agora.media;

import android.content.Context;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

import io.agora.openduo.R;


/**
 * Created by hefeng on 15/8/10.
 * Util to generate Agora media dynamic key.
 */

public class DynamicKeyUtil {

    static byte[] encodeHMAC(String key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        return encodeHMAC(key.getBytes(), message);
    }

    static byte[] encodeHMAC(byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(key, "RAW");

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(keySpec);
        return mac.doFinal(message);
    }

    static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static String getChannelKey(Context context, String channel , long uid) {
        try {
            int ts = (int)(new Date().getTime()/1000);
            int r = new Random().nextInt();
            int expiredTs = 0; // ts + 40

            String result = DynamicKey5.generateMediaChannelKey(
                    context.getString(R.string.agora_app_id),
                    context.getString(R.string.agora_app_certificate),
                    channel, ts, r, uid, expiredTs);

            System.out.println("getChannelKey result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
