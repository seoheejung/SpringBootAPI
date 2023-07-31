package com.api.app.online.notice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.app.online.commonMethod;
import com.api.app.online.common.vo.DefaultResponse;
import com.api.app.online.notice.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    commonMethod cm;

    /* 공지사항 목록 조회 */
    @Override
    public DefaultResponse selectNoticeList(Map<String, Object> params) {
        List<Map<String, Object>> noticeList = new ArrayList<Map<String, Object>>();
        noticeList.addAll(noticeMapper.selectNoticeList(params));
        return DefaultResponse.builder().code(200).msg("success").result(noticeList).build();
    }

    /* 공지사항 상세 */
    @Override
    public DefaultResponse selectNoticeDetail(Map<String, Object> params) {
        Map<String, Object> noticeDetail = new HashMap<String, Object>();
        /* 조회수 증가 */
        Integer updateResult = noticeMapper.updateNoticeHits(params);
        if (updateResult > 0) {
            noticeDetail = noticeMapper.selectNoticeDetail(params);

            String content = (String) noticeDetail.get("content");
            noticeDetail.put("content", cm.getHtmlReplace(content, "uesc"));

            return DefaultResponse.builder().code(200).msg("success").result(noticeDetail).build();
        } else {
            return DefaultResponse.builder().code(403).msg("content does not exist").result("F").build();
        }
    }

    /* 공지사항 등록 */
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public DefaultResponse insertNotice(Map<String, Object> params) {
        Integer resultCnt = noticeMapper.insertNotice(params);
        if (resultCnt < 1) {
            return DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F").build();
        } else {
            return DefaultResponse.builder().code(200).msg("success").result("S").build();
        }
    }

    /* 공지사항 수정 */
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public DefaultResponse updateNotice(Map<String, Object> params) {
        Integer resultCnt = noticeMapper.updateNotice(params);
        if (resultCnt < 1) {
            return DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F").build();
        } else {
            return DefaultResponse.builder().code(200).msg("success").result("S").build();
        }
    }

    /* 공지사항 삭제 */
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public DefaultResponse deleteNotice(Map<String, Object> params) {
        Integer resultCnt = noticeMapper.deleteNotice(params);
        if (resultCnt < 1) {
            return DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F").build();
        } else {
            return DefaultResponse.builder().code(200).msg("success").result("S").build();
        }
    }

    /* 공지사항 복구 */
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public DefaultResponse restoreResult(Map<String, Object> params) {
        Integer resultCnt = noticeMapper.restoreResult(params);
        if (resultCnt < 1) {
            return DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F").build();
        } else {
            return DefaultResponse.builder().code(200).msg("success").result("S").build();
        }
    }
}
