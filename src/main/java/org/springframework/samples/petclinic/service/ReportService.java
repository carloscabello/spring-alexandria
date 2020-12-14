package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Report;
import org.springframework.samples.petclinic.model.Story;
import org.springframework.samples.petclinic.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {

	private ReportRepository reportRepository;	
	
	@Autowired
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}	
	

	public void saveReport(@Valid Report report) throws DataAccessException {
		
		// Creamos el capítulo
		reportRepository.save(report);		
		
	}		
	
	

}

