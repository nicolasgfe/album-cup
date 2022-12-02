package br.edu.unisep.albumcup.domain.usecase.impl;

import br.edu.unisep.albumcup.data.entity.Sticker;
import br.edu.unisep.albumcup.data.repository.StickerRepository;
import br.edu.unisep.albumcup.domain.usecase.UpdateStickerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class UpdateStickerUseCaseImpl implements UpdateStickerUseCase {
    private final StickerRepository repository;

    public void execute(Integer id, Sticker sticker) {
        if (repository.findById(id).isPresent()) {
            Sticker dto = repository.findById(id).get();

            if (dto.getPlayer() != null) {
                dto.setPlayer(sticker.getPlayer());

            }
            if (dto.getCountry() != null) {
                dto.setCountry(sticker.getCountry());
            }

            if (dto.getWeight() != null) {
                dto.setWeight(sticker.getWeight());
            }
            if (dto.getBirthday() != null) {
                dto.setBirthday(sticker.getBirthday());
            }
            if (dto.isLegendary() != sticker.isLegendary()) {
                dto.setLegendary(sticker.isLegendary());
            }
            repository.save(dto);
        } else {
            throw new HttpClientErrorException((HttpStatus.NOT_FOUND));
        }
    }
}
