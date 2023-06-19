package edu.hubu.exam.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hubu.exam.entity.PaperEntity;
import edu.hubu.exam.service.PaperService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;



/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@RequestMapping("exam/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("exam:paper:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paperService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("exam:paper:info")
    public R info(@PathVariable("id") Long id){
		PaperEntity paper = paperService.getById(id);

        return R.ok().put("paper", paper);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:paper:save")
    public R save(@RequestBody PaperEntity paper){
		paperService.save(paper);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("exam:paper:update")
    public R update(@RequestBody PaperEntity paper){
		paperService.updateById(paper);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("exam:paper:delete")
    public R delete(@RequestBody Long[] ids){
		paperService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
