package com.realaicy.prod.jc.realglobal.web;

import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by realaicy on 16/8/18.
 * xxx
 */
@Controller
public class FileController {

    private static final Tika TIKA = new Tika();
    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;

    @RequestMapping(value = "/uploadPortrait", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadPortrait(
            @RequestParam(value = "portrait", required = false) MultipartFile uploadfile) {

        String filename = "realfile_" + System.currentTimeMillis();
        try {

            if (!TIKA.detect(uploadfile.getInputStream()).startsWith("image")) {
                return new ResponseEntity<>("not a image file!", HttpStatus.BAD_REQUEST);
            }
            if (uploadfile.getSize() > 1048576) {
                return new ResponseEntity<>("image size over 1M!", HttpStatus.BAD_REQUEST);
            }

            System.out.println(ImageIO.read(uploadfile.getInputStream()).getWidth());
            System.out.println(ImageIO.read(uploadfile.getInputStream()).getHeight());

            Files.copy(uploadfile.getInputStream(), Paths.get(uploadfiletmppath, filename));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(filename, HttpStatus.OK);
    } // method uploadPortrait

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam(value = "realfile") MultipartFile uploadfile) {

        String strTypeTemp1 = FilenameUtils.getExtension(uploadfile.getOriginalFilename());
        String filename;
        if (!strTypeTemp1.equalsIgnoreCase("pdf")
                && !strTypeTemp1.equalsIgnoreCase("txt")
                && !strTypeTemp1.equalsIgnoreCase("doc")
                && !strTypeTemp1.equalsIgnoreCase("docx")) {
            return new ResponseEntity<>("文件格式错误!", HttpStatus.BAD_REQUEST);
        }
        try {

            String strTypeTemp2 = TIKA.detect(uploadfile.getInputStream());
            if (!strTypeTemp2.equalsIgnoreCase("text/plain")
                    && !strTypeTemp2.equalsIgnoreCase("application/pdf")
                    && !strTypeTemp2.equalsIgnoreCase("application/x-TIKA-msoffice")
                    && !strTypeTemp2.equalsIgnoreCase("application/x-TIKA-ooxml")) {
                return new ResponseEntity<>("文件格式错误!", HttpStatus.BAD_REQUEST);
            }
            if (uploadfile.getSize() > StaticParams.FILEUPLOAD.MAXBYTESPERFILE) {
                return new ResponseEntity<>("file size over 20M!", HttpStatus.BAD_REQUEST);
            }
            filename = "realfile_" + System.currentTimeMillis() + "." + strTypeTemp1;
            Files.copy(uploadfile.getInputStream(), Paths.get(uploadfiletmppath, filename));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(filename, HttpStatus.OK);
    }
}
