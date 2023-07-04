package kea.alog.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${custom.storage.endpoint}")
    private String endpoint;

    @Bean
    public AmazonS3Client amazonS3Client() {
    BasicAWSCredentials awsCredentials= new BasicAWSCredentials(accessKey,secretKey);
    EndpointConfiguration endpointConfiguration = new EndpointConfiguration( endpoint,region);
    return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                                                .withRegion(region)
                                                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                                                .withPathStyleAccessEnabled(true)
                                                .withEndpointConfiguration(endpointConfiguration)
                                                .build();
    }
}