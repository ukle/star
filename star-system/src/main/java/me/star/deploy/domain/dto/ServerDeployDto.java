package me.star.deploy.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import me.star.base.BaseDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/4
 */
@Data
public class ServerDeployDto extends BaseDTO implements Serializable {

    private Long id;

    private String name;

    private String ip;

    private Integer port;

    private String account;

    @JSONField(serialize = false)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerDeployDto that = (ServerDeployDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

