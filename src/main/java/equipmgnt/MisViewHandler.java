package equipmgnt;

import equipmgnt.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MisViewHandler {


    @Autowired
    private MisRepository misRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                Mis mis = new Mis();
                // view 객체에 이벤트의 Value 를 set 함
                mis.setId(ordered.getId());
                mis.setEquipmentId(ordered.getEquipmentId());
                mis.setQty(ordered.getQty());
                mis.setStatus(ordered.getStatus());
                // view 레파지 토리에 save
                misRepository.save(mis);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void when_then_UPDATE_(@Payload CancelRequested cancelRequested) {
        try {
            if (cancelRequested.isMe()) {
                // view 객체 조회
                Optional<Mis> misOptional = misRepository.findById(cancelRequested.getOrderId());
                if( misOptional.isPresent()) {
                    Mis mis = misOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mis.setStatus(cancelRequested.getStatus());
                    // view 레파지 토리에 save
                    misRepository.save(mis);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenApprovalObtained_then_UPDATE_2(@Payload ApprovalObtained approvalObtained) {
        try {
            if (approvalObtained.isMe()) {
                // view 객체 조회
                Optional<Mis> misOptional = misRepository.findById(approvalObtained.getOrderId());
                if( misOptional.isPresent()) {
                    Mis mis = misOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mis.setStatus(approvalObtained.getStatus());
                    // view 레파지 토리에 save
                    misRepository.save(mis);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}