package chilling.encore.controller;

import chilling.encore.dto.ListenTogetherDto.AllPopularListenTogether;
import chilling.encore.dto.ListenTogetherDto.CreateListenTogetherRequest;
import chilling.encore.dto.ListenTogetherDto.ListenTogetherPage;
import chilling.encore.global.dto.ResponseDto;
import chilling.encore.service.ListenTogetherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static chilling.encore.dto.responseMessage.ListenTogetherConstants.ListenTogetherFailMessage.SAVE_FAIL_MESSAGE;
import static chilling.encore.dto.responseMessage.ListenTogetherConstants.ListenTogetherSuccessMessage.*;
import static chilling.encore.global.dto.ResponseCode.globalFailCode.AUTHORIZATION_FAIL_CODE;
import static chilling.encore.global.dto.ResponseCode.globalFailCode.SERVER_ERROR;
import static chilling.encore.global.dto.ResponseCode.globalSuccessCode.CREATE_SUCCESS_CODE;
import static chilling.encore.global.dto.ResponseCode.globalSuccessCode.SELECT_SUCCESS_CODE;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/listen")
@Api(tags = "ListenTogetherPage API")
public class ListenTogetherController {
    private final ListenTogetherService listenTogetherService;

    @GetMapping(value = {"/page/{region}/{page}", "/page/{region}"})
    @ApiOperation(value = "같이할래요 게시글 페이징 조회")
    public ResponseEntity<ResponseDto<ListenTogetherPage>> getListenTogetherPage(@PathVariable String region, @PathVariable @Nullable Integer page) {
        ListenTogetherPage listenTogetherPage = listenTogetherService.getListenTogetherPage(region, page);
        return ResponseEntity.ok(ResponseDto.create(SELECT_SUCCESS_CODE.getCode(), SELECT_SUCCESS_MESSAGE.getMessage(), listenTogetherPage));
    }

    @PostMapping("/save")
    @ApiOperation(value = "같이할래요 글 작성", notes = "programIdx 함께 넘겨주세요")
    public ResponseEntity<ResponseDto> save(@RequestBody CreateListenTogetherRequest createListenTogetherRequest) {
        try {
            listenTogetherService.save(createListenTogetherRequest);
            return ResponseEntity.ok(ResponseDto.create(CREATE_SUCCESS_CODE.getCode(), CREATE_SUCCESS_MESSAGE.getMessage()));
        } catch (ClassCastException e) {
            return ResponseEntity.ok(ResponseDto.create(AUTHORIZATION_FAIL_CODE.getCode(), SAVE_FAIL_MESSAGE.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.ok(ResponseDto.create(SERVER_ERROR.getCode(), SAVE_FAIL_MESSAGE.getMessage()));
        }
    }
    
    @GetMapping("/popular")
    @ApiOperation(value = "내지역 인기글 조회", notes = "로그인하지 않은 경우 인기 지역에서 조회")
    public ResponseEntity<ResponseDto<AllPopularListenTogether>> getPopular() {
        AllPopularListenTogether allPopularListenTogethers = listenTogetherService.popularListenTogether();
        return ResponseEntity.ok(ResponseDto.create(SELECT_SUCCESS_CODE.getCode(), SELECT_POPULAR_SUCCESS_MESSAGE.getMessage(), allPopularListenTogethers));
    }
}
