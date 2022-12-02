package br.edu.unisep.albumcup.domain.usecase.impl;

import br.edu.unisep.albumcup.data.repository.StickerRepository;
import br.edu.unisep.albumcup.domain.builder.StickerBuilder;
import br.edu.unisep.albumcup.domain.dto.StickerDto;
import br.edu.unisep.albumcup.domain.usecase.FindByIdStickerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class FindByIdStickerUseCaseImpl implements FindByIdStickerUseCase {
    private final StickerRepository repository;
    private final StickerBuilder stickerBuilder;

    @Override
    public StickerDto execute(Integer id){
        var result = repository.findById(id);
        if (result.isEmpty()){
            return null;
        }
        return stickerBuilder.build(result.get());
    }

}
