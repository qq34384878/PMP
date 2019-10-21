package com.fangyu.pmp.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fangyu.pmp.common.response.StatusCode;
import com.fangyu.pmp.common.utils.PageUtil;
import com.fangyu.pmp.common.utils.QueryUtil;
import com.fangyu.pmp.model.entity.SysPostEntity;
import com.fangyu.pmp.model.mapper.SysPostDao;
import com.fangyu.pmp.server.service.SysPostService;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 9:16 下午
 */
@Service("sysPostService")
public class SysPostServiceImpl extends ServiceImpl<SysPostDao, SysPostEntity> implements SysPostService {

    // 分页模糊查询
    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        String search = (String) params.get("search");
        // 调用自封装的分页查询工具
        IPage<SysPostEntity> queryPage = new QueryUtil<SysPostEntity>().getPage(params);

        // SQL: SELECT * FROM sys_post WHERE 1=1 AND (post_code LIKE '%经理%' OR post_name LIKE '%经理%')
        QueryWrapper wrapper = new QueryWrapper<SysPostEntity>()
                .like(StringUtils.isNotBlank(search), "post_code", search.trim())
                .or(StringUtils.isNotBlank(search))
                .like(StringUtils.isNotBlank(search), "post_name", search.trim());
        IPage<SysPostEntity> resPage = this.page(queryPage, wrapper);
        return new PageUtil(resPage);
    }

    // 新增
    @Override
    public void savePost(SysPostEntity entity) {
        if (this.getOne(new QueryWrapper<SysPostEntity>().eq("post_code", entity.getPostCode()))!=null){
            throw new RuntimeException(StatusCode.PostCodeHasExist.getMsg());
        }
        entity.setCreateTime(DateTime.now().toDate());
        save(entity);
    }
}
