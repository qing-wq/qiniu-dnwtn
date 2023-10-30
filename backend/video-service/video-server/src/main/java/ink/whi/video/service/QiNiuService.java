package ink.whi.video.service;

import com.qiniu.storage.model.DefaultPutRet;
import ink.whi.common.vo.page.PageListVo;
import ink.whi.common.vo.page.PageParam;
import ink.whi.video.model.dto.QiniuQueryCriteria;
import ink.whi.video.model.dto.VideoInfoDTO;
import ink.whi.video.repo.qiniu.entity.QiniuConfig;
import ink.whi.video.repo.qiniu.entity.QiniuContent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: qing
 * @Date: 2023/10/25
 */
public interface QiNiuService {
    QiniuConfig getConfig();

    void setConfig(QiniuConfig config);

    PageListVo<QiniuContent> queryFiles(QiniuQueryCriteria criteria, PageParam pageParam);

    DefaultPutRet upload(MultipartFile file) throws IOException;

    QiniuContent queryContentById(Long id);

    String download(VideoInfoDTO videoInfoDTO, QiniuConfig config);
}