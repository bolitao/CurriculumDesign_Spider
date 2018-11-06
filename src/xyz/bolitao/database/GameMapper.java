package xyz.bolitao.database;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import xyz.bolitao.Game;

/**
 * @author Boli Tao
 * @date 2018/7/7
 */
@Mapper
public interface GameMapper {
    // 注释代码是正式开始爬取的代码，未注释的为测试代码
//    @Insert("insert into basic_information(mainKey,name,metaScore,releaseDate,maturityRating,publisher,genre,userScore,platform, imgUrl) values(#{mainKey},#{name},#{metaScore},#{releaseDate},#{maturityRating},#{publisher},#{genre},#{userScore},#{platform},#{imgUrl})")
    @Insert("insert into test(mainKey,name,metaScore,releaseDate,maturityRating,publisher,genre,userScore,platform, imgUrl) values(#{mainKey},#{name},#{metaScore},#{releaseDate},#{maturityRating},#{publisher},#{genre},#{userScore},#{platform},#{imgUrl})")
    void insert(Game game);
}
