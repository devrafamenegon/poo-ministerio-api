package br.edu.faj.poo.ministerio.MinisterioService.controllers;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio.CreateMinisterioDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio.GetMinisterioDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio.UpdateMinisterioDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.response.ResponseDto;
import br.edu.faj.poo.ministerio.MinisterioService.services.MinisterioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ministerios")
public class MinisterioController {
    private final MinisterioService service;

    @Autowired
    public MinisterioController(MinisterioService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> create(@RequestBody CreateMinisterioDto body) {
        try {
            return ResponseEntity.ok(
                new ResponseDto("Ministerio created successfully", true, service.create(body))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getAll() {
        try {
            List<GetMinisterioDto> ministerioEntities = service.getAll();
            return ResponseEntity.ok().body(
                new ResponseDto("Ministerios found", true, ministerioEntities)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable int id) {
        try {
            GetMinisterioDto ministerio = service.getById(id);
            return ResponseEntity.ok().body(
                new ResponseDto("Ministerio found", true, ministerio)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable int id, @RequestBody UpdateMinisterioDto body) {
        try {
            GetMinisterioDto ministerio = service.update(id, body);
            return ResponseEntity.ok().body(
                    new ResponseDto("Ministerio updated successfully", true, ministerio)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().body(
                new ResponseDto("Ministerio deleted successfully", true, null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }
}
