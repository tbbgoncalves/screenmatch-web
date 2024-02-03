package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> obterTodasAsSeries() {
        return converteSerieDto(serieRepository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteSerieDto(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteSerieDto(serieRepository.encontrarEpisodiosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serieBuscada = serieRepository.findById(id);

        if (serieBuscada.isPresent()) {
            Serie s = serieBuscada.get();

            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
                    s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        } else {
            return null;
        }
    }

    public List<EpisodioDTO> obterTodosEpisodios(Long id) {
        Optional<Serie> serieBuscada = serieRepository.findById(id);

        if (serieBuscada.isPresent()) {
            Serie serie = serieBuscada.get();

            return converteEpisodioDto(serie.getEpisodios());
        } else {
            return null;
        }
    }

    public List<EpisodioDTO> obterEpisodiosPorTemporada(Long id, Long numero) {
        return converteEpisodioDto(serieRepository.obterEpisodiosPorTemporada(id, numero));
    }

    public List<SerieDTO> obterSeriesPorCategoria(String genero) {
        Categoria categoria = Categoria.fromPortugues(genero);

        return converteSerieDto(serieRepository.findByGenero(categoria));
    }

    private List<SerieDTO> converteSerieDto(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
                        s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }

    private List<EpisodioDTO> converteEpisodioDto(List<Episodio> episodios) {
        return episodios.stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumero(), e.getTitulo()))
                .collect(Collectors.toList());
    }
}

