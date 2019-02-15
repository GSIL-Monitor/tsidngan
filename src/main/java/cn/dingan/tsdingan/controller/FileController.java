package cn.dingan.tsdingan.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.dingan.tsdingan.model.HrEmployee;
import cn.dingan.tsdingan.model.PlatformResult;
import cn.dingan.tsdingan.model.Result;
import cn.dingan.tsdingan.utils.ApplicationUtils;
import cn.dingan.tsdingan.utils.ImportExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: FileController
 * @Description: 文件上传下载 Controller
 * @author quedd#trasen.cn
 * @date 2018年4月11日 上午10:08:02
 *
 */
@Api(tags = "文件上传下载 Controller")
@RestController
public class FileController {

	public static final String USER_FILE_DIST = "static";

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Value("${saveFileUrl}")
	private String saveFileUrl;
//	@Autowired
//    private HrEmployeeService employeeService;

	 
	
	@ApiOperation(value = "根据业务ID获取文件列表", notes = "根据业务ID获取文件列表")
	@PostMapping("/file/list/{businessId}")
	public Result getFileListByBusiId(@PathVariable String businessId) {
		Result result = new Result();
		try {
//			List<HrFile> files = fileService.findByBusinessId(businessId);
			result.setSuccess(true);
//			result.setObject(files);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMessage("操作错误.");
		}
		return result;
	}
	
	@ApiOperation(value = "更新临时业务ID", notes = "更新临时业务ID")
    @PostMapping("/file/update/{businessId}/{tempBusinessId}")
    @ResponseBody
    public PlatformResult<String> updateTempBusinessId(@PathVariable String businessId, @PathVariable String tempBusinessId) {
        try {
//            fileService.updateTempBusinessId(businessId, tempBusinessId);
            return PlatformResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return PlatformResult.failure();
        }
    }

	@ApiOperation(value = "文件删除", notes = "文件删除")
	@PostMapping("/file/delete/{fileId}")
	public Result deleteFileById(@PathVariable String fileId) {
		Result result = new Result();
		try {
//			fileService.deleteById(fileId);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMessage("操作错误.");
		}
		return result;
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "文件上传V2", notes = "文件上传V2")
	@PostMapping("/file/v2/upload")
	public Result upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Result result = new Result();

		String path = saveFileUrl + File.separator + USER_FILE_DIST + File.separator
				+ new SimpleDateFormat("yyyyMM").format(new Date()) + File.separator
				+ new SimpleDateFormat("dd").format(new Date());
		String fileName = file.getOriginalFilename();
		logger.info("upload fileName:{}", fileName);
		File filePath = new File(path, fileName);
		if (!filePath.getParentFile().exists()) {
			filePath.getParentFile().mkdirs();
		}
		try {
			// 重命名URL地址
			File targetFile = new File(path + File.separator + ApplicationUtils.GUID32() + fileName.substring(fileName.lastIndexOf(".")));
			file.transferTo(targetFile);
			String url = targetFile.getPath().substring(saveFileUrl.length());
			result.setObject(url);
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		result.setSuccess(true);
		return result;
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "文件上传", notes = "文件上传")
	@PostMapping("/file/upload")
	public Result uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@RequestParam(name = "businessId", required = false) String businessId) {
		Result result = new Result();

		Map<String, String> fileData = new HashMap<>();
		String path = saveFileUrl + File.separator + new SimpleDateFormat("yyyyMM").format(new Date()) + File.separator
				+ new SimpleDateFormat("dd").format(new Date());
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		logger.info("upload fileName:{}", fileName);
		File filePath = new File(path, fileName);
		if (!filePath.getParentFile().exists()) {
			filePath.getParentFile().mkdirs();
		}
		try {
//			File targetFile = new File(path + File.separator + fileName);
			File targetFile = new File(path + File.separator + ApplicationUtils.GUID32() + fileName.substring(fileName.lastIndexOf(".")));

			file.transferTo(targetFile);
//			HrFile hrFile = new HrFile();
//			hrFile.setFileName(fileName);
//			hrFile.setFileSize(((Long) file.getSize()).intValue());
//			// 不存储saveUrl
//			hrFile.setFileUrl(targetFile.getPath().substring(saveFileUrl.length()));
//			if (StringUtils.isBlank(businessId)) {
//				businessId = ApplicationUtils.GUID32();
//			}
//			fileService.insert(businessId, hrFile);
			fileData.put("businessId", businessId);
//			fileData.put("fileId", hrFile.getFileId());
//			fileData.put("fileName", file.getOriginalFilename());
//			fileData.put("url", hrFile.getFileUrl());
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		result.setObject(fileData);
		result.setSuccess(true);
		return result;
	}

	/**
	 * 文件下载
	 * 
	 * @param fileId
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@ApiOperation(value = "文件下载", notes = "文件下载")
	@RequestMapping(value = "/file/download/{fileId}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable String fileId, HttpServletResponse response) {
		Assert.hasText(fileId, "fileId must not be null");
//		HrFile file = fileService.findById(fileId);
//		if (file == null) {
//			return;
//		}
//		InputStream is = null;
//		OutputStream os = null;
//		try {
//			response.setContentType("multipart/form-data");
//			response.setHeader("Content-Disposition",
//					"attachment;fileName=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
//			is = new FileInputStream(saveFileUrl + file.getFileUrl());
//			os = response.getOutputStream();
//			byte[] b = new byte[2048];
//			int length;
//			while ((length = is.read(b)) > 0) {
//				os.write(b, 0, length);
//			}
//			os.flush();
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		} finally {
//			IOUtil.close(os);
//			IOUtil.close(is);
//		}
	}
	
	/**
	 * 
	* @Title: export
	* @Description: 导出
	* @param @param record
	* @param @param request
	* @param @param response
	* @param @throws IOException    参数
	* @return void    返回类型
	* @throws
	* @author jyq#trasen.cn
	* @date 2019年1月29日 下午1:50:55
	 */
	@ApiOperation(value = "导出", notes = "导出")
    @GetMapping("/export")
    public void export(HrEmployee record, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
	    // 导出文件名称
//        String name = "xx.xls";
//        // 模板位置
//        String templateUrl = "template/certification.xls";
        
//        DataSet<HrEmployee> dataSet =
//                employeeService.getDataSetList(new Page(), record);
//        List<HrEmployee> list = dataSet.getRows();
//        
//        if (null != dataSet.getRows()) {
//            ExportUtil.export(request, response, list, name, templateUrl);
//        }
    }
	
	   /**
     * 
    * @Title: importQualification
    * @Description: 资质认证导入
    * @param @param file
    * @param @return    参数
    * @return Result    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2018年8月17日 上午10:35:19
     */
    @PostMapping("/import")
    public Result importData(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        try {
            @SuppressWarnings("unchecked")
            List<HrEmployee> datas = (List<HrEmployee>) ImportExcelUtil.getExcelDatas(file, HrEmployee.class);
            if (null != datas && datas.size() > 0) {
//                String msg = HrEmployee.importData(datas);
//                if (StringUtils.isBlank(msg)) {
//                    result.setSuccess(true);
//                    msg = "导入成功";
//                } else {
//                    result.setSuccess(false);
//                }
//                result.setMessage(msg);
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
