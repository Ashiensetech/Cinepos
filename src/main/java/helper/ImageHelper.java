package helper;


import nonentity.photo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * Created by mi on 10/1/15.
 */

public class ImageHelper {
    //server settings for pictures and images
      /* ------------------- Live server of client ----------------------------- */

    private static String GLOBAL_PATH ="";


    public String TGLOBAL_PATH;

    /* ------------------- Develop ----------------------------- */
  //  private static String GLOBAL_PATH = "/home/wsit/rentguru24files/";

    /* -------------------- Beta ---------------------------------- */
//    private static String GLOBAL_PATH = "/home/wsit/rentguru24files_beta/";

        /*------------------Local---------------------*/
   // private static String GLOBAL_PATH= "/home/mi/Projects/j2ee/rentguru24files/";

//    private static String GLOBAL_PATH= "/home/omar/IdeaProjects/rentguru24files/";

    private static String DOC_FOLDER= "";
    private static String DOC_PATH=  "";
    private static String PRODUCT_FOLDER= "";
    private static String PRODUCT_PATH= "";
    private static String PROFILE_FOLDER= "";
    private static String PROFILE_PATH= "";
    private static String CATEGORY_FOLDER= "";
    private static String CATEGORY_PATH= "";
    private static String TEMP_FOLDER= "";
    private static String TEMP_FILE_PATH= "";
    private static String BANNER_IMAGE_FOLDER = "";
    private static String BANNER_IMAGE_PATH= "";

    @PostConstruct
    public void init(){
        ImageHelper.GLOBAL_PATH = TGLOBAL_PATH;
        DOC_FOLDER= "identityDoc/";
        DOC_PATH= GLOBAL_PATH+DOC_FOLDER;
        PRODUCT_FOLDER= "product/";
        PRODUCT_PATH= GLOBAL_PATH+PRODUCT_FOLDER;
        PROFILE_FOLDER= "profile/";
        PROFILE_PATH= GLOBAL_PATH+PROFILE_FOLDER;
        CATEGORY_FOLDER= "category/";
        CATEGORY_PATH= GLOBAL_PATH+CATEGORY_FOLDER;
        TEMP_FOLDER= "temp/";
        TEMP_FILE_PATH= GLOBAL_PATH+TEMP_FOLDER;
        BANNER_IMAGE_FOLDER = "bannerImage/";
        BANNER_IMAGE_PATH= GLOBAL_PATH+BANNER_IMAGE_FOLDER;
    }


    public static void setGlobalPath(String globalPath) {
        GLOBAL_PATH = globalPath;
    }

