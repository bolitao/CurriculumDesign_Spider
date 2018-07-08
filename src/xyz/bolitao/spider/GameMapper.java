package xyz.bolitao.spider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Boli Tao
 * @date 2018/7/7
 */
@Mapper
public interface GameMapper {
    @Insert("insert into basic_information(mainKey,name,metaScore,releaseDate,maturityRating,publisher,genre,userScore,platform, imgUrl) values(#{mainKey},#{name},#{metaScore},#{releaseDate},#{maturityRating},#{publisher},#{genre},#{userScore},#{platform},#{imgUrl})")
    void insert(Game game);
}
