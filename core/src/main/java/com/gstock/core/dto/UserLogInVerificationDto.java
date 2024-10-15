package com.gstock.core.dto;

import com.gstock.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLogInVerificationDto extends BaseEntity {
    String id;
    boolean verified;
    String token;
}
