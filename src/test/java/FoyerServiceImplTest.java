package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllFoyers() {
        // Create a sample list of foyers
        List<Foyer> foyers = Arrays.asList(
                new Foyer(1L, "Foyer1", 100, null, null),
                new Foyer(2L, "Foyer2", 200, null, null)
        );

        // Define repository behavior
        when(foyerRepository.findAll()).thenReturn(foyers);

        // Call the method to test
        List<Foyer> result = foyerService.retrieveAllFoyers();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Foyer1", result.get(0).getNomFoyer());
        assertEquals(100, result.get(0).getCapaciteFoyer());
        
        // Verify that findAll was called once
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveFoyer() {
        // Create a sample foyer
        Foyer foyer = new Foyer(1L, "Foyer1", 100, null, null);
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // Call the method
        Foyer result = foyerService.retrieveFoyer(1L);

        // Assertions
        assertNotNull(result);
        assertEquals("Foyer1", result.getNomFoyer());
        assertEquals(100, result.getCapaciteFoyer());

        // Verify that findById was called once
        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    void testAddFoyer() {
        Foyer foyer = new Foyer(null, "New Foyer", 150, null, null);
        when(foyerRepository.save(foyer)).thenReturn(new Foyer(3L, "New Foyer", 150, null, null));

        Foyer result = foyerService.addFoyer(foyer);

        assertNotNull(result);
        assertEquals("New Foyer", result.getNomFoyer());
        assertEquals(150, result.getCapaciteFoyer());
        assertEquals(3L, result.getIdFoyer());

        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testModifyFoyer() {
        Foyer foyer = new Foyer(1L, "Updated Foyer", 180, null, null);
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.modifyFoyer(foyer);

        assertNotNull(result);
        assertEquals("Updated Foyer", result.getNomFoyer());
        assertEquals(180, result.getCapaciteFoyer());

        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testRemoveFoyer() {
        Long foyerId = 1L;

        // Call the method
        foyerService.removeFoyer(foyerId);

        // Verify that deleteById was called once with the correct ID
        verify(foyerRepository, times(1)).deleteById(foyerId);
    }
}
