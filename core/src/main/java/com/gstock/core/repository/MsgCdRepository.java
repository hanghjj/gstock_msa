package com.gstock.core.repository;

import com.gstock.core.entity.MsgCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgCdRepository extends JpaRepository<MsgCd, Long> {

    public List<MsgCd> findByCode(String code);
}
