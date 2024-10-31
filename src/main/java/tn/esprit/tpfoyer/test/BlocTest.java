package tn.esprit.tpfoyer.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Chambre;


class BlocTest {

    private Bloc bloc;
    private Foyer foyer;

    @BeforeEach
    void setUp() {
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer A");

        bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc 1");
        bloc.setCapaciteBloc(50L);
        bloc.setFoyer(foyer);
    }

    @Test
    void testBlocAttributes() {
        // Vérification des attributs de base
        assertEquals(1L, bloc.getIdBloc());
        assertEquals("Bloc 1", bloc.getNomBloc());
        assertEquals(50L, bloc.getCapaciteBloc());

        // Vérification de la relation avec le foyer
        assertNotNull(bloc.getFoyer());
        assertEquals("Foyer A", bloc.getFoyer().getNomFoyer());
    }

    @Test
    void testAddChambreToBloc() {
        // Ajout d'une chambre au bloc
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(7);

        bloc.getChambres().add(chambre);

        // Vérification que la chambre a bien été ajoutée
        assertEquals(1, bloc.getChambres().size());
        assertTrue(bloc.getChambres().contains(chambre));
    }
}