package asadal.jaeyoung.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.NoticeVO;

public interface NoticeService {
	
	public void noticeWrite(NoticeVO notiveVO, MultipartHttpServletRequest noticeRequest) throws Exception;
	
	public List<NoticeVO> noticeList(SearchCriteria scri) throws Exception;
	
	public int noticeListCount(SearchCriteria scri) throws Exception;
	
	public NoticeVO noticeRead(int id) throws Exception;
	
	//첨부파일 수정 추가
	public void noticeUpdate(NoticeVO noticeVO,String[] files,String[] fileNames,MultipartHttpServletRequest noticeRequest) throws Exception;
	
	public void noticeDelete(int id) throws Exception;
	
	//첨부파일 조회
	public List<Map<String,Object>> selectFileList(int id) throws Exception;
	
	//첨부파일 다운
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception;
	

}
