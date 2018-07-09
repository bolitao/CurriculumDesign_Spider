package xyz.bolitao.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImgDownloadTask implements Runnable {
    /**
     * 爬取的对象
     */
    Game game;

    public ImgDownloadTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL(game.imgUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        FileOutputStream fout = null;
        try {
            URLConnection connection = url.openConnection();
            // 设置 connection 超时
            connection.setConnectTimeout(10 * 1000);
            fout = new FileOutputStream(new File("Img" + File.separator + game.name + game.releaseDate + game.platform + ".jpg"));
            byte[] buf = new byte[1024];
            int length;
            while (-1 != (length = in.read(buf))) {
                fout.write(buf, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null || fout != null) {
                    in.close();
                    fout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
