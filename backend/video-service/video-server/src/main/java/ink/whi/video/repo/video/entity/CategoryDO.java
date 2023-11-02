package ink.whi.video.repo.video.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.common.model.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author: qing
 * @Date: 2023/10/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("category")
public class CategoryDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 状态：0-未发布，1-已发布
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer rank;

    private Integer deleted;
}
