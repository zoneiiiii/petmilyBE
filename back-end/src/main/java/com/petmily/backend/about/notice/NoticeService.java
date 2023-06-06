package com.petmily.backend.about.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
	private final NoticeRepository noticeRepository;
	private final MemberRepository memberRepository;

	public Page<NoticeList> getList(int page, int limit, String keyword, String searchMode){
		System.out.println("service: " + keyword);
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<Notice> notice = null;
		
		if(keyword.isEmpty()) {
			notice = this.noticeRepository.findAll(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				notice = this.noticeRepository.findBySubjectContainingOrContentContaining(keyword, pageable);
				break;
			case "subject":
				notice = this.noticeRepository.findBySubjectContaining(keyword, pageable);
				break;
			case "content":
				notice = this.noticeRepository.findByContentContaining(keyword, pageable);
				break;
			}
		}
		Page<NoticeList> noticeList = notice.map(n -> NoticeList.NoticetoListDto(n));
		return noticeList;
	}

	@Transactional
	public NoticeView viewNotice(Long no) {
		System.out.println("service");
		Optional<NoticeView> noticeView = this.noticeRepository.getNoticeView(no);
		System.out.println("getNoticeView");
		if(noticeView.isPresent()) {
			this.noticeRepository.updateViews(no);
			return noticeView.get();
		}
		else throw new DataNotFoundException("noticeView not found");
	}

	public Boolean insertNotice(NoticeForm noticeForm, String id) {
		System.out.println(id);
		Notice notice = Notice.builder()
				.category("notice")
				.member(this.memberRepository.findByMemberId(id))
				.subject(noticeForm.getSubject())
				.content(noticeForm.getContent())
				.postDate(LocalDateTime.now())
				.count(0)
				.build();
		return this.noticeRepository.save(notice).getMember().getMemberId().equals(id);
	}
	
	public Boolean checkWriter(Long boardNum, String id) {
		Optional<Notice> notice = this.noticeRepository.findById(boardNum);
		return notice.get().getMember().getMemberId().equals(id);
	}
	
	@Transactional
	public int updateNotice(NoticeForm noticeForm, String id) {
		return this.noticeRepository.updateNotice(noticeForm);
		
	}
	
	public long getCount() {
		return this.noticeRepository.count();
	}
}
