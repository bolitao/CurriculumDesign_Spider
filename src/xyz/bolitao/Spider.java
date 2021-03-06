package xyz.bolitao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xyz.bolitao.database.GameMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 爬虫主程序
 * 将数据存入数据库的具体实现
 *
 * @author Boli Tao
 * @since 2018/7/3
 */
public class Spider {
    /**
     * 链接
     */
    String url;

    /**
     * 存储文件的列表
     */
    List<Game> gameList = new ArrayList<>();

    /**
     * xyz.bolitao.Game
     */
    Game game;

    /**
     * 线程池
     */
    ExecutorService servicePool;

    ExecutorService imgPool;

    /**
     * 构造方法
     * 获取用于操作的 url
     */
    public Spider(String url) {
        this.url = url;
    }


    public void run() {
        servicePool = Executors.newFixedThreadPool(4);
        System.out.println("开始爬取数据，请等待");
        for (int i = 0; i < 2; i++) {
            servicePool.execute(new SpiderTask(url + i, gameList));
        }
        servicePool.shutdown();
        while (true) {
            if (servicePool.isTerminated()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 将数据存入数据库
        // 建立连接
        SqlSessionFactory factory = null;
        try {
            factory = new SqlSessionFactoryBuilder().build(
                    new FileReader("src/xyz/bolitao/spider/xyz.bolitao.database/config.xml")
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SqlSession session = factory.openSession();
        // 获得接口的具体实现
        GameMapper mapper = session.getMapper(GameMapper.class);
        for (Game g : gameList) {
            mapper.insert(g);
        }
        session.commit();
        session.close();
        System.out.println("成功将数据存入数据库");

        // 图片下载
        imgPool = Executors.newFixedThreadPool(4);
        System.out.println("正在下载封面,请等待程序结束...");
        for (int i = 0; i < 200; i++) {
            imgPool.execute(new ImgDownloadTask(gameList.get(i)));
        }
        imgPool.shutdown();
    }
}