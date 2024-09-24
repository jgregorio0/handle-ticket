package dev.jgregorio.handle.ticket.infrastructure.api;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.util.List;

public class GoogleVisionApi {

    EntityAnnotation annotateImage(byte[] bytes) throws IOException {
        // Try-with-resources to ensure the client is closed properly
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            // Perform the batchAnnotateImages request
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(
                    // Create a list of requests
                    List.of(
                            // Create image
                            createAnnotateImageRequest(bytes)));
            // Process the response
            AnnotateImageResponse annotateImageResponse = response.getResponsesList().get(0);
            if (annotateImageResponse.hasError()) {
                System.out.println("Error: " + annotateImageResponse.getError().getMessage());//TODO JG add log
                return null;
            }
            // return annotated image
            return annotateImageResponse.getTextAnnotations(0);
        }
    }

    private static AnnotateImageRequest createAnnotateImageRequest(byte[] bytes) {
        // Create an AnnotateImageRequest object
        return AnnotateImageRequest.newBuilder()
                .setImage(
                        Image.newBuilder()
                                .setContent(
                                        ByteString.copyFrom(bytes))
                                .build())
                .addFeatures(
                        // Create a Feature object
                        Feature.newBuilder()
                                .setType(Feature.Type.TEXT_DETECTION)
                                .build()
                )
                .build();
    }

}
