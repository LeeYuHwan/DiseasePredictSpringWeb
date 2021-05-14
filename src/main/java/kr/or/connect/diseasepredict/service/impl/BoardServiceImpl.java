package kr.or.connect.diseasepredict.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.diseasepredict.board.dto.BoardVO;
import kr.or.connect.diseasepredict.board.dto.boardPasswdCheck;
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
	public boardPasswdCheck GetHashedPassed(Long bno) {
		boardPasswdCheck boardPasswdcheck = boardDao.GetHashedPassed(bno);
		return boardPasswdcheck;
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
		boardVO.setUpdateDate(new Date());
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

	@Override
	public List<BoardVO> getTitle(String search) {
		List<BoardVO> list = boardDao.getTitle(search);
		return list;
	}

	@Override
	public List<BoardVO> getContent(String search) {
		List<BoardVO> list = boardDao.getContent(search);
		return list;
	}

	@Override
	public List<BoardVO> getWriter(String search) {
		List<BoardVO> list = boardDao.getWriter(search);
		return list;
	}

	@Override
	public List<BoardVO> getContentTitle(String search) {
		List<BoardVO> list = boardDao.getContentTitle(search);
		return list;
	}

	@Override
	public List<BoardVO> getTitleWriter(String search) {
		List<BoardVO> list = boardDao.getTitleWriter(search);
		return list;
	}

	@Override
	public List<BoardVO> getTitleContentWriter(String search) {
		List<BoardVO> list = boardDao.getTitleContentWriter(search);
		return list;
	}
	
}
