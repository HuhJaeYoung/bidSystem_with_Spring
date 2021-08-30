package asadal.jaeyoung.dao;

import java.util.List;
import java.util.Map;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.NoticeVO;

public interface NoticeDAO {
	
	public void noticeWrite(NoticeVO noticeVO) throws Exception;
	
	//목록
	public List<NoticeVO> noticeList(SearchCriteria scri) throws Exception;
	
	//총 개수 게시물
	public int noticeListCount(SearchCriteria scri) throws Exception;
	
	
	public NoticeVO noticeRead(int id) throws Exception;
	
	public void noticeUpdate(NoticeVO noticeVO) throws Exception;
	
	public void noticeDelete(int id) throws Exception;
	//첨부파일 업로드
	public void insertFile(Map<String,Object> map) throws Exception;
	
	//첨부파일 조회
	public List<Map<String,Object>> selectFileList(int id) throws Exception;
	
	//첨부파일 다운
	public Map<String,Object> selectFileInfo(Map<String,Object>map) throws Exception;
	
	//첨부파일 수정
	public void updateFile(Map<String,Object> map) throws Exception;

}
