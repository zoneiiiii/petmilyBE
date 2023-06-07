package com.petmily.backend.adopt.adoptInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("/adopt")
public class AdoptContoller {
	private final AdoptService adoptService;
	private final HttpSession httpSession;

	@Autowired
	public AdoptContoller(AdoptService adoptService, HttpSession httpSession) {
		this.adoptService = adoptService;
		this.httpSession = httpSession;
	}
	
	@PostMapping("/insert")
    public ResponseEntity<AdoptDto> application(@RequestBody AdoptDto adoptDto, HttpSession session){
        System.out.println(session.getAttribute("id"));
        String memberId = (String)session.getAttribute("id");
        AdoptDto application = adoptService.application(adoptDto, memberId);
        return ResponseEntity.ok(application);
    }
	
	@GetMapping("/list")
	public Page<Adopt> adoptList(Pageable pageable){
//		String memberId = (String)session.getAttribute("id");
//		List<Adopt> adoptList = adoptService.adoptList();
//	    model.addAttribute("list",adoptList);
	    return adoptService.adoptList(pageable);
	}
	 @GetMapping("/counts")
	 public AdoptCountDto getAdoptCounts() {
	    return adoptService.getAdoptCounts();
	 }
	 
	@PutMapping("/{adoptNum}")
	public void updateReview(@PathVariable Long adoptNum, @RequestBody Adopt adopt) {
		adoptService.updateAdopt(adoptNum, adopt);
	}
}
