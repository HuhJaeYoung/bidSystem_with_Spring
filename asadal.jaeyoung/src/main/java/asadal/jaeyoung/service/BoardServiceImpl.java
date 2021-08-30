package asadal.jaeyoung.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import asadal.jaeyoung.dao.BoardDAO;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public void boardWrite(BoardVO boardVO,String writer) throws Exception{
		boardVO.setBoard_writer(writer);
		boardDAO.boardWrite(boardVO);
	}
	
	@Override
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception{
		return boardDAO.boardList(scri);
	}
	@Override
	public int boardListCount(SearchCriteria scri) throws Exception{
		return boardDAO.boardListCount(scri);
	}
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO boardRead(int id) throws Exception{
		boardDAO.boardHit(id);
		return boardDAO.boardRead(id);
	}
	
	@Override
	public void boardUpdate(BoardVO boardVO) throws Exception{
		System.out.println(boardVO+"service");

	}
	
	@Override
	public void boardDelete(int id) throws Exception{
		boardDAO.boardDelete(id);
	}

}
