package ink.whi.user.repo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.common.enums.CollectionStatEnum;
import ink.whi.common.enums.PraiseStatEnum;
import ink.whi.common.enums.ReadStatEnum;
import ink.whi.common.enums.VideoTypeEnum;
import ink.whi.common.vo.base.BaseDO;
import ink.whi.common.vo.page.PageParam;
import ink.whi.user.model.dto.VideoFootCountDTO;
import ink.whi.user.repo.entity.UserFootDO;
import ink.whi.user.repo.mapper.UserFootMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/10/24
 */
@Repository
public class UserFootDao extends ServiceImpl<UserFootMapper, UserFootDO> {
    /**
     * 查询作者全部视频计数信息
     * @param userId
     * @return
     */
    public VideoFootCountDTO countArticleByUserId(Long userId) {
        return baseMapper.countVideoByUserId(userId);
    }

    public VideoFootCountDTO countArticleByArticleId(Long articleId) {
        return baseMapper.countVideoInfoByVideoId(articleId);
    }

    public UserFootDO getRecordByDocumentAndUserId(VideoTypeEnum type, Long articleId, Long userId) {
        return lambdaQuery().eq(UserFootDO::getVideoId, articleId)
                .eq(UserFootDO::getUserId, userId)
                .eq(UserFootDO::getType, type.getCode())
                .one();
    }

    public UserFootDO getByDocumentAndUserId(Long commentId, Integer documentType, Long userId) {
        return lambdaQuery().eq(UserFootDO::getVideoId, commentId)
                .eq(UserFootDO::getType, documentType)
                .eq(UserFootDO::getUserId, userId)
                .one();
    }

    public Integer countCommentPraise(Long commentId) {
        return lambdaQuery().eq(UserFootDO::getVideoId, commentId)
                .eq(UserFootDO::getType, VideoTypeEnum.COMMENT.getCode())
                .eq(UserFootDO::getPraiseStat, PraiseStatEnum.PRAISE.getCode())
                .count().intValue();
    }

    /**
     * 用户浏览记录列表
     * @param userId
     * @param pageParam
     * @return
     */
    public List<Long> listReadArticleByUserId(Long userId, PageParam pageParam) {
        List<UserFootDO> list = lambdaQuery().eq(UserFootDO::getUserId, userId)
                .eq(UserFootDO::getReadStat, ReadStatEnum.READ.getCode())
                .eq(UserFootDO::getType, VideoTypeEnum.ARTICLE.getCode())
                .last(PageParam.getLimitSql(pageParam))
                .orderByDesc(BaseDO::getUpdateTime)
                .list();
        return list.stream().map(UserFootDO::getVideoId).toList();
    }

    /**
     * 用户收藏列表
     * @param userId
     * @param pageParam
     * @return
     */
    public List<Long> listCollectedArticlesByUserId(Long userId, PageParam pageParam) {
        List<UserFootDO> list = lambdaQuery().eq(UserFootDO::getUserId, userId)
                .eq(UserFootDO::getCollectionStat, CollectionStatEnum.COLLECTION.getCode())
                .eq(UserFootDO::getType, VideoTypeEnum.ARTICLE.getCode())
                .orderByDesc(BaseDO::getUpdateTime)
                .last(PageParam.getLimitSql(pageParam))
                .list();
        return list.stream().map(UserFootDO::getVideoId).toList();
    }
}
