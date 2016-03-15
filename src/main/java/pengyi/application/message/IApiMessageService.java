package pengyi.application.message;

import pengyi.application.message.representation.MessageRepresentation;

/**
 * Created by liubowen on 2016/3/15.
 */
public interface IApiMessageService {
    MessageRepresentation apiSearchByCompany(String companyId);

}
