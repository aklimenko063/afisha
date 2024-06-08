package org.javaacademy.afisha.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.dto.ReportDtoRs;
import org.javaacademy.afisha.mapper.ReportMapper;
import org.javaacademy.afisha.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public List<ReportDtoRs> getReport() {
        return reportRepository.getData()
                .stream()
                .map(ReportMapper::covertReportEntityToReportDtoRs)
                .toList();
    }
}
