package com.qa.barcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeCapture {

	public static void main(String[] args) throws IOException, NotFoundException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://barcode.tec-it.com/en/MobileAztecUrl?data=Srinivas%20Tutta");
		driver.manage().window().maximize();
		
		
		String barCodeURL=driver.findElement(By.tagName("img")).getAttribute("src");
		System.out.println(barCodeURL);

		
		URL url=new URL(barCodeURL);
		BufferedImage bufferedImage= ImageIO.read(url);
		LuminanceSource luminanceSource= new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result=new MultiFormatReader().decode(binaryBitmap);
		
		System.out.println(result.getText());
		
		driver.quit();
		
	}
}
