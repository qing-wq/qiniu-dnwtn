package ink.whi.video.service;

import ink.whi.video.model.dto.VideoStatisticDTO;

/**
 * @author: qing
 * @Date: 2023/11/3
 */
public interface CountReadService {
    VideoStatisticDTO queryVideoStatisticInfo(Long videoId);
}
