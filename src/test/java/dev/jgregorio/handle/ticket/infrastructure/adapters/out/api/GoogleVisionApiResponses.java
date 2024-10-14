package dev.jgregorio.handle.ticket.infrastructure.adapters.out.api;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.rpc.Status;

public class GoogleVisionApiResponses {

    public static BatchAnnotateImagesResponse buildSuccessResponse(String expectedAnnotation) {
        return BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .addTextAnnotations(
                                buildSuccessAnnotation(expectedAnnotation))
                        .build())
                .build();
    }

    public static EntityAnnotation buildSuccessAnnotation(String expectedAnnotation) {
        return EntityAnnotation.newBuilder()
                .setDescription(expectedAnnotation)
                .build();
    }

    public static BatchAnnotateImagesResponse buildErrorResponse() {
        return BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .setError(Status.newBuilder().setCode(404).setMessage("Not Found"))
                        .build())
                .build();
    }



}

