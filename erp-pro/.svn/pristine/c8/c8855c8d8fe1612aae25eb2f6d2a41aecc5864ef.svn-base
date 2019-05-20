package com.pt.modules.restful.web;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.pt.common.utils.DateUtils;
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.modules.sm.smuser.entity.SmUser;
import com.pt.modules.sm.smuser.service.SmUserService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 会员信息Controller
 * @author hexl
 * @version 2018-03-13
 */
@SuppressWarnings("rawtypes")
@Api(value = "RestfulController", description = "接口服务")
@Controller
@RequestMapping(value = "${adminPath}/restful/")
public class RestfulController extends BaseController {

	/**
	 * 上传图片接口
	 */
	
	@ApiOperation(notes = "uploadImage", httpMethod = "POST", value = "上传图片")
	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public Map uploadImage(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
//			// 获取表单数据
//			String userName = mr.getParameter("userName");
			// 获取表单文件
			Iterator<String> it = mr.getFileNames();
			while (it.hasNext()) {
				// 取得上传文件
				MultipartFile file = mr.getFile(it.next());
				if (file != null) {
					try {
						saveUpLoadFile(file, request, " ");
						resultMap.put("state", 1);
						resultMap.put("path",
								"/" + file.getOriginalFilename());
					} catch (Exception e) {
						resultMap.put("state", 0);
					}
				}
			}
		}
		resultMap.put("state", 1);
		resultMap.put("path","");
		return resultMap;
	}
	private void saveUpLoadFile(MultipartFile myfile,
			HttpServletRequest request, String id) throws IOException {
		if (!myfile.isEmpty()) {
//			String fileRealPath = "E:\\platform\\workspacePT2\\xlv3r1\\WebContent\\static\\upload";
			String fileRealPath = "E:\\workspace\\upload\\images";
			
			File file = new File(fileRealPath);
			if (!file.exists()) {
				file.mkdir();
			}
			File saveFile = new File(fileRealPath, myfile.getOriginalFilename());
			if (!saveFile.exists()) {
				FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						saveFile);
				// 后台保存;
			}
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
		}
	}
}