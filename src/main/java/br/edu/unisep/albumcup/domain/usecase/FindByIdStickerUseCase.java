package br.edu.unisep.albumcup.domain.usecase;

import br.edu.unisep.albumcup.domain.dto.StickerDto;


public interface FindByIdStickerUseCase {
    StickerDto execute(Integer id);
}