package com.ruoyi.logistics.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.logistics.domain.LnLogistics;
import com.ruoyi.logistics.service.ILnLogisticsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物流信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
@RestController
@RequestMapping("/logistics/logistics")
public class LnLogisticsController extends BaseController
{
    @Autowired
    private ILnLogisticsService lnLogisticsService;

    /**
     * 查询物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('logistics:logistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(LnLogistics lnLogistics)
    {
        startPage();
        List<LnLogistics> list = lnLogisticsService.selectLnLogisticsList(lnLogistics);
        return getDataTable(list);
    }

    /**
     * 导出物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('logistics:logistics:export')")
    @Log(title = "物流信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LnLogistics lnLogistics)
    {
        List<LnLogistics> list = lnLogisticsService.selectLnLogisticsList(lnLogistics);
        ExcelUtil<LnLogistics> util = new ExcelUtil<LnLogistics>(LnLogistics.class);
        util.exportExcel(response, list, "物流信息数据");
    }

    /**
     * 获取物流信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:logistics:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(lnLogisticsService.selectLnLogisticsById(id));
    }

    /**
     * 新增物流信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:logistics:add')")
    @Log(title = "物流信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LnLogistics lnLogistics)
    {
        lnLogistics.setCreateBy(getLoginUser().getUser().getUserName());
        return toAjax(lnLogisticsService.insertLnLogistics(lnLogistics));
    }

    /**
     * 修改物流信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:logistics:edit')")
    @Log(title = "物流信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LnLogistics lnLogistics)
    {
        lnLogistics.setUpdateBy(getLoginUser().getUser().getUserName());
        return toAjax(lnLogisticsService.updateLnLogistics(lnLogistics));
    }

    /**
     * 删除物流信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:logistics:remove')")
    @Log(title = "物流信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(lnLogisticsService.deleteLnLogisticsByIds(ids));
    }
}
