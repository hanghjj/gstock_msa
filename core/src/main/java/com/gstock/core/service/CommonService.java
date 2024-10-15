package com.gstock.core.service;

import com.gstock.core.dto.UserLogInVerificationDto;
import com.gstock.core.entity.MsgCd;
import com.gstock.core.entity.User;
import com.gstock.gutils.exception.CustomException;

import java.util.List;

public interface CommonService {
    public void insertMsgCd(MsgCd msgCd) throws CustomException;
    public MsgCd selectMsgCd(String cd);
    public void insertUser(User user) throws CustomException;
    public User getUser(String id) throws CustomException;
    public List<User> getAllUser() throws CustomException;
    public UserLogInVerificationDto signIn(User user);

}
