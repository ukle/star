package me.star.zheshan.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
* @author Star Chou
* @date 2022-01-15
**/
@Data
public class ZheshanDto implements Serializable {

    /** 编码 */
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long zsId;

    /** 类型 */
    private String zsType;

    /** 尺寸 */
    private String zsLength;

    /** 方数 */
    private String zsFangshu;

    /** 头型 */
    private String zsTouxing;

    /** 排口 */
    private String zsPaikou;

    /** 肩部 */
    private String zsJianbu;

    /** 烫钉 */
    private String zsTangding;

    /** 买入价 */
    private Integer zsSaleIn;

    /** 卖出价 */
    private Integer zsSaleOut;

    /** 作者 */
    private String zsAuthor;

    /** 描述 */
    private String zsDesc;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    private String saleStatus;
}
