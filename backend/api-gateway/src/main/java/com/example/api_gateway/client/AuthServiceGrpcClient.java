package com.example.api_gateway.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.auth.grpc.AuthServiceGrpc.AuthServiceBlockingStub;
import org.example.auth.grpc.ValidationRequest;
import org.example.auth.grpc.ValidationResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceGrpcClient {

    @GrpcClient("auth-service")
    private AuthServiceBlockingStub authServiceStub;

    public Mono<ValidationResponse> validateToken(String accessToken) {
        return Mono.fromCallable(() -> {
            ValidationRequest request = ValidationRequest.newBuilder()
                .setAccessToken(accessToken)
                .build();

            return authServiceStub.validateToken(request);
        });
    }
}
