package com.pzy.controller;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pzy.entity.Video;
import com.pzy.service.CategoryService;
import com.pzy.service.VideoService;
/***
 * @author panchaoyang
 * qq:263608237
 */
@Controller
@RequestMapping("/admin/video")
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("categorys",categoryService.findAll());
		return "admin/video/create";
	}
	@RequestMapping("index")
	public String index() {
		return "admin/video/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(Video video,Model model) {
		video.setCreateDate(new Date());
		videoService.save(video);
		model.addAttribute("tip","发布成功");
		return "admin/video/create";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(value = "sEcho", defaultValue = "1") int sEcho,
			@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", defaultValue = "10") int iDisplayLength, String name
			) throws ParseException {
		int pageNumber = (int) (iDisplayStart / iDisplayLength) + 1;
		int pageSize = iDisplayLength;
		Page<Video> users = videoService.findAll(pageNumber, pageSize, name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aaData", users.getContent());
		map.put("iTotalRecords", users.getTotalElements());
		map.put("iTotalDisplayRecords", users.getTotalElements());
		map.put("sEcho", sEcho);
		return map;
	}
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			videoService.delete(id);
			map.put("state", "success");
			map.put("msg", "删除成功");
		} catch (Exception e) {
			map.put("state", "error");
			map.put("msg", "删除失败，外键约束");
		}
        return map;
	}
}
