package asadal.jaeyoung.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import asadal.jaeyoung.service.ScheduleService;
import asadal.jaeyoung.vo.ScheduleVO;

@Controller
public class CalendarController {
	
	@Inject
	private ScheduleService scheduleService;

	@RequestMapping(value="/calendar/schedulePopup",method=RequestMethod.GET)
	public String goSchedule() {
		return "calendar/schedulePopup";
	}
	
	@ResponseBody
	@RequestMapping(value="/calendar/addSchedule",method=RequestMethod.POST)
	public Map<Object,Object> addSchedule(@RequestBody ScheduleVO scheduleVO) throws Exception{
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		
		scheduleService.addSchedule(scheduleVO);
		return map;
	}
	@RequestMapping(value="/")
	public String index(Model model) throws Exception{
		model.addAttribute("showSchedule",scheduleService.showSchedule());
		return "index";
	}
	@RequestMapping(value="/test")
	public String indextest(Model model) throws Exception{
		model.addAttribute("showSchedule",scheduleService.showSchedule());
		return "fullcalendar";
	}
}
