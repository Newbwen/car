package pengyi.application.message.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.domain.model.message.Message;

/**
 * Created by liubowen on 2016/3/8.
 */
@Component
public class MessageRepresentationMapper extends CustomMapper<Message,MessageRepresentation>{

}
