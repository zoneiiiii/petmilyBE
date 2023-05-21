package com.petmily.backend.community.missing.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class MissingBoardService {
    @Autowired
    private MissingBoardRepository missingBoardRepository;

    @Transactional //(readOnly = true)
    public List<MissingBoardList> getMissingBoardList() {
        return missingBoardRepository.getMissingBoards();
    }

    @Transactional //(readOnly = true)
    public MissingBoardDetail getMissingBoard(Long boardNum) {
    	MissingBoardDetail missingBoard = missingBoardRepository.findMissingBoardDetail(boardNum);
        missingBoardRepository.updateBoardCount(boardNum);
        return missingBoard;
    }

    @Transactional //(readOnly = true)
    public MissingBoard getMissingBoard(String boardId) {
        return missingBoardRepository.findByBoardId(boardId);
    }

    @Transactional
    public void createMissingBoard(MissingBoard missingBoard) {
        missingBoardRepository.save(missingBoard);
    }

    @Transactional
    public void updateMissingBoard(Long boardNum, MissingBoard missingBoard) {
        MissingBoard findMissingBoard = missingBoardRepository.findByBoardNumAndMemberNum(boardNum, missingBoard.getMemberNum());
        findMissingBoard.setBoardSubject(missingBoard.getBoardSubject());
        findMissingBoard.setBoardContent(missingBoard.getBoardContent());
        findMissingBoard.setImgThumbnail(missingBoard.getImgThumbnail());
    }

    @Transactional
    public void deleteMissingBoard(Long boardNum) {
        missingBoardRepository.deleteByBoardNum(boardNum);
    }

}