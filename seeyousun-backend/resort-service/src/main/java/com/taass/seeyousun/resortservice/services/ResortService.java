package com.taass.seeyousun.resortservice.services;

import com.taass.seeyousun.resortservice.dto.ResortDTO;
import com.taass.seeyousun.resortservice.dto.PopularResortDTO;
import com.taass.seeyousun.resortservice.dto.ResortFullDTO;
import com.taass.seeyousun.resortservice.exceptions.ResortNotFoundException;
import com.taass.seeyousun.resortservice.mappers.Mapper;
import com.taass.seeyousun.resortservice.messaging.ReviewMessageDTO;
import com.taass.seeyousun.resortservice.model.Resort;
import com.taass.seeyousun.resortservice.repositories.ResortRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResortService {
    private final ResortRepository resortRepository;
    private final Mapper<ResortDTO, Resort> resortMapper;
    private final Mapper<ResortFullDTO, Resort> resortFullMapper;

    public ResortService(
            ResortRepository resortRepository,
            Mapper<ResortDTO, Resort> resortMapper,
            Mapper<ResortFullDTO, Resort> resortFullMapper) {
        this.resortRepository = resortRepository;
        this.resortMapper = resortMapper;
        this.resortFullMapper = resortFullMapper;
    }

    /**
     * Permette di ottenere i Resort più popolari sfruttando la pagination per ottenere un numero limitato di risultati.
     * Nel caso in cui venga lanciata l'eccezione ResortNotFoundException, essa viene propagata al Controller e verra
     * catturata dal GlobalExceptionHandler per indicare all'utente il problema.
     * @param pageNr specifica il numero della pagina richiesta
     * @param pageSize specifica il numero di elementi per pagina
     * @return un oggetto PopularResortDTO che contiene la lista di ResortDTO con le informazioni essenziali dei Resort
     * popolari e inoltre contiene le informazioni riguardanti la pagination per effettuare eventuali nuove richieste
     * per ottenere ulteriori risultati.
     */
    public PopularResortDTO getPopularResorts(int pageNr, int pageSize) {
        Pageable pageable = PageRequest.of(pageNr, pageSize);
        Page<Resort> resortPage = resortRepository.findByOrderByRatingDesc(pageable);
        List<ResortDTO> resortDtoList = resortPage.getContent().stream().map(resortMapper::mapFrom).toList();
        if(resortDtoList.isEmpty()){
            throw new ResortNotFoundException("Popular resorts not found");
        }
        return PopularResortDTO.builder()
                .popularResorts(resortDtoList)
                .pageNr(resortPage.getNumber())
                .pageSize(resortPage.getSize())
                .totalElements(resortPage.getTotalElements())
                .totalPages(resortPage.getTotalPages())
                .last(resortPage.isLast())
                .build();
    }

    /**
     * Permette di ottenere un Resort con tutte le sue informazioni cercandolo attraverso l'id. Nel caso in cui venga
     * lanciata l'eccezione ResortNotFoundException, essa viene propagata al Controller e verra' catturata dal
     * GlobalExceptionHandler per indicare all'utente il problema.
     * @param id è l'id del Resort che si desidera ottenere
     * @return un oggetto ResortFullDTO che contiene tutte le informazioni del Resort
     * @throws ResortNotFoundException se non viene trovato nessun Resort corrispondente
     */
    public ResortFullDTO getResortById(Long id) throws ResortNotFoundException{
        return resortRepository.findById(id)
                .map(resortFullMapper::mapFrom)
                .orElseThrow(() -> new ResortNotFoundException(String.format("Resorts not found with id: '%d'", id)));
    }

    /**
     * Permette di ottenere un Resort con le sole informazioni di base cercandolo attraverso il nome. Nel caso in cui
     * venga lanciata l'eccezione ResortNotFoundException, essa viene propagata al Controller e verra catturata dal
     * GlobalExceptionHandler per indicare all'utente il problema.
     * @param name è il nome dei Resorts che si desidera ottenere
     * @return una lista di ResortDTO che contiene le informazioni essenziali dei Resort richiesti
     * @throws ResortNotFoundException se non viene trovato nessun Resort corrispondente
     */
    public List<ResortDTO> getResortsByName(String name) throws ResortNotFoundException{
        List<Resort> resortList = resortRepository.findByNameContainingIgnoreCase(name);
        if(resortList.isEmpty()){
            throw new ResortNotFoundException(String.format("Resorts not found with name: '%s'", name));
        }
        return resortList
                .stream()
                .map(resortMapper::mapFrom)
                .toList();
    }

    /**
     * Permette di ottenere un Resort con le sole informazioni di base cercandolo attraverso la location. Nel caso in cui
     * venga lanciata l'eccezione ResortNotFoundException, essa viene propagata al Controller e verra catturata dal
     * GlobalExceptionHandler per indicare all'utente il problema.
     * @param location è la location dei Resorts che si desidera ottenere
     * @return una lista di ResortDTO che contiene le informazioni essenziali dei Resort richiesti
     * @throws ResortNotFoundException se non viene trovato nessun Resort corrispondente
     */
    public List<ResortDTO> getResortsByLocation(String location) throws ResortNotFoundException{
        List<Resort> resortList = resortRepository.findByLocationContainingIgnoreCase(location);
        if(resortList.isEmpty()){
            throw new ResortNotFoundException(String.format("Resorts not found with location: '%s'", location));
        }
        return resortList
                .stream()
                .map(resortMapper::mapFrom)
                .toList();
    }

    /**
     * Permette di ottenere un Resort con le sole informazioni di base cercandolo attraverso la location e i servizi che
     * offre. Nel caso in cui venga lanciata l'eccezione ResortNotFoundException, essa viene propagata al Controller e
     * verra' catturata dal GlobalExceptionHandler per indicare all'utente il problema.
     * @param location è la location dei Resorts che si desidera ottenere
     * @param services è la lista che contiene le stringhe dei nomi dei Servizi richiesti
     * @return una lista di ResortDTO che contiene le informazioni essenziali dei Resort richiesti
     * @throws ResortNotFoundException se non viene trovato nessun Resort corrispondente
     */
    public List<ResortDTO> getResortsByLocationAndServices(String location, List<String> services) throws ResortNotFoundException {
        List<Resort> resortList = resortRepository.findByLocationContainingAndServicesIn(location, services, services.size());
        if(resortList.isEmpty()){
            throw new ResortNotFoundException(String.format("Resorts not found with location '%s' and services: %s", location, String.join(", ", services)));
        }
        return resortList
                .stream()
                .map(resortMapper::mapFrom)
                .toList();
    }

    public void updateResortRating(ReviewMessageDTO reviewMessageDTO) {
        Long resortId = reviewMessageDTO.getResortId();
        Resort r = resortRepository.findById(resortId)
                .orElseThrow(()-> new ResortNotFoundException(String.format("Resorts not found with id: '%d'", resortId)));
        r.setRating(reviewMessageDTO.getAverageRating());
        resortRepository.save(r);
    }
}
