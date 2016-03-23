package pengyi.application.message;

import pengyi.application.message.command.*;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/15.
 */
public interface IApiMessageAppService {
    MessageRepresentation show(String messageId);

    MessageRepresentation deleteByCompany(String messageId);

    BaseResponse apiCreateMessage(CompanyCreateMessageCommand command);

    Pagination<MessageRepresentation> companyMessageList(CompanyListMessageCommand command);

}
