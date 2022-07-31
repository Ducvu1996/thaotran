package com.fpt.convert;

import com.fpt.dto.ProviderDto;
import com.fpt.entities.Provider;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProviderConverter {


    public ProviderDto convertToDTO(Provider provider) {
        ProviderDto providerDto = new ProviderDto();
        providerDto.setIdProvider(provider.getIdProvider());
        providerDto.setNameProvider(provider.getNameProvider());
        providerDto.setTypeProvider(provider.getTypeProvider());

        return providerDto;
    }

    public Provider convertToEntity(ProviderDto providerDto) {
        Provider provider = new Provider();
        provider.setIdProvider(providerDto.getIdProvider());
        provider.setNameProvider(providerDto.getNameProvider());
        provider.setTypeProvider(providerDto.getTypeProvider());
        provider.setCreateAt(Instant.now());
        provider.setUpdateAt(Instant.now());
        return provider;
    }
}
