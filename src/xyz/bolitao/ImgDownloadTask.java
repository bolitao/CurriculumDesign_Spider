package xyz.bolitao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载图片的具体实现
 *
 * @author Boli Tao
 * @date 2018/7/7
 */
public class ImgDownloadTask implements Runnable {
    /**
     * 爬取的游戏对象
     */
    Game game;

    public ImgDownloadTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        try {
            // 获取游戏封面地址
            URL url = new URL(game.imgUrl);
            InputStream in = null;
            FileOutputStream fout = null;
            try {
                // 建立连接
                URLConnection connection = url.openConnection();
                // 设置超时为 50 秒
                connection.setConnectTimeout(50 * 1000);
                in = connection.getInputStream();
                // 以主键.jpg 作为图片名字
                fout = new FileOutputStream(new File("Img" + File.separator + game.mainKey + ".jpg"));
                byte[] buf = new byte[1024];
                int length;
                while (-1 != (length = in.read(buf))) {
                    fout.write(buf, 0, length);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    if (in != null || fout != null) {
                        in.close();
                        fout.close();
                    }
                } catch (IOException ioe2) {
                    ioe2.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
