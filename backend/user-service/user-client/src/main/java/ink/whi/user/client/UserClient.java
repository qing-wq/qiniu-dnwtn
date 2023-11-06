package ink.whi.user.client;

import ink.whi.common.enums.OperateTypeEnum;
import ink.whi.common.enums.VideoTypeEnum;
import ink.whi.common.model.dto.CommentDTO;
import ink.whi.common.model.dto.SimpleUserInfoDTO;
import ink.whi.common.model.dto.UserFootDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qing
 * @Date: 2023/10/24
 */
@FeignClient(value = "user-service", fallback = UserClientResolver.class)
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "client/base/{userId}")
    SimpleUserInfoDTO queryBasicUserInfo(@PathVariable Long userId);

    @GetMapping(path = "client/simple/{userId}")
    SimpleUserInfoDTO querySimpleUserInfo(@PathVariable Long userId);

    @PostMapping(path = "client/foot/user")
    UserFootDTO saveUserFoot(@RequestParam VideoTypeEnum videoType, @RequestParam Long videoId,
                             @RequestParam Long author, @RequestParam Long userId, @RequestParam OperateTypeEnum operate);

    @PostMapping(path = "client/foot/comment")
    void saveCommentFoot(@RequestBody CommentDTO comment, @RequestParam Long userId, @RequestParam(required = false) Long parentCommentUser);

    /**
     * 获取评论点赞数
     *
     * @param commentId
     * @return
     */
    @GetMapping(path = "client/comment/{commentId}")
    Integer queryCommentPraiseCount(@PathVariable Long commentId);

    /**
     * 获取当前用户点赞状态
     *
     * @param commentId
     * @param loginUserId
     * @return
     */
    @GetMapping(path = "client/foot/praise")
    UserFootDTO queryUserFoot(@RequestParam Long commentId, @RequestParam Integer type, @RequestParam Long loginUserId);
}
