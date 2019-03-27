package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Expand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ExpandMapper {

    @Insert("insert into expand(title,info,video_url,image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6) " +
            "values(#{title},#{info},#{videoUrl},#{image1Url},#{image2Url},#{image3Url},#{image4Url},#{image5Url},#{image6Url})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertExpand(Expand expand);
}
