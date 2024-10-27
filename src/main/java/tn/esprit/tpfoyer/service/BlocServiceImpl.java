package tn.esprit.tpfoyer.service;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityNotFoundException; // Ensure this import is added
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j  // Simple Logging Facade For Java
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository; // Change to private final

    @Scheduled(fixedRate = 30000) // milliseconds
    //@Scheduled(cron="0/15 * * * * *")
    public List<Bloc> retrieveAllBlocs() {
        log.debug("Début de la récupération de tous les blocs");
        List<Bloc> listB = blocRepository.findAll();
        log.info("taille totale : {}", listB.size());
        for (Bloc b : listB) {
            log.info("Bloc : {}", b);
        }
        log.debug("Fin de la récupération de tous les blocs");
        return listB;
    }

    @Transactional
    public List<Bloc> retrieveBlocsSelonCapacite(long c) {
        log.debug("Récupération des blocs selon la capacité : {}", c);
        List<Bloc> listB = blocRepository.findAll();
        List<Bloc> listBselonC = new ArrayList<>();

        for (Bloc b : listB) {
            if (b.getCapaciteBloc() >= c) {
                listBselonC.add(b);
                log.debug("Bloc correspondant trouvé : {}", b);
            }
        }
        log.info("Nombre de blocs trouvés avec une capacité supérieure ou égale à {} : {}", c, listBselonC.size());
        return listBselonC;
    }

    @Transactional
    public Bloc retrieveBloc(Long blocId) {
        log.debug("Recuperation du bloc avec ID : {}", blocId);
        return blocRepository.findById(blocId).map(bloc -> {
            log.info("Bloc trouvé : {}", bloc);
            return bloc;
        }).orElseThrow(() -> {
            log.error("Bloc non trouvé avec ID : {}", blocId);
            return new EntityNotFoundException("Bloc not found with id " + blocId);
        });
    }

    public Bloc addBloc(Bloc c) {
        return blocRepository.save(c);
    }

    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    public List<Bloc> trouverBlocsSansFoyer() {
        return blocRepository.findAllByFoyerIsNull();
    }

    public List<Bloc> trouverBlocsParNomEtCap(String nb, long c) {
        return blocRepository.findAllByNomBlocAndCapaciteBloc(nb, c);
    }
}
