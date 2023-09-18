package br.edu.faj.poo.ministerio.MinisterioService.services;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio.CreateMinisterioDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio.GetMinisterioDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio.UpdateMinisterioDto;
import br.edu.faj.poo.ministerio.MinisterioService.entities.Ministerio;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.MinisterioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinisterioService {
    private final MinisterioRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public MinisterioService(MinisterioRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public GetMinisterioDto create(CreateMinisterioDto createMinisterioDto) throws Exception {
        if (createMinisterioDto.getNome() == null || createMinisterioDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (createMinisterioDto.getNumFuncionarios() == 0) {
            throw new Exception("Field numFuncionarios must to be bigger than zero.");
        }

        if (createMinisterioDto.getVerba() == 0) {
            throw new Exception("Field verba must to be bigger than zero.");
        }

        Ministerio ministerio = new Ministerio();
        ministerio.setNome(createMinisterioDto.getNome());
        ministerio.setNumFuncionarios(createMinisterioDto.getNumFuncionarios());
        ministerio.setVerba(createMinisterioDto.getVerba());

        ministerio = repository.create(ministerio);

        return modelMapper.map(ministerio, GetMinisterioDto.class);
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

    public GetMinisterioDto update(int id, UpdateMinisterioDto updateMinisterioDto) throws Exception {
        if (updateMinisterioDto.getNome() == null || updateMinisterioDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (updateMinisterioDto.getNumFuncionarios() == 0) {
            throw new Exception("Field numFuncionarios must to be bigger than zero.");
        }

        if (updateMinisterioDto.getVerba() == 0) {
            throw new Exception("Field verba must to be bigger than zero.");
        }

        if (repository.getById(id) == null) {
            throw new Exception("Ministerio not found.");
        }

        Ministerio ministerio = new Ministerio();
        ministerio.setId(id);
        ministerio.setNome(updateMinisterioDto.getNome());
        ministerio.setNumFuncionarios(updateMinisterioDto.getNumFuncionarios());
        ministerio.setVerba(updateMinisterioDto.getVerba());

        ministerio = repository.update(ministerio);

        return modelMapper.map(ministerio, GetMinisterioDto.class);
    }

    public boolean delete(int id) throws Exception {
        if (repository.getById(id) == null) {
            throw new Exception("Ministerio not found.");
        }

        repository.delete(id);

        return true;
    }
}
