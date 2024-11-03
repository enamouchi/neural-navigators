package tn.esprit.tpfoyer.test
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EtudiantRestControllerTest {

    @Mock
    private EtudiantService etudiantService;

    @InjectMocks
    private EtudiantRestController etudiantRestController;

    private Etudiant etudiant;
    private Long etudiantId;

    @BeforeEach
    void setUp() {
        etudiantId = 1L;
        etudiant = new Etudiant(etudiantId, "John Doe", "john.doe@example.com");
    }

    @Test
    void testGetAllEtudiants() {
        // Arrange
        List<Etudiant> etudiants = Arrays.asList(etudiant);
        when(etudiantService.getAllEtudiants()).thenReturn(etudiants);

        // Act
        ResponseEntity<List<Etudiant>> response = etudiantRestController.getAllEtudiants();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(etudiants, response.getBody());
        verify(etudiantService, times(1)).getAllEtudiants();
    }

    @Test
    void testGetEtudiantById() {
        // Arrange
        when(etudiantService.getEtudiantById(etudiantId)).thenReturn(Optional.of(etudiant));

        // Act
        ResponseEntity<Etudiant> response = etudiantRestController.getEtudiantById(etudiantId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(etudiant, response.getBody());
        verify(etudiantService, times(1)).getEtudiantById(etudiantId);
    }

    @Test
    void testCreateEtudiant() {
        // Arrange
        when(etudiantService.saveEtudiant(etudiant)).thenReturn(etudiant);

        // Act
        ResponseEntity<Etudiant> response = etudiantRestController.createEtudiant(etudiant);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(etudiant, response.getBody());
        verify(etudiantService, times(1)).saveEtudiant(etudiant);
    }

    @Test
    void testUpdateEtudiant() {
        // Arrange
        Etudiant updatedEtudiant = new Etudiant(etudiantId, "Jane Doe", "jane.doe@example.com");
        when(etudiantService.updateEtudiant(etudiantId, updatedEtudiant)).thenReturn(Optional.of(updatedEtudiant));

        // Act
        ResponseEntity<Etudiant> response = etudiantRestController.updateEtudiant(etudiantId, updatedEtudiant);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedEtudiant, response.getBody());
        verify(etudiantService, times(1)).updateEtudiant(etudiantId, updatedEtudiant);
    }

    @Test
    void testDeleteEtudiant() {
        // Arrange
        doNothing().when(etudiantService).deleteEtudiant(etudiantId);

        // Act
        ResponseEntity<Void> response = etudiantRestController.deleteEtudiant(etudiantId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(etudiantService, times(1)).deleteEtudiant(etudiantId);
    }

    
}
