package priv.freeeeeedom.timer.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import priv.freeeeeedom.timer.data.ScfTimerDTO;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author Nevernow
 */
@Repository("scfTimerTaskDao")
public class ScfTimerTaskDao extends JdbcDaoSupport {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfTimerTaskDao.class);

    @Resource(name = "dataSource")
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<Map<String, Object>> getTimerTask(ScfTimerDTO dto) {
        String sql = "select * from timer_task where SCF_NAMESPACE=? and SCF_NAME=? and TRIGGER_NAME=?";
        List<Map<String, Object>> result = null;
        log.info(sql);
        try {
            result = this.getJdbcTemplate().queryForList(sql, dto.getNameSpace(), dto.getFunctionName(), dto.getTriggerName());
        } catch (EmptyResultDataAccessException e) {
            log.info("查询结果为空!");
        }
        return result;
    }

    public void updateInUseTimerTask(String inUse, String taskId) {
        String sql = "update `timer_task` SET `IN_USE` =? WHERE `ID`=?";
        log.info(sql);
        this.getJdbcTemplate().update(sql, inUse, taskId);
    }
    public void updateIsAsyncTimerTask(String isAsync, String taskId) {
        String sql = "update `timer_task` SET `IS_ASYNC` =? WHERE `ID`=?";
        log.info(sql);
        this.getJdbcTemplate().update(sql, isAsync, taskId);
    }
}
