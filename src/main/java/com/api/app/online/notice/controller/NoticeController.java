package com.api.app.online.notice.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.app.online.common.vo.DefaultResponse;
import com.api.app.online.commonMethod;
import com.api.app.online.notice.service.NoticeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "공지사항")
@RequestMapping("/notice")
@RestController
public class NoticeController {

    @Autowired
    NoticeServiceImpl noticeService;
    @Autowired
    commonMethod cm;

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @ApiOperation(value = "공지사항 목록")
    @PostMapping("/list")
    public DefaultResponse selectNoticeList(HttpServletRequest req, @RequestParam Map<String, Object> params) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            int page = Integer.parseInt((String) params.get("page"));
            params.put("page", (page - 1) * 10);

            defaultResponse = noticeService.selectNoticeList(params);
        } catch (Exception e) {
            LOGGER.error("selectNoticeList = ", e);
            defaultResponse = DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F")
                    .build();
        }
        return defaultResponse;
    }

    @ApiOperation(value = "공지사항 상세")
    @PostMapping("/detail")
    public DefaultResponse selectNoticeDetail(@RequestParam Map<String, Object> params) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            defaultResponse = noticeService.selectNoticeDetail(params);
        } catch (Exception e) {
            LOGGER.error("selectNoticeDetail = ", e);
            defaultResponse = DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F")
                    .build();
        }
        return defaultResponse;
    }

    @ApiOperation(value = "공지사항 등록")
    @PostMapping("/insert")
    public DefaultResponse insertNotice(@RequestParam Map<String, Object> params) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            if (((String) params.get("title") == null) || ((String) params.get("content") == null)) {
                return DefaultResponse.builder().code(400).msg("You check the required fields").result("F").build();
            }
            String content = (String) params.get("content");
            params.put("content", cm.getHtmlReplace(content, "esc"));

            defaultResponse = noticeService.insertNotice(params);
        } catch (Exception e) {
            LOGGER.error("insertNotice = ", e);
            defaultResponse = DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F")
                    .build();
        }
        return defaultResponse;
    }

    @ApiOperation(value = "공지사항 수정")
    @PostMapping("/update")
    public DefaultResponse updateNotice(@RequestParam Map<String, Object> params) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            if (((String) params.get("noticeId") == null)) {
                return DefaultResponse.builder().code(400).msg("You check the required fields").result("F").build();
            }
            if ((String) params.get("content") != null) {
                String content = (String) params.get("content");
                params.put("content", cm.getHtmlReplace(content, "esc"));
            }
            defaultResponse = noticeService.updateNotice(params);
        } catch (Exception e) {
            LOGGER.error("updateNotice = ", e);
            defaultResponse = DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F")
                    .build();
        }
        return defaultResponse;
    }

    @ApiOperation(value = "공지사항 삭제")
    @PostMapping("/delete")
    public DefaultResponse deleteNotice(@RequestParam Map<String, Object> params) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            if (((String) params.get("noticeId") == null)) {
                return DefaultResponse.builder().code(400).msg("You check the required fields").result("F").build();
            }
            defaultResponse = noticeService.deleteNotice(params);
        } catch (Exception e) {
            LOGGER.error("deleteNotice = ", e);
            defaultResponse = DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F")
                    .build();
        }
        return defaultResponse;
    }

    @ApiOperation(value = "공지사항 복구")
    @PostMapping("/restore")
    public DefaultResponse restoreNotice(@RequestParam Map<String, Object> params) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            if (((String) params.get("noticeId") == null)) {
                return DefaultResponse.builder().code(400).msg("You check the required fields").result("F").build();
            }
            defaultResponse = noticeService.restoreResult(params);
        } catch (Exception e) {
            LOGGER.error("restoreNotice = ", e);
            defaultResponse = DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F")
                    .build();
        }
        return defaultResponse;
    }

}