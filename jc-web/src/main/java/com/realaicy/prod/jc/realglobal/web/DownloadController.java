package com.realaicy.prod.jc.realglobal.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by realaicy on 16/8/18.
 * xxx
 */
@Controller
@RequestMapping("/download")
public class DownloadController {

    @Value("${realupload.path.tmp}")
    private String uploadfiletmppath;

    /**
     * Under the courseware id to download the file.
     *
     * @param filename The file name.
     * @throws IOException ex
     */
    @RequestMapping("/file/{name:.+}")
    public void downloadFile(@PathVariable("name") String filename, final HttpServletResponse response)
            throws IOException {
        System.out.println("");
        String encoding = response.getCharacterEncoding();

        response.setHeader("Content-Type", "application/octet-stream");
        //这里获取到文件的相对路径。其中 /course/ 为虚拟路径，主要用于nginx中进行拦截包含了/course/ 的URL， 并进行文件下载。
        //在以上nginx配置的第二个location 中同样也设置了 /course/，实际的文件下载路径并不会包含 /course/
        //当然，如果希望包含的话可以将以上的 alias 改为 root 即可。
        response.setHeader("X-Accel-Redirect", "/files/" + filename);
        response.setHeader("X-Accel-Charset", "utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        //response.setContentLength((int) file.contentLength());
    }
}
