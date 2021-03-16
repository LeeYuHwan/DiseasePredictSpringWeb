package kr.or.connect.diseasepredict.service;

import java.util.List;

import kr.or.connect.diseasepredict.board.dto.BoardVO;

public interface BoardService 
{
	public List<BoardVO> getList();
	public BoardVO read(Long bno);
	public Long delete(Long bno);
	public Long update(BoardVO boardVO, Long bno);
	public Long getTotalCount();
	public BoardVO insert(BoardVO boardVO);
}
