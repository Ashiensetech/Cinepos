package helper;


import custom_exception.TempFileException;
import nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import utility.FileUtil;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * Created by mi on 10/1/15.
 */
@Component
public class ImageHelper {
    //server settings for pictures and images
      /* ------------------- Live server of client ----------------------------- */

    private static String GLOBAL_PATH ="";

    @Autowired
    public String TGLOBAL_PATH;


    private static String FILM_FILE_FOLDER = "";
    private static String TEMP_FILE_FOLDER = "";
    private static String PRODUCT_FOLDER = "";
    private static String FILM_FILE_PATH = "";
    private static String TEMP_FILE_PATH = "";
    private static String PRODUCT_FILE_PATH = "";
    @PostConstruct
    public void init(){
        ImageHelper.GLOBAL_PATH = TGLOBAL_PATH;
        FILM_FILE_FOLDER = "film/";
        FILM_FILE_PATH = GLOBAL_PATH+FILM_FILE_FOLDER;
        TEMP_FILE_FOLDER = "temp/";
        TEMP_FILE_PATH =GLOBAL_PATH+TEMP_FILE_FOLDER;
        PRODUCT_FOLDER = "product/";
        PRODUCT_FILE_PATH = GLOBAL_PATH+PRODUCT_FOLDER;
        System.out.println("TGLOBAL_PATH :" +TGLOBAL_PATH);
    }


    private static boolean isFileExist(String absPath){
        File docFile =new File(absPath);
        return docFile.exists();
    }
    public static boolean isFilmFileExist(String path){
        return isFileExist(FILM_FILE_PATH + path);
    }
    public static boolean isTempFileExist(String fileName){
        System.out.println(TEMP_FILE_PATH+fileName);
        return isFileExist(TEMP_FILE_PATH+fileName);
    }
    public static String moveFilmFile(int filmId,String tempFileName) throws TempFileException{
        String fileName = filmId+"/"+System.nanoTime()+"."+getExtension(tempFileName);
        String filePath = FILM_FILE_PATH +fileName;
        try{

            File docFile =new File(TEMP_FILE_PATH+tempFileName);

            createDirIfNotExist(FILM_FILE_PATH + filmId);

            if(docFile.renameTo(new File(filePath))){
                System.out.println("File is moved successful!");
            }else{
               throw  new TempFileException("Unable to move file");
            }
            System.out.println(GLOBAL_PATH+tempFileName);


        }catch(Exception e){
            throw  new TempFileException(e.getMessage());
        }
        return fileName;
    }
    public static String moveProductFile(int productId,String tempFileName) throws TempFileException{
        String fileName = productId+"/"+System.nanoTime()+"."+getExtension(tempFileName);
        String filePath = PRODUCT_FILE_PATH +fileName;
        try{

            File docFile =new File(TEMP_FILE_PATH+tempFileName);

            createDirIfNotExist(PRODUCT_FILE_PATH + productId);

            if(docFile.renameTo(new File(filePath))){
                System.out.println("File is moved successful!");
            }else{
                throw  new TempFileException("Unable to move file");
            }
            System.out.println(PRODUCT_FILE_PATH+tempFileName);


        }catch(Exception e){
            throw  new TempFileException(e.getMessage());
        }
        return fileName;
    }
    public static void createDirIfNotExist(String path) {
        File theDir = new File(path);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            if(theDir.mkdir()){
                System.out.println("DIR created");
            }else{
                System.out.println("Cannot create dir");
            }
        }
    }
    public static byte[] serialize(Object object)throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(object);
        System.out.println("Completed Serializing");
        return b.toByteArray();
    }


    public static BufferedImage decodeToImage(String imageString) {
        BufferedImage image = null;
        byte[]        imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public static String saveInTempFolder(byte[] pdfByte, String originalFileName) {
        String fileName = System.nanoTime()+ "."+getExtension(originalFileName);
        System.out.println(GLOBAL_PATH);
        String filePath = TEMP_FILE_PATH+fileName;
        try {
            File someFile = new File(filePath);
            FileOutputStream fos;
            fos = new FileOutputStream(someFile);
            fos.write(pdfByte);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
    public static void saveInTempFolder(String base64Str) {
        BufferedImage image = null;
        byte[]        pdfByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            pdfByte = decoder.decodeBuffer(base64Str);


            File someFile = new File(GLOBAL_PATH);
            FileOutputStream fos;
            fos = new FileOutputStream(someFile);
            fos.write(pdfByte);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String getRandomNumber(){
        Random rnd = new Random();
        int n = 1000 + rnd.nextInt(900000);
        return Integer.toString(n);
    }
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static String createThumbnail(BufferedImage img, int width, int height, String path) {


        Image         scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        thumbnail.createGraphics().drawImage(scaledImg, 0, 0, null);

        String fileName = System.nanoTime() + ".jpg";

        path += "/thumbnail";
        createDirIfNotExist(GLOBAL_PATH + path);
        path += "/" + fileName;

        try {
            ImageIO.write(thumbnail, "jpg", new File(GLOBAL_PATH + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    public static String createThumbnail(BufferedImage img, int width, int height, String path,String tmpFileName) {


        Image         scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        thumbnail.createGraphics().drawImage(scaledImg, 0, 0, null);

        String fileName = System.nanoTime() + "."+getExtension(tmpFileName);

        path += "/thumbnail";
        createDirIfNotExist(GLOBAL_PATH + path);
        path += "/" + fileName;

        try {
            ImageIO.write(thumbnail, getExtension(tmpFileName), new File(GLOBAL_PATH + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getExtension(String fileName){
        String extension ="";
        String name = fileName;
        try {
            extension =  name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
        System.out.println("Extension "+extension);
        return extension;
    }

}


