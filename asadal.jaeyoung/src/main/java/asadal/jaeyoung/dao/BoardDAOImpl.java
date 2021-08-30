package asadal.jaeyoung.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.BoardVO;


@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void boardWrite(BoardVO boardVO) throws Exception{
		sqlSession.insert("boardMapper.insert",boardVO);
	}
	
	@Override
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception{
		return sqlSession.selectList("boardMapper.listPage",scri);
	}
	@Override
	public int boardListCount(SearchCriteria scri) throws Exception{
		return sqlSession.selectOne("boardMapper.listCount",scri);
	}
	
	@Override 
	public BoardVO boardRead(int board_id) throws Exception{
		return sqlSession.selectOne("boardMapper.read", board_id);
	}
	
	@Override
	public void boardUpdate(BoardVO boardVO) throws Exception{
		 sqlSession.update("boardMapper.update", boardVO);

	}
	
	@Override
	public void boardDelete(int board_id) throws Exception{
		sqlSession.delete("boardMapper.delete", board_id);
	}
	
	@Override
	public void boardHit(int board_id) throws Exception{
		sqlSession.update("boardMapper.boardHit",board_id);
	}
}
