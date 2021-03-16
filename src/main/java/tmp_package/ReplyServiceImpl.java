package tmp_package;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.diseasepredict.board.dto.Criteria;
import kr.or.connect.diseasepredict.board.dto.ReplyPageDTO;
import kr.or.connect.diseasepredict.board.dto.ReplyVO;
import kr.or.connect.diseasepredict.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService 
{
	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) 
	{
		// TODO Auto-generated method stub
		System.out.println("register......" + vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) 
	{
		// TODO Auto-generated method stub
		System.out.println("get......" + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) 
	{
		// TODO Auto-generated method stub
		System.out.println("modify......" + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) 
	{
		// TODO Auto-generated method stub
		System.out.println("remove...." + rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) 
	{
		// TODO Auto-generated method stub
		System.out.println("get Reply List of a Board " + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		return new ReplyPageDTO (
				mapper.getCountByBno(bno), 
				mapper.getListWithPaging(cri, bno));
	}		
}
