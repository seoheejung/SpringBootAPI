package com.api.app.online.notice.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NoticeMapper {
	/* 공지사항 목록 조회 */
	List<Map<String, Object>> selectNoticeList(Map<String, Object> params);

	/* 공지사항 상세  */
	Map<String, Object> selectNoticeDetail(Map<String, Object> params);
	/* 공지사항 조회수 증가 */
	int updateNoticeHits(Map<String, Object> params);
	
	/* 공지사항 등록 */
	int insertNotice(Map<String, Object> params);
	
	/* 공지사항 수정 */
	int updateNotice(Map<String, Object> params);

	/* 공지사항 삭제 */
	int deleteNotice(Map<String, Object> params);
	
	/* 공지사항 복구 */
	int restoreResult(Map<String, Object> params);
}
