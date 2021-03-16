package tmp_package;

/*@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController 
{
	private ReplyService service;
	
	//@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo)
	{
		System.out.println("ReplyVO: " + vo);
		int insertCount = service.register(vo);
		System.out.println("Reply INSERT COUNT: " + insertCount);
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		Criteria cri = new Criteria(page, 10);
		System.out.println("get Reply List bno: " + bno);
		System.out.println("cri:" + cri);
		return new ResponseEntity<>(service.getListPage(cri,  bno), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno)
	{
		System.out.println("get: " + rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	//@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(value = "/{rno}")
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno)
	{
		System.out.println("remove: " + rno);
		System.out.println("replyer: " + vo.getReplyer());
		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//@PreAuthorize("principal.username == #vo.replyer")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", consumes = "application/json")
	public ResponseEntity<String> modify (@RequestBody ReplyVO vo, @PathVariable("rno") Long rno)
	{
		System.out.println("rno: " + rno);
		System.out.println("modify: " + vo);
		return service.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}*/
