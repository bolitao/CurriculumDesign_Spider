/**
 * 游戏详细信息
 *
 * @author Boli Tao
 * @date 2018/7/3
 */
public class Game implements Comparable<Game> {
    /**
     * 游戏名 + 平台 + 发售日期的 MD5 值
     * 用作主键
     */
    String mainKey;

    /**
     * 游戏名
     */
    public String name;

    /**
     * 媒体综合评分
     */
    public double metaScore;

    /**
     * 发售日
     */
    public String releaseDate;

    /**
     * 分级
     */
    public String maturityRating;

    /**
     * 发行商
     */
    public String publisher;

    /**
     * 游戏类型
     */
    public String genre;

    /**
     * 玩家评分
     */
    public double userScore;

    /**
     * 游戏发售平台
     */
    public String platform;

    /**
     * 缩略图链接
     */
    public String imgUrl;

    @Override
    public String toString() {
        return "Game{" +
                "mainKey='" + mainKey + '\'' +
                ", name='" + name + '\'' +
                ", metaScore=" + metaScore +
                ", releaseDate='" + releaseDate + '\'' +
                ", maturityRating='" + maturityRating + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", userScore=" + userScore +
                ", platform='" + platform + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(double metaScore) {
        this.metaScore = metaScore;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getUserScore() {
        return userScore;
    }

    public void setUserScore(double userScore) {
        this.userScore = userScore;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMainKey() {
        return mainKey;
    }

    public void setMainKey(String mainKey) {
        this.mainKey = mainKey;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int compareTo(Game o) {
        return (int) (this.metaScore - o.metaScore);
    }
}
