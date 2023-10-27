package ink.whi.video.repo.video.dao;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import ink.whi.common.enums.YesOrNoEnum;
import ink.whi.common.vo.base.BaseDO;
import ink.whi.video.model.dto.VideoInfoDTO;
import ink.whi.video.model.video.CategoryDTO;
import ink.whi.video.model.video.TagDTO;
import ink.whi.video.repo.video.converter.VideoConverter;
import ink.whi.video.repo.video.entity.CategoryDO;
import ink.whi.video.repo.video.entity.VideoDO;
import ink.whi.video.repo.video.entity.VideoTagDO;
import ink.whi.video.repo.video.mapper.CategoryMapper;
import ink.whi.video.repo.video.mapper.VideoMapper;
import ink.whi.video.repo.video.mapper.VideoTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/10/26
 */
@Repository
public class VideoDao extends ServiceImpl<VideoMapper, VideoDO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private VideoTagMapper videoTagMapper;

    public VideoInfoDTO queryById(Long videoId) {
        VideoDO video = lambdaQuery().eq(VideoDO::getDeleted, YesOrNoEnum.NO.getCode())
                .eq(BaseDO::getId, videoId)
                .one();
        return VideoConverter.toDto(video);
    }


    public CategoryDTO queryCategoryById(Long categoryId) {
        LambdaQueryChainWrapper<CategoryDO> wrapper = ChainWrappers.lambdaQueryChain(categoryMapper);
        CategoryDO category = wrapper.eq(BaseDO::getId, categoryId)
                .eq(CategoryDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
        return VideoConverter.toDto(category);
    }

    public List<TagDTO> listTagsDetail(Long videoId) {
        return videoTagMapper.listVideoTagDetails(videoId);
    }
}
