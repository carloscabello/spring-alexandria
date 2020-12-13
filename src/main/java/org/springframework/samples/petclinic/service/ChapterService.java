package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Chapter;
import org.springframework.samples.petclinic.model.Story;
import org.springframework.samples.petclinic.repository.ChapterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChapterService {

	private ChapterRepository chapterRepository;	
	
	@Autowired
	public ChapterService(ChapterRepository chapterRepository) {
		this.chapterRepository = chapterRepository;
	}	
	
	@Transactional(readOnly = true)	
	public Collection<Chapter> findChapters(Integer storyId) throws DataAccessException {
		return chapterRepository.findChapterByStoryId(storyId);
	}	

	@Transactional
	public Chapter findChapterById(Integer chapterId) throws DataAccessException {
		Chapter chapter = chapterRepository.findById(chapterId).get();
		return chapter;
	}

	public void saveChapter(@Valid Chapter chapter) throws DataAccessException {
		
		// Creamos el capítulo
		chapterRepository.save(chapter);		
		
	}		
	
	public Collection<Chapter> findChapterByStoryId(int storyId){
		return chapterRepository.findChapterByStoryId(storyId);
	}		

}

