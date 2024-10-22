package com.gstock.core.service;

import com.gstock.core.dto.UserLogInVerificationDto;
import com.gstock.core.entity.MsgCd;
import com.gstock.core.entity.User;
import com.gstock.core.repository.MsgCdRepository;
import com.gstock.gutils.exception.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.gstock.core.CoreConstants.USER_NORMAL;


@Service
@Transactional
public class CommonServiceImpl implements CommonService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EntityManager em;
    @Autowired
    private MsgCdRepository repository;
    @Autowired
    private AuthJwtService authService;

    @Override
    public void insertMsgCd(MsgCd msgCd) throws CustomException {
        String code = msgCd.getCode();
        if (!code.equals(findMsgCd(code))) em.persist(msgCd);
        else throw new CustomException("message.duplicate.error");
    }


    public String findMsgCd(String cd){
        return repository.findByCode(cd).stream()
                .findAny()
                .map(MsgCd::getCode)
                .orElse("");
    }

    @Override
    public MsgCd selectMsgCd(String cd) {
        List<MsgCd> resultList = repository.findByCode(cd);
        return resultList.stream()
                .findFirst()
                .orElseThrow(()-> new CustomException("message.search.fail"));
    }

    @Override
    public void insertUser(User user) throws CustomException {
        user.setUserSeCd(Optional.ofNullable(user.getUserSeCd()).orElse(USER_NORMAL));
        if(user.getUserSeCd().length()!=1) user.setUserSeCd(null);
        try {
            String initialPw = user.getPassword();
            String encodedPw = passwordEncoder.encode(initialPw);
            user.setPassword(encodedPw);
            em.persist(user);
        }catch (Exception e){
            throw new CustomException("common.register.error");
        }
    }

    @Override
    public User getUser(String id) throws CustomException {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUser() throws CustomException {
        return em.createQuery("SELECT U FROM GS_USER U", User.class)
                .getResultList()
                .stream()
                .toList();
    }

    @Override
    public UserLogInVerificationDto signIn(User logUser) {
        User user = em.find(User.class, logUser.getId());
        String token = authService.getToken(logUser.getId());
        if (user == null) { //등록되지 않은 아이디
            return new UserLogInVerificationDto(null, false, null);
        } else if (passwordEncoder.matches(logUser.getPassword(), user.getPassword())) { // pw 일치 여부 확인
            return new UserLogInVerificationDto(user.getId(), true, token);
        } else {
            return new UserLogInVerificationDto(null, false, null);
        }
    }
}
