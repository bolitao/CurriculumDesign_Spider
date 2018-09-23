/**
 * 程序入口
 *
 * @author Boli Tao
 * @date 2018/7/6
 */
public class StartService {
    public static void main(String[] args) {
        Spider spider = new Spider("http://www.metacritic.com/browse/games/score/metascore/all/all/filtered?sort=desc&view=detailed&page=");
        spider.run();
    }
}
