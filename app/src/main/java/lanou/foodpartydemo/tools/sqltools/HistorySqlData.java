package lanou.foodpartydemo.tools.sqltools;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 *
 * 数据库类, 存储搜索的历史记录
 */
public class HistorySqlData {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String historyStr;

    public HistorySqlData(String historyStr) {
        this.historyStr = historyStr;

    }
    public HistorySqlData() {
    }

    public String getHistoryStr() {
        return historyStr;
    }

    public void setHistoryStr(String historyStr) {
        this.historyStr = historyStr;
    }


}
