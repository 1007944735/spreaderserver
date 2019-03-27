package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.ExpandMapper;
import com.sgevf.spreaderserver.dao.RedPacketMapper;
import com.sgevf.spreaderserver.entity.Expand;
import com.sgevf.spreaderserver.entity.RedPacket;
import com.sgevf.spreaderserver.service.FileService;
import com.sgevf.spreaderserver.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PubServiceImpl implements PubService {
    @Autowired
    private FileService fileService;

    @Autowired
    private ExpandMapper expandMapper;

    @Autowired
    private RedPacketMapper redPacketMapper;

    private String[] urls;

    private Expand e;
    private RedPacket rp;

    /**
     * @param redPacket
     * @param expand
     * @param pictures
     * @param video
     * @return -1 发布失败
     */
    @Transactional
    @Override
    public int pub(RedPacket redPacket, Expand expand, List<MultipartFile> pictures, MultipartFile video) {
        urls = new String[6];
        e = expand;
        rp = redPacket;
        String videoUrl = fileService.uploadVideo(video);
        if (videoUrl==null) {
            return -1;
        }else {
            e.setVideoUrl(videoUrl);
        }
        for (int i = 0; i < pictures.size(); i++) {
            String pictureUrl = fileService.uploadImage(pictures.get(i));
            if (!pictureUrl.isEmpty()) {
                urls[i] = pictureUrl;
            }
        }
        e.setImage1Url(urls[0] == null ? "" : urls[0]);
        e.setImage2Url(urls[1] == null ? "" : urls[1]);
        e.setImage3Url(urls[2] == null ? "" : urls[2]);
        e.setImage4Url(urls[3] == null ? "" : urls[3]);
        e.setImage5Url(urls[4] == null ? "" : urls[4]);
        e.setImage6Url(urls[5] == null ? "" : urls[5]);

        int eNum = expandMapper.insertExpand(e);
        if (eNum <= 0) {
            return -1;
        }
        rp.setExpandId(e.getId());

        int rNum = redPacketMapper.insertRedPacket(rp);
        if (rNum <= 0) {
            return -1;
        }
        return rp.getId();
    }

    @Override
    public void searchDefault(String longitude, String latitude) {

    }
}
