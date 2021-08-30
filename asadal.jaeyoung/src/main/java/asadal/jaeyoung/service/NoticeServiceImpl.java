package asadal.jaeyoung.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import asadal.jaeyoung.dao.NoticeDAO;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.util.FileUtils;
import asadal.jaeyoung.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{

	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	private NoticeDAO noticeDAO;
	
	@Override
	public void noticeWrite(NoticeVO noticeVO, MultipartHttpServletRequest noticeRequest) throws Exception{
		noticeDAO.noticeWrite(noticeVO);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(noticeVO,noticeRequest);
		int size=list.size();
		for(int i=0;i<size;i++) {
			noticeDAO.insertFile(list.get(i));
			
		}
	}
	
	@Override
	public List<NoticeVO> noticeList(SearchCriteria scri) throws Exception{
		return noticeDAO.noticeList(scri);
	}
	@Override
	public int noticeListCount(SearchCriteria scri) throws Exception{
		return noticeDAO.noticeListCount(scri);
	}
	
	@Override
	public NoticeVO noticeRead(int id) throws Exception{
		return noticeDAO.noticeRead(id);
	}
	
	@Override
	public void noticeUpdate(NoticeVO noticeVO, String[] files,String[] fileNames, MultipartHttpServletRequest noticeRequest) throws Exception{
		
		noticeDAO.noticeUpdate(noticeVO);
		
		List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(noticeVO,files,fileNames,noticeRequest);
		Map<String,Object> tempMap = null;
		int size=list.size();
		for(int i = 0;i<size;i++) {
			tempMap=list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				noticeDAO.insertFile(tempMap);
			}else {
				noticeDAO.updateFile(tempMap);
			}
		}
	}
	
	@Override
	public void noticeDelete(int id) throws Exception{
		noticeDAO.noticeDelete(id);
	}
	@Override
	public List<Map<String,Object>> selectFileList(int id) throws Exception{
		return noticeDAO.selectFileList(id);
	}
	@Override
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception{
		System.out.println(map+"service");

		return noticeDAO.selectFileInfo(map);
	}
	
}
