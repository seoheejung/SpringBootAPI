package com.api.app.online.notice.service;

import java.util.Map;

import com.api.app.online.common.vo.DefaultResponse;

public interface NoticeService {
    /* 공지사항 목록 조회 */
    DefaultResponse selectNoticeList(Map<String, Object> params);

    /* 공지사항 상세 */
    DefaultResponse selectNoticeDetail(Map<String, Object> params);

    /* 공지사항 등록 */
    DefaultResponse insertNotice(Map<String, Object> params);

    /* 공지사항 수정 */
    DefaultResponse updateNotice(Map<String, Object> params);

    /* 공지사항 삭제 */
    DefaultResponse deleteNotice(Map<String, Object> params);

    /* 공지사항 복구 */
    DefaultResponse restoreResult(Map<String, Object> params);
}
