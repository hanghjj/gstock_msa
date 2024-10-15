package com.gstock.core.controller;

import com.gstock.core.dto.UserLogInVerificationDto;
import com.gstock.core.entity.MsgCd;
import com.gstock.core.entity.User;
import com.gstock.core.service.CommonService;
import com.gstock.gutils.exception.CustomException;
import com.gstock.gutils.util.DateTimeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/comm")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Common", description = "공통 기능 관련 API")
public class CommonController {
    private final CommonService commonService;

    @GetMapping("/msg/add")
    @Operation(summary = "신규 메세지코드 등록", description = "메세지 코드 신규등록")
    @Tag(name = "Common")
    public ResponseEntity<MsgCd> insertNewMessage(
            @Parameter(name = "code", description = "메세지코드") @RequestParam(value = "code") String code,
            @Parameter(name = "massage", description = "메세지") @RequestParam(value = "massage") String massage
    ) throws CustomException {
        MsgCd msgCd = new MsgCd(code, massage);
        commonService.insertMsgCd(msgCd);
        return ResponseEntity.status(HttpStatus.OK).body(msgCd);
    }

    @GetMapping("/msg/search")
    @Operation(summary = "메세지코드 조회", description = "메세지 코드 조회")
    @Tag(name = "Common")
    public ResponseEntity<String> searchMessage (
            @Parameter(name = "code", description = "메세지코드") @RequestParam(value = "code") String code
    ) throws CustomException {
        String message = commonService.selectMsgCd(code).getMessage();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }



    @GetMapping("/msg/err")
    @Operation(summary = "CustomExeption 발생", description = "메세지 코드 전달, 에러메세지 리턴")
    @Tag(name = "Common")
    public ResponseEntity<MsgCd> invokeError(
            @Parameter(name = "msgCd", description = "메세지코드") @RequestParam(value = "msgCd") String msgCd,
            @Parameter(name = "parameters", description = "매개변수 리스트(쉼표로 구분)",
                    examples = {
                            @ExampleObject(name = "params", value = "삼성전자,SK하이닉스")
                    }
            ) @RequestParam(value = "parameters", required = false) String parameters
    ) throws CustomException {
        if (parameters == null) throw new CustomException(msgCd);
        List<String> param = Arrays.stream(parameters.split(","))
                .toList();
        throw new CustomException(msgCd, param);
    }

    @PostMapping(value = "/user/register")
    @Operation(summary = "신규가입", description = "신규 회원가입 데이터 저장")
    @Tag(name = "Common")
    public ResponseEntity<User> insertNewUser(@Parameter(name = "user", description = "회원가입정보") @RequestBody User user) {
        System.out.println(user.toString());
        user.setMdfDt(DateTimeUtils.getDateFormat("yyyyMMdd"));
        user.setRegDt(DateTimeUtils.getDateFormat("yyyyMMdd"));
        commonService.insertUser(user);
        user.setPassword("ENCODED");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping(value = "/user/signin")
    @Operation(summary = "로그인", description = "회원 로그인처리")
    @Tag(name = "Common")
    public ResponseEntity<UserLogInVerificationDto> signIn(
            @Parameter(name = "user", description = "로그인정보") @RequestBody User user) {

        UserLogInVerificationDto signedUser = commonService.signIn(user);
        if(!signedUser.isVerified()){
            throw new CustomException("common.login.fail");
        }
        return ResponseEntity.status(HttpStatus.OK).body(signedUser);
    }
}
