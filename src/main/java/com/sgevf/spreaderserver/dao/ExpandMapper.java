package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Expand;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpandMapper {

    @Insert("insert into expand(title,info,video_url,image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6) " +
            "values(#{title},#{info},#{videoUrl},#{image1Url},#{image2Url},#{image3Url},#{image4Url},#{image5Url},#{image6Url})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertExpand(Expand expand);

    @Select("select * from expand")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "info", property = "info"),
            @Result(column = "video_url", property = "videoUrl"),
            @Result(column = "image_url_1", property = "image1Url"),
            @Result(column = "image_url_2", property = "image2Url"),
            @Result(column = "image_url_3", property = "image3Url"),
            @Result(column = "image_url_4", property = "image4Url"),
            @Result(column = "image_url_5", property = "image5Url"),
            @Result(column = "image_url_6", property = "image6Url"),
    })
    List<Expand> queryExpand();

    @Select("select * from expand where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "info", property = "info"),
            @Result(column = "video_url", property = "videoUrl"),
            @Result(column = "image_url_1", property = "image1Url"),
            @Result(column = "image_url_2", property = "image2Url"),
            @Result(column = "image_url_3", property = "image3Url"),
            @Result(column = "image_url_4", property = "image4Url"),
            @Result(column = "image_url_5", property = "image5Url"),
            @Result(column = "image_url_6", property = "image6Url"),
    })
    Expand queryExpandById(@Param("id") int id);
}
