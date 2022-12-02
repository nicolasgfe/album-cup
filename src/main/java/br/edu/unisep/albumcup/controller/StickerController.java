package br.edu.unisep.albumcup.controller;

import br.edu.unisep.albumcup.data.entity.Sticker;
import br.edu.unisep.albumcup.domain.dto.CreateStickerDto;
import br.edu.unisep.albumcup.domain.dto.StickerDto;
import br.edu.unisep.albumcup.domain.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sticker")
@RequiredArgsConstructor
public class StickerController {

    private final CreateStickerUseCase createStickerUseCase;
    private final FindAllStickersUseCase findAllStickersUseCase;

    private final UpdateStickerUseCase updateStickerUseCase;
    private final FindByIdStickerUseCase findByIdStickerUseCase;

    private final DeleteStickerUseCase deleteStickerUseCase;

    @PostMapping
    public ResponseEntity save(@RequestBody CreateStickerDto stickerData) {
        createStickerUseCase.execute(stickerData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<StickerDto>> findAll() {
        var result = findAllStickersUseCase.execute();

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping(path = {"/{id_sticker}"})
    public ResponseEntity <StickerDto> findById(@PathVariable(value="id_sticker") Integer id){
        var result = findByIdStickerUseCase.execute(id);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        deleteStickerUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id")Integer id, @RequestBody Sticker sticker){
        updateStickerUseCase.execute(id, sticker);
    }

}
