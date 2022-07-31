package com.fpt.services;


import com.fpt.dto.ProviderDto;
import com.fpt.entities.Provider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProviderService {
    List<ProviderDto> getAll();

    Page<Provider> findByNameProviderLike(Integer pageNumber, Integer pageSize, String keyword);

    ProviderDto save(ProviderDto provider);

    ProviderDto update(Provider provider, String code);

    boolean delete(Integer id);

    ProviderDto findById(Integer id);
}
