package shopping.shopping.Service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.Loader;
import org.hibernate.type.BlobType;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;

import static org.junit.jupiter.api.Assertions.*;

class FileSaveTest {

    @Test
    public void blobimg() {

        Image image = new ImageIcon("E:\\download\\new.png").getImage();

        BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(image,0,0,null);
        g2d.dispose();

       ByteArrayOutputStream baos = null;
       try{
           baos = new ByteArrayOutputStream();
           ImageIO.write(bi,"jpg",baos);
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try{
               baos.close();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        System.out.println("bais = " + bais);
    }
    @Test
    public void blobpdf() throws IOException {

//        Image image = new ImageIcon("E:\\download\\new.png").getImage();
//
//        BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = bi.createGraphics();
//        g2d.drawImage(image,0,0,null);
//        g2d.dispose();
        File file = new File("C:\\Users\\bdgom\\OneDrive\\desktop\\Gool.pdf");

        ByteArrayInputStream bais;
        byte[] toBy = new byte[(int) file.length()];
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            toBy  = Base64.encodeBase64(fis.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}