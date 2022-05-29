package com.ruoyi.logistics.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.logistics.domain.LnLogistics;
import com.ruoyi.logistics.service.ILnLogisticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.logistics.domain.LnLogisticItems;
import com.ruoyi.logistics.service.ILnLogisticItemsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 快件信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
@RestController
@RequestMapping("/logistics/items")
public class LnLogisticItemsController extends BaseController
{
    @Autowired
    private ILnLogisticItemsService lnLogisticItemsService;

    @Autowired
    private ILnLogisticsService lnLogisticsService;

    /**
     * 查询快件信息列表
     */
    @PreAuthorize("@ss.hasPermi('logistics:items:list')")
    @GetMapping("/list")
    public TableDataInfo list(LnLogisticItems lnLogisticItems)
    {
        startPage();
        List<LnLogisticItems> list = lnLogisticItemsService.selectLnLogisticItemsList(lnLogisticItems);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('logistics:items:list')")
    @GetMapping("/list2")
    public TableDataInfo list2(LnLogisticItems lnLogisticItems)
    {
        startPage();
        List<LnLogisticItems> list = lnLogisticItemsService.selectLnLogisticItemsList2(lnLogisticItems);
        String roomList="";
        for(LnLogisticItems l:list){
//            if(1!=Integer.parseInt(l.getStatus())){
                roomList+=l.getRoomId()+",";
//            }
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("/home/user/Desktop/zll/system/"+lnLogisticItems.getApartmentId()+".html"));
            out.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, viewport-fit=cover\">\n" +
                    "    <title>"+lnLogisticItems.getApartmentId()+"幢 待领取快递清单</title>\n" +
                    "    <style type=\"text/css\">\n" +
                    "        .title {\n" +
                    "            font-size: 30px;\n" +
                    "            color: brown;\n" +
                    "            text-align: center;\n" +
                    "            margin: 0px auto;\n" +
                    "            padding: 10px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .container {\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "            padding-bottom: 60px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .row {\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "            margin-top: 15px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .floor {\n" +
                    "            border-bottom: 1px dashed #aaa;\n" +
                    "        }\n" +
                    "\n" +
                    "        .rooms {\n" +
                    "            margin-top: 8px;\n" +
                    "            display: flex;\n" +
                    "            flex-direction: row;\n" +
                    "            flex-wrap: wrap;\n" +
                    "        }\n" +
                    "\n" +
                    "        .room {\n" +
                    "            padding: 10px;\n" +
                    "            text-align: center;\n" +
                    "            display: inline-flex;\n" +
                    "            font-weight: bold;\n" +
                    "            color: brown;\n" +
                    "            border: 1px dashed brown;\n" +
                    "            margin: 8px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .copyright {\n" +
                    "            width: 400px;\n" +
                    "            position: fixed;\n" +
                    "            bottom: 32px;\n" +
                    "            text-align: center;\n" +
                    "            color: brown;\n" +
                    "            font-size: 12px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"container\" id=\"container\">\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"floor\">\n" +
                    "                1F\n" +
                    "            </div>\n" +
                    "            <div class=\"rooms\">\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"floor\">\n" +
                    "                1F\n" +
                    "            </div>\n" +
                    "            <div class=\"rooms\">\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"floor\">\n" +
                    "                1F\n" +
                    "            </div>\n" +
                    "            <div class=\"rooms\">\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "                <div class=\"room\">101</div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"copyright\">\n" +
                    "        初心龙南青年志愿者程序员研发\n" +
                    "    </div>\n" +
                    "    <script>\n" +
                    "        var list = '"+roomList+"';\n" +
                    "        let rooms = list.split(',')\n" +
                    "        let roomArr = new Array();\n" +
                    "\n" +
                    "        let str = \"\"\n" +
                    "        for (let r of rooms) {\n" +
                    "            if (r) {\n" +
                    "                // console.log(r)\n" +
                    "                let k = `${parseInt(r / 100)}F`\n" +
                    "                if (!roomArr[k]) {\n" +
                    "                    roomArr[k] = []\n" +
                    "                }\n" +
                    "                roomArr[k].push(r)\n" +
                    "            }\n" +
                    "        }\n" +
                    "        for (let o in roomArr) {\n" +
                    "            console.log(o)\n" +
                    "            let floor_rooms = roomArr[o]\n" +
                    "            let rooms = ''\n" +
                    "            for (let i of roomArr[o]) {\n" +
                    "                rooms += `<div class=\"room\">${i}</div>`\n" +
                    "            }\n" +
                    "            str += `<div class=\"row\">\n" +
                    "                    <div class=\"floor\">\n" +
                    "                        ${o}\n" +
                    "                    </div>\n" +
                    "                    <div class=\"rooms\">\n" +
                    "                        ${rooms}\n" +
                    "                    </div>\n" +
                    "                </div>`\n" +
                    "        }\n" +
                    "        document.getElementById('container').innerHTML = str\n" +
                    "        // console.log(roomArr)\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>");
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }

        return getDataTable(list);
    }

