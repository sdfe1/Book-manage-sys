package com.zll.pojo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Follow {

    @Id
    @NotNull(message = "被关注用户ID不能为空")
    @Min(value = 1, message = "用户ID不合法")
    private Long followerId;

    @NotNull(message = "被关注用户ID不能为空")
    @Min(value = 1, message = "用户ID不合法")
    private Long followedId;

    private LocalDateTime createTime;
}
