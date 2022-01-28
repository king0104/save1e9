package com.example.save1e9.service;

import com.example.save1e9.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class Producers {
    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(String topicName, User user) {

        Message<User> message = MessageBuilder.
                withPayload(user)
                .build();


        /**
         * send() API : ListenableFuture 객체 반환
         * ListenableFuture
         * - new ListenableFutureCallback
         *      producer의 메세지 송신 여부 & 현재 보낸 파티션의 offset 확인 가능
         *
         */
        ListenableFuture<SendResult<String, User>> future =
                kafkaTemplate.send(topicName, user);

        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {

            @Override
            public void onSuccess(SendResult<String, User> result) {
                System.out.println("Sent message=[" + result.getProducerRecord().value().toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
