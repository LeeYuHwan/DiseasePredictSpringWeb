package kr.or.connect.diseasepredict.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.diseasepredict.board.dto.BoardVO;
import kr.or.connect.diseasepredict.board.dto.Criteria;
import kr.or.connect.diseasepredict.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService 
{
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) 
	{
		// TODO Auto-generated method stub
		System.out.println("register......" + board);
		
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) 
	{
		// TODO Auto-generated method stub
		System.out.println("get....." + bno);
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) 
	{
		// TODO Auto-generated method stub
		System.out.println("modify....." + board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) 
	{
		// TODO Auto-generated method stub
		System.out.println("remove...." + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) 
	{
		// TODO Auto-generated method stub
		System.out.println("get List with criteria: " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		System.out.println("get total count");
		return mapper.getTotalCount(cri);
	}
}
