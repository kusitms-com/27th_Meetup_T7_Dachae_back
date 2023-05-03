package chilling.encore.controller;

import chilling.encore.dto.MainDto.MainResponse;
import chilling.encore.dto.ProgramDto.NewProgramsResponse;
import chilling.encore.global.dto.ResponseDto;
import chilling.encore.service.MainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static chilling.encore.global.dto.ResponseCode.globalSuccessCode.*;
import static chilling.encore.dto.responseMessage.MainConstants.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
@Api(tags = "MainPage API")
public class MainController {
    private final MainService mainService;

    @GetMapping()
    @ApiOperation(value = "메인페이지 접근", notes = "필요한 모든 데이터 전송")
    public ResponseEntity<ResponseDto<MainResponse>> getFirstPage() {
        MainResponse firstPage = mainService.getFirstPage();
        return ResponseEntity.ok(ResponseDto.create(SELECT_SUCCESS_CODE.getCode(), MAIN_SELECT_SUCCESS.getMessage(), firstPage));
    }

    @GetMapping("/{region}")
    @ApiOperation(value = "새소식 관심지역 변경", notes = "새소식에 관심 지역이 변경됨")
    public ResponseEntity<ResponseDto<NewProgramsResponse >> getNewPrograms(@PathVariable String region) {
        NewProgramsResponse newPrograms = mainService.getNewPrograms(region);
        return ResponseEntity.ok(ResponseDto.create(SELECT_SUCCESS_CODE.getCode(), CHANGE_NEWPROGRAM_SUCCESS.getMessage(), newPrograms));
    }
}
