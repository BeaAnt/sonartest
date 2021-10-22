package com.jsoup.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@RestController
public class QrController {

	
	@RequestMapping("/qr/{text}")
	public byte[] getqr(HttpServletResponse response,@PathVariable String text) throws IOException {
		

		response.setContentType("image/png");
		 response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
         OutputStream responseOutputStream = response.getOutputStream();
		
		
		byte[] bf= getQRCodeImage(text, 300, 300);

		responseOutputStream.write(bf);
responseOutputStream.close();
		return bf;
		
		//ByteArrayInputStream bis = new ByteArrayInputStream(bf);
	    //  BufferedImage bImage2 = ImageIO.read(bis);
	    //  ImageIO.write(bImage2, "jpg", new File("C://Users/A0000/output.jpg") );
		 	}

	public static byte[] getQRCodeImage(String text, int width, int height) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			byte [] imgbyte= byteArrayOutputStream.toByteArray();
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgbyte));
			return imgbyte;

		}catch(

				Exception e)
		{
			return null;
		}
		
		
	}
	
	public static byte[] getQRCodeImageHappy(String text, int width, int height) {
		try {
			text = "https://www.facebook.com/HappyTogetherBar";
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			byte [] imgbyte= byteArrayOutputStream.toByteArray();
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgbyte));
			return imgbyte;

		}catch(

				Exception e)
		{
			return null;
		}
		
		
	}
}
