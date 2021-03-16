package kr.or.connect.diseasepredict.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.diseasepredict.board.dto.BoardVO;
import kr.or.connect.diseasepredict.dao.BoardDao;
import kr.or.connect.diseasepredict.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService 
{
	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<BoardVO> getList() {
		List<BoardVO> list = boardDao.getList();
		return list;
	}

	@Override
	public BoardVO read(Long bno) {
		BoardVO readBoard = boardDao.read(bno);
		return readBoard;
	}

	@Override
	@Transactional(readOnly = false)
	public Long delete(Long bno) {
		boardDao.delete(bno);
		return bno;		
	}

	@Override
	@Transactional(readOnly = false)
	public Long update(BoardVO boardVO, Long bno) {
		boardDao.update(boardVO, bno);
		return bno;
	}

	@Override
	public Long getTotalCount() {		
		long count = boardDao.getTotalCount();
		return count;
	}

	@Override
	@Transactional(readOnly = false)
	public BoardVO insert(BoardVO boardVO) {
		boardVO.setUpdateDate(new Date());
		boardVO.setRegdate(new Date());
		long count = boardDao.insert(boardVO);
		boardVO.setBno(count);
		return boardVO;
	}	
}
