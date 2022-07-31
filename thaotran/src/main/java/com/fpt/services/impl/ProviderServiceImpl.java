package com.fpt.services.impl;

import com.fpt.convert.ProviderConverter;
import com.fpt.dto.ProviderDto;
import com.fpt.entities.Provider;
import com.fpt.repository.ProviderRepository;
import com.fpt.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProviderConverter providerConverter;

    @Override
    public List<ProviderDto> getAll() {
        List<Provider> providers = providerRepository.findAll();
        List<ProviderDto> providerDtoList = new ArrayList<>();
        for (Provider provider :
                providers) {
            providerDtoList.add(providerConverter.convertToDTO(provider));
        }
        return providerDtoList;

    }

    @Override
    public Page<Provider> findByNameProviderLike(Integer pageNumber, Integer pageSize, String keyword) {
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (keyword == null) keyword = "";
        return providerRepository.findByNameProviderLike(keyword, pageable);
    }

    @Override
    public ProviderDto save(ProviderDto providerDto) {
        Provider provider = providerConverter.convertToEntity(providerDto);
        Provider providerSave = providerRepository.save(provider);

        return providerConverter.convertToDTO(providerSave);

    }

    @Override
    public ProviderDto update(Provider provider, String code) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            providerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProviderDto findById(Integer id) {
        Provider provider = providerRepository.getById(id);
        return providerConverter.convertToDTO(provider);
    }
}
