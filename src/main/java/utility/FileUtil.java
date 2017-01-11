package utility;

import custom_exception.TempFileException;
import dao.TempFileDao;
import entity.TempFile;
import helper.ImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/**
 * Created by mi on 1/11/17.
 */
@Service
public class FileUtil {

        @Autowired
        TempFileDao tempFileDao;

        private TempFile getTempFile(int fileToken)throws TempFileException{
            /**
             * Token existence
             * */
            TempFile tempFile = tempFileDao.getByToken(fileToken);
            if(tempFile==null){
                throw new TempFileException("Token not found");
            }
            return tempFile;
        }
        public String moveFilmFileFromTemp(int filmId,int fileToken) throws FileNotFoundException, TempFileException {

            /**
             * Token existence
            * */
            TempFile tempFile = this.getTempFile(fileToken);


            /**
             * File existence
             * */
            boolean fileExist = ImageHelper.isTempFileExist(tempFile.getPath());
            if(!fileExist){
                System.out.println(tempFile.getPath());
                throw  new FileNotFoundException("No file found with the token "+fileToken);
            }

            String filePath = ImageHelper.moveFilmFile(filmId,tempFile.getPath());


            return filePath;
        }
        public String moveProductFileFromTemp(int productId,int fileToken) throws FileNotFoundException, TempFileException {

            /**
             * Token existence
             * */
            TempFile tempFile = this.getTempFile(fileToken);


            /**
             * File existence
             * */
            boolean fileExist = ImageHelper.isTempFileExist(tempFile.getPath());
            if(!fileExist){
                System.out.println(tempFile.getPath());
                throw  new FileNotFoundException("No file found with the token "+fileToken);
            }

            String filePath = ImageHelper.moveProductFile(productId, tempFile.getPath());


            return filePath;
        }
}
