package com.mikro.gsmikro;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import java.util.List;

public class UserVerification {

    public void MachineVerify(String source, String target) {
        // Adjust similarity strength here
        Float similarityThreshold = 80F;
        String bucket = "mikros-verify-upload";

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-east-1").build();

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName(source).withBucket(bucket))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName(target).withBucket(bucket))).withSimilarityThreshold(similarityThreshold);

        try {
            // Call Operation
            CompareFacesResult compareFacesResult=rekognitionClient.compareFaces(compareFacesRequest);

            // Result section
            List <CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();

            if (!faceDetails.isEmpty()) {
                for (CompareFacesMatch match: faceDetails){
                    ComparedFace face= match.getFace();
                    BoundingBox position = face.getBoundingBox();
                    System.out.println("Face at " + position.getLeft().toString()
                            + " " + position.getTop()
                            + " matches with " + match.getSimilarity().toString()
                            + "% confidence.");

                    List<ComparedFace> uncompared = compareFacesResult.getUnmatchedFaces();

                    System.out.println("There was " + uncompared.size()
                            + " face(s) that did not match");

                }
            } else {
                System.out.println("An error occured");
            }
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
    }
}
