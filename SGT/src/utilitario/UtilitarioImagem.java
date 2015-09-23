package utilitario;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class UtilitarioImagem {

	public static ImageIcon converterImage(byte[] imagem) {
		try {
			return new ImageIcon(imagem);
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] converterImageByte(File Imagem) {
		try {
			FileInputStream fis = new FileInputStream(Imagem);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] buf = new byte[1024];
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
				System.out.println("read " + readNum + " bytes,");
			}

			return (bos.toByteArray());
		} catch (Exception e) {
			return null;
		}
	}
}
