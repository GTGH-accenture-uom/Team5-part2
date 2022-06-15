package team5.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import team5.model.Vaccination;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class QRCodeService {


    public byte[] generateQRCode(String vaccinationsWithState, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(vaccinationsWithState, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (WriterException e) {
          //  logger.error(e.getMessage(), e);
        } catch (IOException e) {
            //logger.error(e.getMessage(), e);
        }
        return null;
    }
}
