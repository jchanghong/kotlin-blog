package com.jchanghong.mapper

import com.jchanghong.vo.LogInfo
import org.apache.ibatis.annotations.Mapper

/**
 * @author Do
 * *
 * @package com.eumji.zblog.mapper
 * *
 * @name LogDao
 * *
 * @date 2017/4/10
 * *
 * @time 18:16
 */
@Mapper
interface LogMapper {
    /**
     * 保存日志信息
     * @param log
     */
    fun save(log: LogInfo)
}