    /**
     * 导出快件信息列表
     */
    @PreAuthorize("@ss.hasPermi('logistics:items:export')")
    @Log(title = "快件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LnLogisticItems lnLogisticItems)
    {
        List<LnLogisticItems> list = lnLogisticItemsService.selectLnLogisticItemsList(lnLogisticItems);
        ExcelUtil<LnLogisticItems> util = new ExcelUtil<LnLogisticItems>(LnLogisticItems.class);
        util.exportExcel(response, list, "快件信息数据");
    }

    /**
     * 获取快件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:items:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(lnLogisticItemsService.selectLnLogisticItemsById(id));
    }

    /**
     * 新增快件信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:items:add')")
    @Log(title = "快件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LnLogisticItems lnLogisticItems)
    {
        lnLogisticItems.setCreateBy(getLoginUser().getUser().getUserName());
        return toAjax(lnLogisticItemsService.insertLnLogisticItems(lnLogisticItems));
    }

    /**
     * 修改快件信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:items:edit')")
    @Log(title = "快件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LnLogisticItems lnLogisticItems) throws IOException
    {
        lnLogisticItems.setUpdateBy(getLoginUser().getUser().getUserName());

        // 检查上导房间是否存在
        LnLogistics lnLogistics = new LnLogistics();
        lnLogistics.setPlatform(1);
        lnLogistics.setApartmentId(lnLogisticItems.getApartmentId());
        lnLogistics.setRoomId(lnLogisticItems.getRoomId());
        List<LnLogistics> list = lnLogisticsService.selectLnLogisticsList(lnLogistics);
//        LnLogistics checkLogistics = lnLogisticsService.selectOneLnLogistics(lnLogistics);
        if(list.size()==0){
            lnLogistics.setCreateBy(getLoginUser().getUser().getUserName());
            lnLogisticsService.insertLnLogistics(lnLogistics);
        }
        // status=2,到楼，发通知
//        if(lnLogisticItems.getStatus().equals("2")){
//            System.out.println("Arrive Building:"+lnLogisticItems.getApartmentId()+"-"+lnLogisticItems.getRoomId());
//        }
        return toAjax(lnLogisticItemsService.updateLnLogisticItems(lnLogisticItems));
    }
    /**
     * 修改快件信息
     */
    @Log(title = "快件信息", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadImg")
    public AjaxResult uploadImg(@RequestParam("avatarfile") MultipartFile file,@RequestParam("id") Long id) throws IOException
    {
        LnLogisticItems lnLogisticItems=new LnLogisticItems();
        lnLogisticItems.setId(id);
        //lnLogisticItems.setStatus("1");
        if (!file.isEmpty())
        {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            System.out.println("================================>img");
            System.out.println(id);
            System.out.println(avatar);
            lnLogisticItems.setImg(avatar);
        }
        lnLogisticItems.setUpdateBy(getLoginUser().getUser().getUserName());
        return toAjax(lnLogisticItemsService.updateLnLogisticItems(lnLogisticItems));
    }

    /**
     * 删除快件信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:items:remove')")
    @Log(title = "快件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(lnLogisticItemsService.deleteLnLogisticItemsByIds(ids));
    }
}
