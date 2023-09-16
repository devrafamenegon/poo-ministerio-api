package br.edu.faj.poo.ministerio.MinisterioService.services;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio.GetMinisterioDto;
import br.edu.faj.poo.ministerio.MinisterioService.entities.Ministerio;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.MinisterioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

public class MinisterioService {
    private final MinisterioRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public MinisterioService(MinisterioRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<GetMinisterioDto> getAll() throws Exception {
        List<Ministerio> ministerios = repository.getAll();

        if (ministerios.isEmpty()) {
            throw new Exception("Ministerios not found");
        }

        return ministerios.stream().map(
            ministerio -> modelMapper.map(ministerio, GetMinisterioDto.class)
        ).collect(Collectors.toList());
    }

    public GetMinisterioDto getById(int id) throws Exception {
        Ministerio ministerio = repository.getById(id);
        if (ministerio == null) {
            throw new Exception("Ministerio not found");
        }
        return modelMapper.map(ministerio, GetMinisterioDto.class);
    }

    private void create(Ministerio m) throws Exception {
        if (m.getNome() == null || m.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (m.getNumFuncionarios() == 0) {
            throw new Exception("Field numFuncionarios must to be bigger than zero.");
        }

        if (m.getVerba() == 0) {
            throw new Exception("Field verba must to be bigger than zero.");
        }
    }
}
