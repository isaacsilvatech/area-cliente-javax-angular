package br.eti.ljr.sn.clientesrv.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

public class Images {

	public static byte[] rezise(byte[] imagem, Integer width, Integer height) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(imagem);
		BufferedImage originalImage = ImageIO.read(bais);

		if (Objects.isNull(originalImage)) {
			return null;
		}

		// Corrigir a rotação usando os dados EXIF
		originalImage = correctImageOrientation(imagem, originalImage);

		int originalWidth = originalImage.getWidth();
		int originalHeight = originalImage.getHeight();

		double scale = Math.max((double) width / originalWidth, (double) height / originalHeight);
		int scaledWidth = (int) (originalWidth * scale);
		int scaledHeight = (int) (originalHeight * scale);

		int cropX = (scaledWidth - width) / 2;
		int cropY = (scaledHeight - height) / 2;

		Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		BufferedImage scaledBufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = scaledBufferedImage.createGraphics();
		g2d.drawImage(scaledImage, 0, 0, null);
		g2d.dispose();

		BufferedImage outputImage = scaledBufferedImage.getSubimage(cropX, cropY, width, height);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(outputImage, "png", baos);
		baos.flush();
		byte[] outputImageBytes = baos.toByteArray();
		baos.close();

		return outputImageBytes;
	}

	private static BufferedImage correctImageOrientation(byte[] imageBytes, BufferedImage image) throws IOException {
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(new ByteArrayInputStream(imageBytes));
			ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);

			if (directory != null) {
				int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);

				if (orientation == 6) { // 90 degrees
					image = rotateImage(image, 90);
				} else if (orientation == 3) { // 180 degrees
					image = rotateImage(image, 180);
				} else if (orientation == 8) { // 270 degrees
					image = rotateImage(image, 270);
				}
			}
		} catch (Exception e) {
			// Não encontrou dados EXIF ou ocorreu erro, não faz nada
		}

		return image;
	}

	private static BufferedImage rotateImage(BufferedImage image, int angle) {
		int width = image.getWidth();
		int height = image.getHeight();

		// Criando uma nova imagem para o caso de rotacionar
		BufferedImage rotatedImage = new BufferedImage(height, width, image.getType());

		Graphics2D g2d = rotatedImage.createGraphics();

		// Realizando a rotação no ponto central
		g2d.rotate(Math.toRadians(angle), height / 2.0, width / 2.0);

		// Desenhando a imagem rotacionada, ajustando para centralizar a imagem
		int x = (height - width) / 2;
		int y = (width - height) / 2;

		g2d.drawImage(image, x, y, null);
		g2d.dispose();

		return rotatedImage;
	}

}
