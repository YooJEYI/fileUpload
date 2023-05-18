package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    //사용자가 저장한 파일이름
    private String uploadFileName;
    //서버에 저장된 파일이름
    private String storeFileName;

    public UploadFile(String uploadFileName,String storeFileName){
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

}
