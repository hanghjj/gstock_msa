package com.gstock.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "GS_USER")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Id
    private String id;
    private String password;
    private String email;
    @Column(name = "USER_SE_CD")
    private String userSeCd;
    @Column(name = "PHONE_NUM")
    private String phoneNum;
    @Column(name = "REG_DT")
    private String regDt;
    @Column(name = "MDF_DT")
    private String mdfDt;
}
