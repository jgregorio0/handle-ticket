package dev.jgregorio.handle.ticket.infrastructure.adapters.out.api;

import com.google.cloud.vision.v1.*;
import com.google.rpc.Status;
import dev.jgregorio.handle.ticket.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GoogleVisionApiTest {

    @Mock
    private ImageAnnotatorClient imageAnnotatorClient;

    private GoogleVisionApi googleVisionApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        googleVisionApi = new GoogleVisionApi();
    }

    @Test
    void givenImageBytes_whenAnnotateImage_thenSuccess() throws IOException {
        // GIVEN image bytes
        byte[] imageBytes = {1, 2, 3};
        // WHEN mock the static create() method
        try (MockedStatic<ImageAnnotatorClient> mockedStatic = mockStatic(ImageAnnotatorClient.class)) {
            mockedStatic.when(ImageAnnotatorClient::create)
                    .thenReturn(imageAnnotatorClient);
            // AND mock response
            String expectedAnnotation = "Test";
            when(imageAnnotatorClient.batchAnnotateImages((List<AnnotateImageRequest>) any()))
                    .thenReturn(buildSuccessResponse(expectedAnnotation));
            // AND annotate image
            List<EntityAnnotation> result = googleVisionApi.annotateImage(imageBytes);
            // THEN result is successful
            assertEquals(expectedAnnotation, result.getFirst().getDescription());
        }
    }

    @Test
    void givenImageBytes_whenAnnotateImageHasError_thenReturnNull() throws IOException {
        // GIVEN image bytes
        byte[] imageBytes = {1, 2, 3};
        // WHEN mock the static create() method
        try (MockedStatic<ImageAnnotatorClient> mockedStatic = mockStatic(ImageAnnotatorClient.class)) {
            mockedStatic.when(ImageAnnotatorClient::create)
                    .thenReturn(imageAnnotatorClient);
            // AND mock success response
            when(imageAnnotatorClient.batchAnnotateImages((List<AnnotateImageRequest>) any()))
                    .thenReturn(buildErrorResponse());
            // AND annotate image
            List<EntityAnnotation> result = googleVisionApi.annotateImage(imageBytes);
            // THEN return null
            assertNull(result);
        }
    }

    @Test
    void givenImageBytes_whenAnnotateImageThrowsException_ThenIOExceptionIsThrown() {
        // GIVEN image bytes
        byte[] imageBytes = {1, 2, 3};
        // WHEN mock the static create() method to throw exception
        try (MockedStatic<ImageAnnotatorClient> mockedStatic = mockStatic(ImageAnnotatorClient.class)) {
            mockedStatic.when(ImageAnnotatorClient::create)
                    .thenThrow(new IOException("Test Exception"));
            // AND annotate image
            Executable executable = () -> googleVisionApi.annotateImage(imageBytes);
            // THEN IOException is thrown
            assertThrows(IOException.class, executable);
        }
    }

    /**
     * Required:
     * 1- Image /ticket-mercadona.jpg
     * 2- Set Google Vision API credentials environment variable:
     * GOOGLE_APPLICATION_CREDENTIALS=/path-to-credentials-file/credentials.json
     */

    //@Test
    void givenImage_whenAnnotateImage_thenReturn() throws IOException, URISyntaxException {
        // get file path resource
        List<EntityAnnotation> entityAnnotations = new GoogleVisionApi()
                .annotateImage(Files.readAllBytes(
                        Paths.get(TestConstants.TICKET_URL.toURI())));
        assertEquals("\"MERCADONA.S.A.\\nAvda. Reino Unido, s/n\\nSEVILLA\\nTELEFONO:\\n954619421\\nNIF:\\nA-46103834\\n08/01/2021\\n21:19 OP: 301497\\nFACTURA SIMPLIFICADA: 3501-011-084867\\nDescripción\\n2 CERVEZA CLASIC\\n0,65\\nPrecio Importe\\nunidad (€)\\n1,30\\n1 CHULETA AGUJA\\n1 CODILLO\\n1 BURGER VAC/CER\\n1 CERV.ESPECIAL\\n1 CERVEZA\\n3.45\\n1,99\\n3,35\\n0.95\\n0.80\\n1 GUISANTE FINO\\n1,15\\n3 6 HUEV. CAMPER\\n1,20 3,60\\n1 ALUBIA PINTA\\n1.90\\n3 MANGO DESHIDRA\\n1,30\\n3,90\\n1 PIPAS AGUASAL\\n1,00\\n1 ALUBIA GRANJA\\n2,27\\n2 TOALL.BEBE ATO\\n1,60\\n3,20\\n1 TACO PALETA\\n4,22\\n1 TACO BACON\\n2,09\\n1 FREGONA SUAVE\\n1 OREJONES ALBAR\\n0,70\\n1,90\\n1 LAVAVAJILLAS U\\n1,80\\n1 LECHE ENTERA 6\\n7,50\\n3 BOLSA PLASTICO\\n0,10\\n0,30\\n2 LANGOSTINO COC\\n3,65\\n7,30\\n1 ESCALOP. SALM\\n9,02\\n1 PECHUGA PAVO\\n4,31\\n1 HUMMUS PIMI\\n1,40\\n1 MANZANA PLATAN\\n0,70\\n1 AJO SECO 250 G\\n1,30\\n1 PIMIENTO FREIR\\n0,446 kg\\n1,79 €/kg\\n0,80\\n1 ALCACHOFA\\n1,068 kg 3,19 €/kg\\n3,41\\n1 PIMIENTO ROJO\\n0,502 kg 1,99 €/kg\\n1,00\\nTOTAL\\n76.61\",",
                entityAnnotations.get(0).getDescription());
    }

    private static BatchAnnotateImagesResponse buildSuccessResponse(String expectedAnnotation) {
        return BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .addTextAnnotations(EntityAnnotation.newBuilder()
                                .setDescription(expectedAnnotation)
                                .build())
                        .build())
                .build();
    }

    private static BatchAnnotateImagesResponse buildErrorResponse() {
        return BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .setError(Status.newBuilder().setCode(404).setMessage("Not Found"))
                        .build())
                .build();
    }

}

