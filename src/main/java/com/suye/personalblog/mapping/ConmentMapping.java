package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Conment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 21:57
 */
@Component
public interface ConmentMapping {

    @Select("select * from conment order by conment_time desc limit 0,5")
    public List<Conment> recentConment();
}
