package xyz.bolitao.spider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// @为注解
@Mapper
public interface GameMapper {
    @Insert("insert into basic_information(name,metaScore,releaseDate,maturityRating,publisher,genre,userScore,platform) values(#{name},#{metaScore},#{releaseDate},#{maturityRating},#{publisher},#{genre},#{userScore},#{platform})")
    void insert(Game game);

    // TODO: 反射
    @Select("SELECT * FROM basic_information WHERE name=#{pk}")
    Game load(String pk);

    @Select("SELECT * FROM basic_information")
    List<Game> find();
}
