package pengyi.repository.report;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.message.IMessageResposition;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.report.IReportResposition;
import pengyi.domain.model.report.Report;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: liubowen
 * Date: 2016-03-07 16:22:18
 */
@Repository("reportRepository")
public class ReportResposition extends AbstractHibernateGenericRepository<Report, String> implements IReportResposition<Report, String> {
}

