package com.test.huabanspider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

public class DownloadPipeline extends FilePersistentBase implements Pipeline {

    private static final Logger logger = Logger.getLogger(DownloadPipeline.class);

    public DownloadPipeline(String downloadPath) {
        this.setPath(downloadPath);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Object o = resultItems.get("img");
        if (o != null) {
            logger.info("Catch from: " + o);
            download(o.toString());
        }
    }

    public void download(String imgUrl) {
        String fileType = ".jpg";
        File outFile = getFile(this.getPath() + UUID.randomUUID().toString() + fileType);
        OutputStream os = null;
        InputStream is = null;
        try {
            URL url = new URL(imgUrl);
            os = new FileOutputStream(outFile);
            is = url.openStream();
            byte[] buff = new byte[1024];
            while (true) {
                int readed = is.read(buff);
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buff, 0, temp, 0, readed);
                os.write(temp);
            }
        } catch (Exception e) {
            logger.error("Download img error: " + e);
        } finally {
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            } catch (IOException e) {
                logger.error("Close stream error: " + e);
            }
        }
    }
}