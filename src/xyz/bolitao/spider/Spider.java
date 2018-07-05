package xyz.bolitao.spider;

import java.util.ArrayList;
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
//        servicePool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1; i++) {
            System.out.println(url + i);
            servicePool.execute(new SpiderTask(url + i, gameList));
        }
        servicePool.shutdown();
    }

//    public static void writeData(List<Game> games) {
//        System.out.println(games.size());
//        Collections.sort(games);
//        String gameJson = new Gson().toString(games);
//    }
}