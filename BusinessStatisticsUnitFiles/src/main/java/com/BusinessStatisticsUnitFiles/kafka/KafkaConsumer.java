package com.BusinessStatisticsUnitFiles.kafka;


import com.BusinessStatisticsUnitFiles.dataAccessObject.IBusinessEntityRepository;
import com.BusinessStatisticsUnitFiles.dataTransferObject.BusinessEntity;
import com.BusinessStatisticsUnitFiles.models.BusinessEntityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
/*

@Component
public class KafkaConsumer {

    @Autowired
    IBusinessEntityRepository businessEntityRepository;

    private static final Logger LOG = LoggerFactory.getLogger(BusinessEntity.class);


    @KafkaListener(topics = "${statistics.kafka.topic.create.entity}", groupId = "statistics-BusinessEntityManagementSystem-group")
    @KafkaHandler
    @Transactional
    public void receiveCreateEntity(@Payload BusinessEntity data,
                                    @Headers MessageHeaders headers) {
        LOG.info("received from SGE data");
        //LOG.info("received from business-entity-management-system data='{}'", data);
		*/
/*headers.keySet().forEach(key -> {
					LOG.info("{}: {}", key, headers.get(key));
				});*//*


        BusinessEntityModel recivedEntity = new BusinessEntityModel();

      //  businessEntityRepository.save(BindingUniverseDefaultSchemaToUniverse.Binding(recivedEntity, data));
    }

	*/
/*@KafkaListener(topics = "${statistics.kafka.topic.update.entity}", groupId = "statistics-BusinessEntityManagementSystem-group")
	@KafkaHandler
	public void receiveUpdateEntity(@Payload BusinessEntity data,
	                                @Headers MessageHeaders headers) {
		LOG.info("received from BusinessEntityManagementSystem data='{}'", data);

		headers.keySet().forEach(key -> {
			LOG.info("{}: {}", key, headers.get(key));
		});
	}*//*

}

*/
