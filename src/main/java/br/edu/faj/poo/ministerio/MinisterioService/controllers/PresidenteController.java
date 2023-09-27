package br.edu.faj.poo.ministerio.MinisterioService.controllers;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.presidente.CreatePresidenteDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.presidente.GetPresidenteDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.presidente.UpdatePresidenteDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.response.ResponseDto;
import br.edu.faj.poo.ministerio.MinisterioService.services.PresidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presidentes")
@CrossOrigin(origins = "*")
public class PresidenteController {
    private final PresidenteService service;

    @Autowired
    public PresidenteController(PresidenteService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> create(@RequestBody CreatePresidenteDto body) {
        try {
            return ResponseEntity.ok(
                new ResponseDto("Presidente created successfully", true, service.create(body))
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
            List<GetPresidenteDto> presidenteEntities = service.getAll();
            return ResponseEntity.ok().body(
                new ResponseDto("Presidentes found", true, presidenteEntities)
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
            GetPresidenteDto presidente = service.getById(id);
            return ResponseEntity.ok().body(
                new ResponseDto("Presidente found", true, presidente)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable int id, @RequestBody UpdatePresidenteDto body) {
        try {
            GetPresidenteDto presidente = service.update(id, body);
            return ResponseEntity.ok().body(
                    new ResponseDto("Presidente updated successfully", true, presidente)
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
                new ResponseDto("Presidente deleted successfully", true, null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }
}
