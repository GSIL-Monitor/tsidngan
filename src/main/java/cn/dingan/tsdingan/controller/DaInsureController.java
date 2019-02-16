package cn.dingan.tsdingan.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.service.DaInsureService;
import cn.trasen.BootComm.excel.utils.ImportExcelUtil;
import cn.trasen.BootComm.model.DataSet;
import cn.trasen.core.entity.Result;
import cn.trasen.core.feature.orm.mybatis.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
* @ClassName: DaInsureController
* @Description: 投保人信息
* @author jyq#trasen.cn
* @date 2019年2月12日 下午4:26:04
*
 */
@Api(tags = "投保人信息Controller")
@RestController
public class DaInsureController {
    
    
    private static final Logger logger = LoggerFactory.getLogger(DaInsureController.class);
    
    @Autowired
    private DaInsureService daInsureService;

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @PostMapping("/daInsure/findById/{id}")
    public Result findById(@PathVariable String id) {
        Result result = new Result();
        try {
            DaInsure record = daInsureService.findById(id);
            result.setObject(record);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setMessage("操作失败.");
            result.setSuccess(false);
        }
        return result;
    }
    
    /**
     * 
    * @Title: checkIdNumber
    * @Description: 校验身份证号
    * @param @param idNumber
    * @param @return    参数
    * @return Result    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月15日 下午5:53:32
     */
    @ApiOperation(value = "校验身份证号", notes = "校验身份证号")
    @PostMapping("/daInsure/checkIdNumber/{idNumber}")
    public Result checkIdNumber(@PathVariable String idNumber) {
        Result result = new Result();
        try {
        	List<DaInsure> list = daInsureService.checkIdNumber(idNumber);
            if(null!=list && list.size()>0) {
	        	 result.setSuccess(false);
	             result.setMessage("该投保人已存在,请检查身份证是否正确");
            }else {
            	result.setSuccess(true);
            	result.setMessage("保存成功");
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setMessage("操作失败.");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 新增
     * 
     * @param record
     * @return
     */
    @ApiOperation(value = "新增投保人信息", notes = "新增投保人信息")
    @PostMapping("/daInsure/save")
    public Result insertPost(@RequestBody DaInsure record) {
        Result result = new Result();
        try {
            int count = daInsureService.insert(record);
            if(count==2) {
            	result.setSuccess(false);
            	result.setMessage("该投保人已存在,请检查身份证是否正确");
            }else{
            	result.setSuccess(true);
            	result.setMessage("保存成功");
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setMessage("添加失败,请联系管理员");
            result.setSuccess(false);
        }
        return result;
    }



    /**
     * 修改
     * 
     * @param record
     * @return
     */
    @ApiOperation(value = "修改投保人信息", notes = "修改投保人信息")
    @PostMapping("/daInsure/update")
    public Result updateByPostlId(@RequestBody DaInsure record) {
        Result result = new Result();
        try {
            int i=daInsureService.update(record);
            if(i==1){
                result.setSuccess(true);
                result.setMessage("操作成功.");
            }else{
                result.setSuccess(false);
                result.setMessage("修改失败.");
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("操作失败.");
        }
        return result;
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @ApiOperation(value = "删除投保人信息", notes = "删除投保人信息")
    @PostMapping("/daInsure/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        Result result = new Result();
        try {
            daInsureService.deleteById(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("操作失败.");
        }
        return result;
    }

    /**
     * 查询列表
     * 
     * @param page
     * @param record
     */
    @ApiOperation(value = "获取投保人列表", notes = "投保人列表")
    @PostMapping("/daInsure/list")
    public Result getPostList(Page page, DaInsure record) {
    	 Result result = new Result();
         try {
        	 DataSet<DaInsure> dataset = daInsureService.getDataSetList(page, record);
        	 result.setObject(dataset);
             result.setSuccess(true);
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
             result.setSuccess(false);
             result.setMessage("操作失败.");
         }
        return result;
         
    }

//    /**
//     * 
//     * @Title: export
//     * @Description: 投保人导出
//     * @param @param page
//     * @param @param request
//     * @param @param response 参数
//     * @return void 返回类型
//     * @throws @author jyq#trasen.cn
//     * @date 2018年4月20日 上午10:07:03
//     */
//    @GetMapping("/post/export")
//    public void export(Page page, HttpServletRequest request, HttpServletResponse response,
//            DaInsure record) {
//        // 导出文件名称
//        String name = "投保人管理.xls";
//        // 模板位置
//        String templateUrl = "template/post.xls";
//        // 导出数据列表
//        List<DaInsure> list = daInsureService.getPostList(record);
//        try {
//            ExportUtil.export(request, response, list, name, templateUrl);
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }

    
    /**
     * 
    * @Title: importPost
    * @Description: 投保人导入
    * @param @param file
    * @param @return    参数
    * @return Result    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2018年4月26日 下午2:46:40
     */
    @PostMapping("/daInsure/import")
    public Result importDaInsure(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        try {
            @SuppressWarnings("unchecked")
            List<DaInsure> datas = (List<DaInsure>) ImportExcelUtil.getExcelDatas(file, DaInsure.class);
            if (null != datas && datas.size() > 0) {
                String msg = daInsureService.importDaInsure(datas);
                if (StringUtils.isBlank(msg)) {
                    result.setSuccess(true);
                    msg = "导入成功";
                } else {
                    result.setSuccess(false);
                }
                result.setMessage(msg);
            } else {
                result.setSuccess(false);
                result.setMessage("导入数据不能为空");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("操作失败.");
        }
        return result;
    }
}
