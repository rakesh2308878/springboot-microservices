package com.rakesh.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import com.rakesh.organizationservice.dto.OrganizationDto;
import com.rakesh.organizationservice.entity.Organization;
import com.rakesh.organizationservice.mapper.OrganizationMapper;
import com.rakesh.organizationservice.repository.OrganizationRepository;
import com.rakesh.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        // convert OrganizationDto into Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
