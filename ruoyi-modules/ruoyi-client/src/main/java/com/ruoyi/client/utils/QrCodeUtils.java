package com.ruoyi.client.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;

public class QrCodeUtils {

    public static String createCode(String content) {
        try {
            System.out.println(content.length());
            // 定义二维码参数
            HashMap<EncodeHintType, Object> hints = new HashMap<>();
            // 字符集
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 纠错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            // 空白
            hints.put(EncodeHintType.MARGIN, 2);
            if(content.length()>1800){
                return null;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, 400, 400, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", baos);
            String qrCode =  Base64.getEncoder().encodeToString(baos.toByteArray());
            bitMatrix.clear();
            baos.close();
            return qrCode;
        } catch (Exception e) {
            return null;
        }
    }

}
