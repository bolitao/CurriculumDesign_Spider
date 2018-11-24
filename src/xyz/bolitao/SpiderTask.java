package xyz.bolitao;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫的具体方法
 *
 * @author Boli Tao
 */
public class SpiderTask implements Runnable {
    /**
     * 链接
     */
    String url;

    /**
     * 存储文件的列表
     */
    List<Game> gameList = new ArrayList<>();

    /**
     * 构造方法
     * 获取用于操作的 url
     */
    SpiderTask(String url, List gameList) {
        this.url = url;
        this.gameList = gameList;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements items = doc.select(".col.main_col .list_products.list_cproduct_summaries " +
                    ".product.has_small_image");
            // 遍历获得的 doc
            for (Element item : items) {
                Game game = new Game();
                game.name = item.select(".basic_stat.product_title .product_title a").get(0).text();
                game.metaScore = Double.parseDouble(item.select(".basic_stat.product_score span").get(0).text());
                game.releaseDate = item.select(".stat.release_date .data").get(0).text();
                /*
                异常：未提供游戏分级信息
                处理：游戏分级设置为 null
                 */
                try {
                    game.maturityRating = item.select(".stat.maturity_rating .data").get(0).text();
                } catch (IndexOutOfBoundsException e) {
                    game.maturityRating = null;
                }
                /*
                 异常：未提供发行商信息
                 处理：将发行商信息设置为 null
                 */
                try {
                    game.publisher = item.select(".stat.publisher .data").get(0).text();
                } catch (IndexOutOfBoundsException e) {
                    game.publisher = null;
                }
                /*
                异常：站点可能未提供游戏类型/分类，爬虫会抛出 IndexOutOfBoundsException
                处理：遇到这种情况将 genre 值设为 null
                 */
                try {
                    game.genre = item.select(".stat.genre").get(0).text();
                } catch (IndexOutOfBoundsException e) {
                    game.genre = null;
                }
                /*
                异常：未提供分数
                处理：将用户分数设置为 -1
                 */
                try {
                    game.userScore = Double.parseDouble(item.select(".data.textscore").get(0).text());
                } catch (NumberFormatException e) {
                    game.userScore = -1;
                } catch (IndexOutOfBoundsException e) {
                    game.userScore = -1;
                }
                game.platform = item.select(".stat.platform_list .data").get(0).text();
                game.imgUrl = item.select("img").get(0).attr("src");
                game.mainKey = DigestUtils.md5Hex(game.name + game.platform + game.releaseDate);
                gameList.add(game);
            }
        } catch (IOException e) {
        }
    }

}
