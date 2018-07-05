package xyz.bolitao.spider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// @为注解
@Mapper
public interface GameMapper {
    @Insert("INSERT INTO basic_formation(name,metaScore,release_date,maturityRating,publisher,genre,userScore,platform) VALUES(#{name},#{metaScore},#{releaseDate},#{maturityRating},#{publisher},#{genre},#{userScore},#{userScore}")
    void insert(Game game);

    // TODO: 反射
    @Select("SELECT * FROM game_information WHERE name=#{pk}")
    Game load(String pk);

    @Select("SELECT * FROM game_information")
    List<Game> find();
}
