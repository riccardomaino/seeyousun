package com.taass.seeyousun.resortservice.services;

import com.taass.seeyousun.resortservice.client.EventClient;
import com.taass.seeyousun.resortservice.client.ReviewClient;
import com.taass.seeyousun.resortservice.dto.*;
import com.taass.seeyousun.resortservice.exceptions.PriceNotFoundException;
import com.taass.seeyousun.resortservice.exceptions.ResortNotFoundException;
import com.taass.seeyousun.resortservice.exceptions.ServiceNotReachableException;
import com.taass.seeyousun.resortservice.mappers.impl.PriceListDTOmapper;
import com.taass.seeyousun.resortservice.mappers.impl.ResortFullMapper;
import com.taass.seeyousun.resortservice.mappers.impl.ResortMapper;
import com.taass.seeyousun.resortservice.messaging.ReviewMessageDTO;
import com.taass.seeyousun.resortservice.model.PricePeriod;
import com.taass.seeyousun.resortservice.model.Resort;
import com.taass.seeyousun.resortservice.repositories.ResortRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResortService {
    private final ResortRepository resortRepository;
    private final ResortMapper resortMapper;
    private final ResortFullMapper resortFullMapper;
    private final ReviewClient reviewClient;
    private final EventClient eventClient;
    private final PriceListDTOmapper priceMapper;

    public ResortService(
            ResortRepository resortRepository,
            ResortMapper resortMapper,
            ResortFullMapper resortFullMapper,
            ReviewClient reviewClient,
            EventClient eventClient,
            PriceListDTOmapper priceMapper) {
        this.resortRepository = resortRepository;
        this.resortMapper = resortMapper;
        this.resortFullMapper = resortFullMapper;
        this.reviewClient = reviewClient;
        this.eventClient = eventClient;
        this.priceMapper = priceMapper;
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
            throw new ResortNotFoundException("Nessun resorts popolare trovato");
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
     * @param resortId è l'id del Resort che si desidera ottenere
     * @return un oggetto ResortFullDTO che contiene tutte le informazioni del Resort
     * @throws ResortNotFoundException se non viene trovato nessun Resort corrispondente
     */
    public ResortFullDTO getResortById(Long resortId) throws ResortNotFoundException{
        // Ottiene il Resort dal Database attraverso il repository "ResortRepository"
        ResortFullDTO resortFullDTO = resortRepository.findById(resortId)
                .map(resortFullMapper::mapFrom)
                .orElseThrow(() -> new ResortNotFoundException(String.format("Nessun resorts trovato con id: '%d'", resortId)));

        // Effettuiamo chiamata REST al event-service sincrona per ottenere gli eventi del resort
        ResponseEntity<ApiResponseDTO<List<EventDTO>>> responseEvent = eventClient.getEventForResort(resortId);
        if(responseEvent.getStatusCode() != HttpStatus.OK) {
            throw new ServiceNotReachableException("L'Event Service non è raggiungibile");
        }
        List<EventDTO> eventsDTO = Objects.requireNonNull(responseEvent.getBody()).getData();
        resortFullDTO.setEvents(eventsDTO);

        // Effettuiamo chiamata REST al review-service sincrona per ottenere le reviews del resort
        ResponseEntity<ApiResponseDTO<List<ReviewDTO>>> responseReview = reviewClient.getReviewsForResort(resortId);
        if(responseReview.getStatusCode() != HttpStatus.OK) {
            throw new ServiceNotReachableException("Il Review Service non è raggiungibile");
        }
        List<ReviewDTO> reviewDTO = Objects.requireNonNull(responseReview.getBody()).getData();
        resortFullDTO.setReviews(reviewDTO);
        return resortFullDTO;
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
            throw new ResortNotFoundException(String.format("Nessun resorts trovato con nome: '%s'", name));
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
            throw new ResortNotFoundException(String.format("Nessun resorts trovato con location: '%s'", location));
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
            throw new ResortNotFoundException(String.format("Nessun resorts trovato con location '%s' e con i seguenti servizi: %s", location, String.join(", ", services)));
        }
        return resortList
                .stream()
                .map(resortMapper::mapFrom)
                .toList();
    }

    public DimensionDTO getResortDimension(Long resortId) {
        Resort r = resortRepository.findById(resortId)
                .orElseThrow(()-> new ResortNotFoundException(String.format("Nessun resorts trovato con id: '%d'", resortId)));
        return DimensionDTO.builder()
                .totalUmbrellaLine(r.getTotalUmbrellaLine())
                .totalUmbrellaColumn(r.getTotalUmbrellaColumn())
                .build();
    }

    public PriceListDTO getResortPricing(Long resortId, LocalDate date) {
        PricePeriod p = resortRepository.findPricePeriod(resortId, date)
                .orElseThrow(()-> new PriceNotFoundException(String.format("Nessun listino prezzo trovato per il resort %d in data %s", resortId,date)));
        return priceMapper.mapFrom(p);
    }

    public void updateResortRating(ReviewMessageDTO reviewMessageDTO) {
        Long resortId = reviewMessageDTO.getResortId();
        // Resort r = resortRepository.findById(resortId).orElseThrow(()-> new ResortNotFoundException(String.format("Nessun resorts trovato con id: '%d'", resortId)));
        Optional<Resort> opt = resortRepository.findById(resortId);
        if(opt.isPresent()){
            Resort r = opt.get();
            r.setRating(reviewMessageDTO.getAverageRating());
            resortRepository.save(r);
        }
    }

    public Boolean checkResortById(Long resortId){
        Optional<Resort> opt = resortRepository.findById(resortId);
        return opt.isPresent();
    }

    public ReservationStateDTO getReservationInformation(Long resortId, LocalDate date) {
        // Otteniamo le dimensioni (linee x colonne) del resort
        DimensionDTO dimensionDTO = this.getResortDimension(resortId);

        // Otteniamo il listino dei prezzi del resort
        PriceListDTO priceListDTO = this.getResortPricing(resortId,date);

        // Effettuiamo una chiamata REST sincrona al resort-reservation-service per ottenere le informazioni sugli ombrelloni occupati
        List<UmbrellaDTO> reservedUmbrella = Objects.requireNonNull(resortReservationClient
                .getReservedUmbrellaForResortAndDate(resortId, date.toString())
                .getBody()).getData();

        // Costruiamo un oggetto "ReservationStateDTO" con le informazioni della prenotazione
        return ReservationStateDTO.builder()
                .dimension(dimensionDTO)
                .priceList(priceListDTO)
                .reservedUmbrella(reservedUmbrella)
                .build();
    }
}
