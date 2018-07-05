package xyz.bolitao.spider;

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
        servicePool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 7; i++) {
//            System.out.println(url + i);
            servicePool.execute(new SpiderTask(url + i, gameList));
        }
        servicePool.shutdown();
//        for (int i = 0; i < 7; i++) {
//            SpiderTask spiderTask = new SpiderTask(url + i, gameList);
//        }
    }
}