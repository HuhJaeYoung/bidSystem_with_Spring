package asadal.jaeyoung.service;

import java.util.List;


import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.BoardVO;

public interface BoardService {
	public void boardWrite(BoardVO boardVO,String writer) throws Exception;
	
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception;
	
	public int boardListCount(SearchCriteria scri) throws Exception;
	
	public BoardVO boardRead(int id) throws Exception;
	
	public void boardUpdate(BoardVO boardVO) throws Exception;
	
	public void boardDelete(int id) throws Exception;

}
