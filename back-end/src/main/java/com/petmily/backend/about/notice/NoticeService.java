package com.petmily.backend.about.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.about.domain.Notice;
import com.petmily.backend.about.dto.NoticeForm;
import com.petmily.backend.about.dto.NoticeList;
import com.petmily.backend.about.dto.NoticeView;
import com.petmily.backend.global.DataNotFoundException;
import com.petmily.backend.member.login.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
	private final NoticeRepository noticeRepository;
	private final MemberRepository memberRepository;
	@Autowired
	private final HttpSession httpSession;

	public Page<NoticeList> getList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<Notice> notice = null;
		Page<NoticeList> listDto = null;
		
		if(keyword.isEmpty()) {
			notice = this.noticeRepository.findAll(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				notice = this.noticeRepository.findBySubjectContainingOrContentContaining(keyword, keyword, pageable);
				break;
			case "subject":
				notice = this.noticeRepository.findBySubjectContaining(keyword, pageable);
				break;
			case "content":
				notice = this.noticeRepository.findByContentContaining(keyword, pageable);
				break;
			}
		}
		listDto = notice.map(n -> NoticeList.NoticetoListDto(n));
		return listDto;
	}

	public NoticeView viewNotice(Long no) {
		Optional<NoticeView> noticeView = this.noticeRepository.getNoticeView(no);
		if(noticeView.isPresent()) {
			this.noticeRepository.updateViews(no);
			return noticeView.get();
		}
		
//		System.out.println(view);
//		this.noticeRepository.updateViews(no);
//		view.setCount(view.getCount()+1);

//		Optional<Notice> notice = this.noticeRepository.findById(boardNum);
//		if(notice.isPresent()) {
//			this.noticeRepository.updateViews(boardNum);
//			Optional<NoticeView> noticeView = notice.map(n -> NoticeView.toNoticeView(n));
//			noticeView.get().setCount(notice.get().getCount() + 1);
//			return noticeView.get();
//		}
		else throw new DataNotFoundException("noticeView not found");
	}

	public void insertNotice(NoticeForm noticeForm, String id) {
		System.out.println(id);
		Notice notice = Notice.builder()
				.category("notice")
				.member(this.memberRepository.findByMemberId(id))
				.subject(noticeForm.getSubject())
				.content(noticeForm.getContent())
				.postDate(LocalDateTime.now())
				.count(0)
				.build();
		this.noticeRepository.save(notice);
	}
}
