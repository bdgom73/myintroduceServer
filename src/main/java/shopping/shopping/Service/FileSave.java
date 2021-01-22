package shopping.shopping.Service;

import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileSave {

    /** 실제 저장하는 절대 주소 */
    private final String absoluteAddress = "C:\\Users\\bdgom\\OneDrive\\desktop\\shopping\\src\\main\\resources\\static\\images";

    private final String ext;

    /** 서버  주소*/
    private final String hostAddress = "http://localhost:8080";
    /** 폴더 주소*/
    private String folderAddress ;

    /** DB에 sava and load 주소  */
    private final String imageAddress ;

    /** 랜덤 파일 명 class 가져오기*/
    private final RandomString rs;

    /** 랜덤 파일명 */
    private final String randomFileName;

    /** 파일의 원본 이름*/
    private final String originalFileName;


    public FileSave(MultipartFile file, String folderAddress) throws IOException {
        this.folderAddress = folderAddress;
        rs = new RandomString();
        originalFileName = file.getOriginalFilename();
        ext = getOriginalFileName().substring(getOriginalFileName().lastIndexOf("."));
        randomFileName = rs.getRandomString().toString();
        imageAddress = hostAddress+this.folderAddress+"/"+randomFileName+ext;
        file.transferTo(new File(absoluteAddress+"\\"+randomFileName+ext));
    }
    public FileSave(MultipartFile file) throws IOException {
        this(file,"/images");
    }
    public String getSavedFileName(){
        return randomFileName;
    }
    public String getOriginalFileName(){
        return originalFileName;
    }
    public String getImageAddress(){
        return imageAddress;
    }
    public void setFolderAddress(String folderAddress){
        this.folderAddress = folderAddress;
    }

}
