package tmp_package;

import java.util.List;

import kr.or.connect.diseasepredict.board.dto.Criteria;
import kr.or.connect.diseasepredict.board.dto.ReplyPageDTO;
import kr.or.connect.diseasepredict.board.dto.ReplyVO;

public interface ReplyService 
{
	public int register(ReplyVO vo);
	
	public ReplyVO get(Long rno);
	
	public int modify (ReplyVO vo);
	
	public int remove(Long rno);
	
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
