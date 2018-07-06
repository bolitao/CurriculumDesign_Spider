package xyz.bolitao.spider;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 爬虫主程序
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
//    List<Game> gameList = Collections.synchronizedList(new LinkedList<>());
    /**
     * 线程池
     */
    ExecutorService servicePool;

    /**
     * 构造方法
     * 获取用于操作的 url
     */
    public Spider(String url) {
        this.url = url;
    }


    public void run() {
        servicePool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 155; i++) {
//            System.out.println(url + i);
            servicePool.execute(new SpiderTask(url + i, gameList));
        }
        servicePool.shutdown();
        while (true)
        {
            if (servicePool.isTerminated())
                break;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        for (int i = 0; i < 7; i++) {
//            SpiderTask spiderTask = new SpiderTask(url + i, gameList);
//        }
        System.out.println(gameList.size());
        // 存储到数据库
        // 建立连接
        SqlSessionFactory factory = null;
        try {
            factory = new SqlSessionFactoryBuilder().build(
                    new FileReader("config.xml")
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
        System.out.println("存储成功");
    }
}