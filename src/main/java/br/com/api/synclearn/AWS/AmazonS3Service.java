package br.com.api.synclearn.AWS;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
//import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.io.InputStream;



@ApplicationScoped
public class AmazonS3Service {

    @Inject
    @ConfigProperty(name = "bucket.name")
    private String bucketName;

    //private final S3Client s3Client;

   /* public AmazonS3Service() {
        this.s3Client = S3Client.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.US_EAST_1)
                .build();
    }


    public String uploadArquivoS3(String key, InputStream inputStream) {
        try {
            long tamanhoArquivo = inputStream.available();

            PutObjectRequest request = PutObjectRequest
                    .builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            PutObjectResponse response = s3Client.putObject(request, RequestBody.fromInputStream(inputStream, tamanhoArquivo));


            String s3url = "https://" + bucketName + ".s3.amazonaws.com/" + key;

            return s3url;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload do arquivo " + key, e);
        }
    }

    */

}
