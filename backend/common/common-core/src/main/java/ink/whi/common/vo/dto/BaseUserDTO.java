package ink.whi.common.vo.dto;

import ink.whi.common.vo.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * @author: qing
 * @Date: 2023/4/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseUserDTO extends BaseDTO {
    @Serial
    private static final long serialVersionUID = -2426438424647735636L;

    /**
     * 用户id
     */
    private Long userId;

    /*  *
     * 用户名
     */
    private String userName;

    /**
     * 用户角色 必须是String
     */
    private String role;
}