    public static String getGlobalPath() {
        return GLOBAL_PATH;
    }
    public static boolean isFileExist(String path){
        File docFile =new File(GLOBAL_PATH+path);
        return docFile.exists();
    }
    public static String moveFile(int appCredentialId,String oldPath){
        String fileName = appCredentialId+"/"+System.nanoTime()+"."+getExtension(oldPath);
        String filePath = DOC_PATH+fileName;
        try{

            File docFile =new File(GLOBAL_PATH+oldPath);

            createDirIfNotExist(DOC_PATH + appCredentialId);

            if(docFile.renameTo(new File(filePath))){
                System.out.println("File is moved successful!");
            }else{
                System.out.println("File is failed to move!"+filePath);
            }
            System.out.println(GLOBAL_PATH+oldPath);


        }catch(Exception e){
            e.printStackTrace();
        }
        return DOC_FOLDER+fileName;
    }
    public static Picture moveProductImage(int appCredentialId,String oldPath){
        String fileName = appCredentialId+"/"+System.nanoTime()+"."+getExtension(oldPath);
        String filePath = PRODUCT_PATH+fileName;
        Picture picture = new Picture();
        try{

            File docFile =new File(GLOBAL_PATH+oldPath);

            createDirIfNotExist(PRODUCT_PATH + appCredentialId);

            if(docFile.renameTo(new File(filePath))){
                BufferedImage in = ImageIO.read(new File(filePath));

                picture.getOriginal().setPath(PRODUCT_FOLDER + fileName);
                picture.getOriginal().getSize().setHeight(in.getHeight());
                picture.getOriginal().getSize().setWidth(in.getWidth());
                in.flush();

            }else{
                System.out.println("File is failed to move!"+filePath);
            }
            System.out.println(GLOBAL_PATH+oldPath);


        }catch(Exception e){
            e.printStackTrace();
        }

        return picture;
    }
    public static Picture moveProfileImage(int appCredentialId,String oldPath){
        String fileName = appCredentialId+"/"+System.nanoTime()+"."+getExtension(oldPath);
        String filePath = PROFILE_PATH+fileName;
        Picture picture = new Picture();
        try{

            File docFile =new File(GLOBAL_PATH+oldPath);

            createDirIfNotExist(PROFILE_PATH + appCredentialId);

            if(docFile.renameTo(new File(filePath))){
                BufferedImage in = ImageIO.read(new File(filePath));

                picture.getOriginal().setPath(fileName);
                picture.getOriginal().getSize().setHeight(in.getHeight());
                picture.getOriginal().getSize().setWidth(in.getWidth());
                in.flush();

            }else{
                System.out.println("File is failed to move!"+filePath);
            }
            System.out.println(GLOBAL_PATH+oldPath);


        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return picture;
    }
    public static Picture moveCategoryImage(String oldPath){
        String fileName = System.nanoTime()+"."+getExtension(oldPath);
        String filePath = CATEGORY_PATH+fileName;
        Picture picture = new Picture();
        try{

            File docFile =new File(GLOBAL_PATH+oldPath);

            createDirIfNotExist(CATEGORY_PATH);

            if(docFile.renameTo(new File(filePath))){
                BufferedImage in = ImageIO.read(new File(filePath));

                picture.getOriginal().setPath(fileName);
                picture.getOriginal().getSize().setHeight(in.getHeight());
                picture.getOriginal().getSize().setWidth(in.getWidth());
                in.flush();

            }else{
                System.out.println("File is failed to move!"+filePath);
            }
            System.out.println(GLOBAL_PATH+oldPath);


        }catch(Exception e){
            e.printStackTrace();
        }

        return picture;
    }
    public static Picture moveBannerImage(String oldPath){
        String fileName = System.nanoTime()+"."+getExtension(oldPath);
        String filePath = BANNER_IMAGE_PATH+fileName;
        Picture picture = new Picture();
        try{

            File docFile =new File(GLOBAL_PATH+oldPath);

            createDirIfNotExist(BANNER_IMAGE_PATH);

            if(docFile.renameTo(new File(filePath))){
                BufferedImage in = ImageIO.read(new File(filePath));

                picture.getOriginal().setPath(fileName);
                picture.getOriginal().getSize().setHeight(in.getHeight());
                picture.getOriginal().getSize().setWidth(in.getWidth());
                in.flush();

            }else{
                System.out.println("File is failed to move!"+filePath);
            }
            System.out.println(GLOBAL_PATH+oldPath);


        }catch(Exception e){
            e.printStackTrace();
        }

        return picture;
    }
    public static void createDirIfNotExist(String path) {
        File theDir = new File(path);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
                System.out.println("DIR created");
            } catch (SecurityException se) {
                //handle it
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
    public static String saveFile(byte[] pdfByte, String originalFileName) {
        String fileName = getRandomNumber() + "."+getExtension(originalFileName);
        System.out.println(GLOBAL_PATH);
        try {
            File someFile = new File(TEMP_FILE_PATH+ "/" +fileName);
            FileOutputStream fos;
            fos = new FileOutputStream(someFile);
            fos.write(pdfByte);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TEMP_FOLDER+fileName;
    }
    public static void saveFile(String base64Str) {
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
    public static Picture saveProfileImage(int appCredentialId,InputStream inputStream) {
        InputStream in = new BufferedInputStream(inputStream);
        OutputStream out = null;
        String fileName = getRandomNumber() + ".jpg";
        String fileFolder = PROFILE_PATH+"/"+appCredentialId+"/";
        String filePath = PROFILE_PATH+"/"+appCredentialId+"/"+fileName;
        Picture picture = new Picture();
        try {
            createDirIfNotExist(fileFolder);
            out = new BufferedOutputStream(new FileOutputStream(filePath));
            for ( int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();

            BufferedImage bIm = ImageIO.read(new File(filePath));
            picture.getOriginal().setPath(appCredentialId+"/" + fileName);
            picture.getOriginal().getSize().setHeight(bIm.getHeight());
            picture.getOriginal().getSize().setWidth(bIm.getWidth());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picture;


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


