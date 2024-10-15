package com.gstock.core.exceptions;

import com.gstock.core.service.CommonService;
import com.gstock.gutils.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.gstock.core.CoreConstants.DEFAULT_ERR_MSG;

@RestControllerAdvice
@AllArgsConstructor
public class CoreExceptionHandler {

    private final CommonService commonService;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> CustomException(CustomException e){
        String cd = e.getMessageCode();
        List<String> params = Optional.ofNullable(e.getParams()).orElse(Collections.emptyList());
        String msg = commonService.selectMsgCd(cd).getMessage();
        if("".equals(msg)) return new ResponseEntity<>(DEFAULT_ERR_MSG,HttpStatus.INTERNAL_SERVER_ERROR);
        for(int i = 0; i<params.size(); i++){
            msg = msg.replace("@"+(i+1)+"@", params.get(i));
        }
        return new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
